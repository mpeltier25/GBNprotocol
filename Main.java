import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
public class Main{
    public static final int WINDOW_SIZE = 3;
    private Sender sender;
    private Receiver receiver;
    private Channel senderToReceiver,receiverToSender;
    private int errType;
    private String srcFile,dstFile,logFile;
    public void startSim(){

        System.out.println("GBNProtocol Simulation" +
                "\n\tWindow Size:  " + Project3.WINDOW_SIZE +
                "\n\tInput File:  " + this.srcFile +
                "\n\tOutput File:  " + this.dstFile +
                "\n\tChannel Log File:  " + this.logFile +
                "\n\tChannel Type:  " + ErrorModel.channelTypes[this.errType] + "\n");
        receiver.start();
        sender.start();

        try{
            sender.join();
        }catch(InterruptedException e){
            System.err.println("sender interrupted exception");
            System.exit(1);
        }
        try{
            receiver.join();
        }catch(InterruptedException e){
            System.err.println("receiver interrupted exception");
            System.exit(1);
        }
        System.out.println("\nSimulation Complete:");
        System.out.println(sender + "\n" +
                receiver);
    }
    /* constructor */
    public Project3(String[]args){

        errType = Integer.parseInt(args[0]);
        srcFile = args[1];
        dstFile = args[2];
        logFile = args[3];
        try{
            senderToReceiver =
                new Channel(
                            new ErrorModel(errType),
                            new PrintStream(new FileOutputStream(logFile))
                            );

            receiverToSender =
                new Channel(
                            new ErrorModel(ErrorModel.PERFECT),
                            null
                            );
        }catch(FileNotFoundException e){
            System.err.println("Could not find logfile: "+logFile);
            System.exit(-1);
        }

        /* instantiate sender and receiver */
        sender = new Sender(srcFile,new Port(senderToReceiver, receiverToSender));
        receiver = new Receiver(dstFile,new Port(receiverToSender, senderToReceiver));
    }
    /** main routine */
    public static void main(String[]args){
        if(args.length != 4)
            System.err.println("Need to use Project3 <error-type> <source-file> <dest-file> <log-file>");
        else
            new Project3(args).startSim();

        System.exit(args.length-4);
    }
}
