public class Packet
{
    /*  Packet Constructor:
     *  i    = seqno
     *  j    = size of packet
     *  flag = ack
     */
    
    public Packet(int i, int j, boolean flag, byte abyte0[])
    {   
		  ack = false;
        seqno = i;
        size = j;
        ack = flag;
		  data = new byte[size]; 
        data = abyte0;
    }
    
    public void setData(int i, byte byte0)
    {
        data[i] = byte0;
    }

    public byte[] getData()
    {
        return data;
    }
    
    public int getSeqNo()
    {
        return seqno;
    }

    public int getSize()
    {
        return size;
    }

    public boolean getAck()
    {
        return ack;
    }

    public String toString()
    {
        return "seq: " + seqno + " size: " + size + " ack: " + ack + " data: " + data;
    }

    private int seqno;
    private int size;
    private boolean ack;
    private byte data[];
}