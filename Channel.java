/**
 * Channel.java
 * Class for representing a communication channel
 *
 */
 
// You may modify this to suit your design

import java.util.concurrent.*;
import java.io.PrintStream;

/** This class must be used as it is */
public class Channel{

    /* Internal Packet buffer. */
    private BlockingQueue<Packet>q =
            new LinkedBlockingQueue<Packet>();
    /* Instance of error model for this channel. */
    private ErrorModel errorModel;
 
    /* Logging output stream. */
    private PrintStream log;


    /** Constructor - Constructs the object and sets parameters.
      * @param errorModel the error model for this channel.
      * @param log Output Log. */
    public Channel(ErrorModel errorModel,PrintStream log){
        this.errorModel = errorModel;
        this.log = log;
        if (log != null){
            log.println("\t\t   Packet Index\t\t in Packet ");
            log.println("------------------------------------------------------------------------");
        }
    }

    /** Send one Packet through the channel or block if channel is
     * currently used.
     * @param p Packet to be sent. */
    public void sendPacket(Packet p){
        try{
            q.put(p);
        }catch (InterruptedException e){
            System.err.println("Fatal Error: - Channel interrupted during write");
            System.exit(1);
        }
    }


    /** Receive one packet from channel or block if none is available.
     * @return Received packet. */
    public Packet recvPacket(){

        Packet p = null, c = null;

        try{
            /* pop the queue to p */
           p = q.take();
        }catch(InterruptedException e){
            System.err.println("Fatal Error: Channel interrupted during read");
            System.exit(-1);
        }

        String logMessage = "";
        /* induce error */
        c = errorModel.induceError(p);
		  
        //assign an appropriate value to the log message to be printed to the log file
        if (log != null)
            log.println(logMessage);

        /* return the processed packet */
        return c;
    }

    /**
     * Retrieves the total number of packets that have been put onto the
     * Channel, but not yet received.
     * @return  the number of unreceived bytes
     */
    public int packetsInChannel() {return q.size();}
}
