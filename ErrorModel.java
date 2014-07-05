/**
 * ErrorModel.java
 *
 */

public class ErrorModel{
    /* error type flags */
    private boolean perfect = false;
    private boolean   lossy = false;
       

    /* Channel Types :                 */
    public static final int PERFECT = 0;
    public static final int LOSSY = 1;

    public static final String[] channelTypes = {"PERFECT", "LOSSY"};

    /* Processes the packet based on errorModel */
    public Packet induceError(Packet p){
       
		  /* perfect channel? */
        if(perfect)
            /* yes, do nothing */
            return p;

        /* lossy channel? */
        if(lossy)
            /* yes, return null */
            return (p = null);
				
        return null;
    }


    /** Construct an ErrorModel object */
    public ErrorModel(int gotType){
        switch(gotType){
          case LOSSY:
                      lossy = true;
                       break;
           default:
                       perfect = true;
        }
    }
}


