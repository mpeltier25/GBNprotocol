
import java.util.ArrayList;

public class GBNProtocol
{
    private int windowSize;
    private int base = 0;
    private int nextSequence = 0;
    private ArrayList<SentPacket> packetList = new ArrayList<SentPacket>();
	public GBNProtocol(int window)
	{
        this.windowSize = window;
	}
    public boolean isFull()
    {
        return (this.packetList.size() >= this.windowSize);
    }

    public boolean isEmpty()
    {
        return (this.packetList.size() == 0);
    }
    
    public void acknowledgePacket(int sequenceNumber)
    {
        for (SentPacket packet : this.packetList)
            if (packet.getSequenceNumber() == sequenceNumber) {
                this.packetList.remove(packet);
                packet.stopTimer();
                break;
            }
    }

    public Packet getPacket(int sequenceNumber)
    {
        for (SentPacket packet : this.packetList)
            if (packet.getSequenceNumber() == sequenceNumber)
                return packet.getPacket();
        return null;
    }

    public boolean hasPacketTimeouts()
    {
        for (SentPacket packet : this.packetList)
            if (packet.isTimerExpired())
                return true;
        return false;
    }

    public ArrayList<SentPacket> getAllPackets()
    {
        return this.packetList;
    }

    public void addSentPacket(SentPacket aPacket)
    {
        aPacket.startTimer();
        this.packetList.add(aPacket);
    }

    public boolean containsPacketSeq(int sequenceNumber)
    {
        for (SentPacket packet : this.packetList)
            if (packet.getSequenceNumber() == sequenceNumber)
                return true;
        return false;
    }
}
