import daj.*;

/**
 *
 *
 *	Main application for following example:
 *	Huang Termination Algorithm.
 *
 *
 */
public class HTAMain extends Application
{

	/**
	 * Constant defines number of processes.
	 */
	private final int NO_OF_NODES = 5;

	/**
	 *	Constructor.
	 */
    public HTAMain()
	{
		super("Huang's Termination Detection", 400, 400);
	}

	/**
	 *	Main function contructs application object.
	 *
	 *	@param args Command line arguments.
	 */
	public static void main(String[] args)
    {
        new HTAMain().run();
    }

	/**
	 *	Constructs network and initializes nodes.
	 */
    public void construct()
    {
		//create an array of nodes
        Node[] nodes = new Node[NO_OF_NODES];
		
		// array that holds node locations (x and y coordinate of nodes)
		int[][] position = {
			{ 100, 100 },
			{ 200, 100 },
			{ 250, 200 },
			{ 200, 300 },
			{ 100, 300 }
		};

		// creating nodes
		for (int i = 0; i < nodes.length; i++)
		{
			nodes[i] = node(new HTAProg(i), Integer.toString(i), position[i][0], position[i][1]);
		}

		// creating links
		for (int i = 0; i < nodes.length - 1; i++)
		{
			for (int j = i + 1; j < nodes.length; j++)
			{
				link(nodes[i], nodes[j]);
				link(nodes[j], nodes[i]);
			}
		}

	}
	
	/**
	 *	Returns description of application.
	 *
	 *	@return Description of application.
	 */
    public String getText(){
      	return "Huang's Termination Program\n";
    }

    public void resetStatistics(){ }
}
