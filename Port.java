/**

 * Port.java
 * Class for representing a communication port
 * with incoming and outgoing channels.
 *
 */


/**
 * This class represents a port.
 */
public class Port
{
    /**
     * Outgoing port.
     */
    protected Channel outgoing;
    /**
     * Incoming port.
     */
    protected Channel incoming;

    /**
     * Constructor - Constructs the object and sets parameters.
     * @param outgoing port -- Outgoing channel.
     * @param incoming port -- Incoming channel.
     */
    public Port( Channel outgoing, Channel incoming )
    {
        this.outgoing = outgoing;
        this.incoming = incoming;
    }

    /**
     * Transmit packet over outgoing channel.
     */
    public void sendPacket(Packet p){
        //fill in here
    	//start edit
    	outgoing.sendPacket(p);
    	//end edit
    }


    /** Receive packet from incoming channel. */
    public void recvPacket(Packet p){
        //fill in here
    	//start edit
    	int index=0;
    	Packet recievedpacket=this.incoming.recvPacket();
    	while (recievedpacket !=null)
    	{
    	if(index<p.getSize())
    	recievedpacket=this.incoming.recvPacket();
    	}
    	//end edit
		  
	 }


    /**
     * Check to see if there are any packets
     * available for receipt
     * @return  true if the incoming channel has a packet waiting
     */
    public boolean hasMessageWaiting ()
    {
        return this.incoming.packetsInChannel() > 0;
    }
}
