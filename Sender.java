/**

 * Sender.java
 * Class for simulating an end system that is
 * sending a file across the network.
 *
 */

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Sender extends Thread {
    // Communications Port
    private Port port;

    // Filename to be read from
    private String fileName;

    // instance variables for sliding window protocol
    private GBNProtocol window = new GBNProtocol(3);
    // Statistics
    private int packetSent = 0;
    private int retransmissions = 0;
    private int acksReceived = 0;
    private int nacksReceived = 0;
    private int totalDataSent = 0;
    private int packetTimeouts = 0;

    /**
     * Creates a Sender object to transmit the contents of the given 
     * file through the give communications port.
     * @param filename  the file to be transmitted
     * @param port      the port to be used for communication
     */
    public Sender(String filename, Port port)
    {
        this.port = port;
        this.fileName = filename;
    }

    /**
     * Sends the given packet through the outgoing channel of the
     * sender's communications port.
     * @param aFrame    the frame to be setn
     */
    public void sendPacket(Packet aPacket)
    {
    	//begin editing
        // Send the packet 
    	this.port.sendPacket(aPacket);
        // Extract the sequence number
    	int seqnumber=aPacket.getSeqNo();
        // Check to see if this is a retransmission
       if(this.window.containsPacketSeq(seqnumber))
        // Increment the number of retransmissions
       this.retransmissions++;
       else
        // Increment the number of frames sent
       this.packetSent++;
        // Add the packet to the window
      this.window.addSentPacket(new SentPacket(aPacket));
    	//end editing
    }

   /**
     * Main loop for the sender thread.
     * Reads the source file and transmitts to the Receiver thread.
     */
    @Override
    public void run(){

        FileReader fr = null;
        /* try to open input file */
        try{
            fr = new FileReader(this.fileName);
        }catch(FileNotFoundException fnfe){
            System.err.println("Cannot find file: " + this.fileName);
            System.exit(-1);
        }
        //start editing
        
        
        // Create a list of packets to be sent over the channel
         Packet packet1 = null;
        // Sliding window protocol implrmentation
        while(port.hasMessageWaiting())
        {
    	if(packet1.getAck()==true)
    	{
    	System.out.println("I've recieved an ack");
    	this.acksReceived++;
    	}
    	else
    	{
    	System.out.println("I've recieved a nak");
    	this.nacksReceived++;
    	}
    	if(this.window.hasPacketTimeouts())
    	{
    	this.packetTimeouts++;
    	for (SentPacket packet : this.window.getAllPackets())
            sendPacket(packet.getPacket());
    	break;
    	}
        }
        //end editing                     
        
    }

    /**
     * Compiles a string representation of the Sender object's
     * statistics
     * @return  A String containing the vital statistics of the Sender's
     *          transmission
     */
    @Override
    public String toString()
    {
        return "Sender Statistics:  \n" +
                "\tPckets Transmitted:  " + this.packetSent + "\n" +
                "\tRetransmissions:  " + this.retransmissions + "\n" +
                "\tACKs Received:  " + this.acksReceived + "\n" +
                "\tNACKs Received:  " + this.nacksReceived + "\n" +
                "\tBytes Sent:  " + this.totalDataSent + "\n" +
                "\tPacket Timeouts:  " + this.packetTimeouts;
    }
}
