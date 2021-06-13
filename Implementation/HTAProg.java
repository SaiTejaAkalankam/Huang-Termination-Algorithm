import daj.Program;
import java.util.*;
import java.text.DecimalFormat;

/**
 *
 *  Huang-Termination algorithm program.
 *
 *	
 */
public class HTAProg extends Program
{

    /**
	 *	Constant defines process ID.
	 */
    private final int processID;

    /**
	 *	Constant defines process Weight.
	 */
    private double processWeight;

    /**
	 *	Indicates that process is active or idle.
	 */
    private boolean isProcessActive;

    /**
	 *	Constant defines Type of Message.
	 */
    private int msgType;

    /**
	 *	Constant defines Weight that is to be thrown to other process.
	 */
    private double weightToSend;

    /**
	 *	Holds recieved message or incoming message
	 */
    private HTAMessage msgRec;

    /**
	 *	Constant defines Message that is to be sent to other process.
	 */
    private HTAMessage msgtoSent;

    /**
	 *  Holds random array of integers
     *  It is used by controlling agent to throw weights to random processes 
	 */
    private ArrayList<Integer> randomArray;

    /**
	 *	Holds Decimal format.
	 *	It is used to format numbers using a formatting pattern you specify yourself.
	 *  In our case we want it round double types to 2 decimal places while displaying weight thrown.
	 */
    private DecimalFormat df;


    // constructor
    public HTAProg(int processID){
        this.processID = processID;
        df = new DecimalFormat("####0.0000");
        
        if(processID == 0)
        {
            // setting initial weight of controlling agent as 1
            processWeight = 1;

            // setting initial status of controlling agent as active
            isProcessActive = true;
        }
        else{

            // setting initial weight of normal process or non-controlling agent as 0
            processWeight = 0;

            // setting initial status of normal process or non-controlling process as idle
            isProcessActive = false;
        }
    }


    /**
	 *	Huang-termination algorithm execution.
	 */
    public void main()
    {
       
        // Generates random array of integers
        // Used by controlling agent to throw weights to process in random order
       randomArray = new ArrayList<Integer>(new RandomArray().generateRandomArray());

       // controlling agent throws random weights to different processes  
       if(processID == 0){

            // setting message type as 1 i.e. Computational Message 
            msgType = 1;
            
            for(int i = 0; i < randomArray.size(); i++ )
            {
                // generate random weight between 0 and current weight of controlling agent to throw to process
                weightToSend = new Random().nextDouble() * (processWeight);

                // controlling agent sends random weight to each process and his id 
                // and Type of Message which is computational message
                msgtoSent = new HTAMessage(msgType, weightToSend, processID);
                out(randomArray.get(i)).send(msgtoSent);

                // after throwing random weight decrement that random weight from current controlling agent weight
                processWeight -= weightToSend;

                // sleep for some random seconds
                // this is used by controlling agent to put some time interval between throwing weights to different processes
                sleep(new Random().nextInt(3));
            }
        }


       if(processID > 0 && processID < 5)
       {

        // Message type = 0 means it is control message from process to controlling agent
        msgType = 0;

        // recieve computational message from controlling agent
        msgRec = (HTAMessage)in(0).receive();

          if(msgRec.getType() == 1)
          {
            // process becomes active after recieving computational message from contolling agent
             isProcessActive = true;

            // adding recieved weight to weight of process 
             processWeight = msgRec.getWeight();
          }

         // sleep for some random seconds
        // this is used by a process to simulate a computation which takes some time 
         sleep(new Random().nextInt(5));

        //   throw weight back to controlling agent in a control message
          msgtoSent = new HTAMessage(msgType, processWeight, processID);
          out(0).send(msgtoSent);

        //   weight of process becomes 0 again
          processWeight = 0;

        //   process becomes idle after sending control message to contolling agent
          isProcessActive = false;
       }

           
       if(processID == 0)
       {
           //  controlling agent waits till its weight becomes 1 again 
           while(processWeight != 1)
           {
               for(int i = 0; i < randomArray.size(); i++)
               {
                    //  control message is recieved  
                    int index = in().select();
                    msgRec = (HTAMessage)in(index).receive(); 

                    if(msgRec.getType() == 0)
                    {   
                        // recieved weight is added to controlling agent's weight
                        processWeight += msgRec.getWeight();
                    }
               }
           }
       }  
    }


    /**
	 *	Returns description of the node.
	 *
	 *	@return Description of the node.
	 */
    public String getText()
    {
        // // add in node's description the Algorithm name
        StringBuffer text = new StringBuffer("Huang's Termination Program\n\n");

        if(processID == 0)
        {
            // add in node's description the node type as Controlling Agent
            text.append("Node Type : Controlling Agent\n");

            // add in node's description the node status as Active
            text.append("Process Status : Active\n");
        }
        else{
            // add in node's description the node type as Process Node
            text.append("Node Type: Process Node\n");
            text.append("Process Status : ");

            // add in node's description the status of process
            // Active if the process is active or idle
            text.append((isProcessActive) ? "Active\n" : "Idle\n");
        }

        // add in node's description the ID of process
        text.append("Process ID : " + processID);

        // add in node's description the process weight
        text.append("\nProcess Weight : " + df.format(processWeight));
     return text.toString();
    }
}
