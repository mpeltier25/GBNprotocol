/**

 * SentPacket.java
 * Class for storing information about a packet that has been sent.
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class SentPacket implements ActionListener
{
	// Instance Variables
  
    private Packet packet;
    // timer to check for timeouts
    private Timer timer = new Timer(1000, this);
    private boolean expired = false;

	/**
     * send packet
     * @param aPacket    the packet that was sent
     */
	public SentPacket(Packet aPacket){
        this.packet = aPacket;
        this.startTimer();
	}

    /**
     * Start the timer for the sent packet
     */
    public void startTimer()
    {
        this.timer = new Timer(1000, this);
        this.timer.setRepeats(false);
        this.expired = false;
        this.timer.start();
    }

    /**
     * Stop the timer for the sent packet
     */
    public void stopTimer()
    {
        this.timer.stop();
        this.timer = null;
    }

    /**
     * Checks if the timer for this sent packet
     * has expired
     * @return  true if the timer interval has elapsed
     *          otherwise, false.
     */
    public boolean isTimerExpired()
    {
        return this.expired;
    }

    /**
     * Retrieves the packet that was sent
     * @return  the packet
     */
    public Packet getPacket()
    {
        return this.packet;
    }

    /**
     * Retrieves the sequence number of the packet that was sent
     * @return  the packet's sequence number
     */
    public int getSequenceNumber()
    {
        return this.packet.getSeqNo();
    }

    /**
     * Executes when the timer expires
     * @param e Swing event
     */
    public void actionPerformed( ActionEvent e )
    {
        if (e.getSource() == timer)
        {
            this.expired = true;
            this.timer.stop();
        }
    }
}
