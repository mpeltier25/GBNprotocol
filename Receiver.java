/**

 * Receiver.java
 * Class for simulating an end system that is reciving
 * a file from the network
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Receiver extends Thread
{
    // Filename to be written to.
    private String fileName;

    // Communications Port
    private Port port;

    // List of received packets
    private ArrayList<Byte> bytesReceived = new ArrayList<Byte>();

    // Statistics
    private int packetReceived = 0;
    private int acksSent = 0;
    private int nacksSent = 0;
    private int totalBytesReceived = 0;

    /**
     * Creates a Receiver thread to read a file from the given
     * communications port
     * @param filename  the file to write the read data to
     * @param aPort     the communications port to use
     */
    public Receiver(String filename, Port aPort)
    {
        this.fileName = filename;
        this.port = aPort;
    }

       /**
     * Creates a new packete with either ACK or NACK as the data field
     * to send back to the sender in response to the receipt of a packet
     * @param ack       true if the received packet was valid, otherwise false
     * @param seqNum    the sequnce number of the received frame
     * @return          the byte array representation of the reply frame
     */
    private Packet makeReplyPacket(boolean ack, int seqNum)
    {
      Packet p=null;    
		  /**
		      fill in
								
			*/
      //start editing
      if(ack)
      {
      this.acksSent++;  
      }
      else {
      this.nacksSent++;  
      }
      System.out.println("\t\tSent " + ((ack) ? "ACK" : "NACK") + ": " + seqNum);
      //end editing  
		    return p;
    }

    private boolean isLastPacket(byte[] packet)
    {
         /**
		      fill it
				
			*/
    	//start editing
    	for (int b = 0; b < packet.length; b++)
            if (packet[packet.length + b] != (byte) 0)
                return false;
    	//end editing

        return true;
    }
    
    /**
     * Main loop for the receiver thread.
     * Reads packets from the port and acknowledges their receipt.
     * The resulting data is written back to the destination file.
     */
    @Override
    public void run()
    {
               
			/**
		      fill in here				
			*/
    	//start editing
    	while(true)
    	{
    		Packet p = null;
    		int nextSeq = 0;
    		int lastReceived = -1;
    	    int sequenceNumber = 0;
    	    if (sequenceNumber == nextSeq) {
                    nextSeq ++;
                    lastReceived ++;
                    this.packetReceived ++;
                    System.out.println("\t\tReceived: " + sequenceNumber);
                this.port.sendPacket(p);
    		break;
    	    }
    	}
        //end editing
       
        // Open output file
        BufferedWriter writer = null;
        /* try to open output file */
        try{
            writer = new BufferedWriter(new FileWriter(this.fileName));
        } catch (IOException ex) {
            System.err.println("Error opening output file: " + this.fileName);
            System.exit(-1);
        }
    	
		  // write to file
      try {
		writer.write(getName());
	} catch (IOException ex) {
		System.err.println("Error writing to output file: " + this.fileName);
        System.exit(-1);
	}
    }

    /**
     * Compiles a string representation of the Receiver object's
     * statistics
     * @return  A String containing the vital statistics of the Receivers's
     *          transmission
     */
    @Override
    public String toString()
    {
        return "Receiver Statistics:  \n" +
                "\tPackets Received:  " + this.packetReceived + "\n" +
                "\tACKs Sent:  " + this.acksSent + "\n" +
                "\tNACKs Sent:  " + this.nacksSent + "\n" +
                "\tBytes Received:  " + this.totalBytesReceived;
    }
}
