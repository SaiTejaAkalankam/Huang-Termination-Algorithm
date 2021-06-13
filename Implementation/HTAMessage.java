import daj.*;
import java.text.DecimalFormat;

/**
 *	
 *
 *  Huang-Termination algorithm message. 
 *  Can be a Computation Message or a control message.
 *	Sender identification is contained for informational purposes.
 *	Weight thrown and message type is also contained in the message. 
 *
 *	
 */
class HTAMessage extends Message {

	/**
	 *	Constant defines message type descriptions.
	 */
	protected static final String[] TYPEDESCRIPTION = {
			new String("Control Message"),
			new String("Computation Message")
	};

	/**
	 *	Holds sender identification for informational purposes.
	 */
	private int senderId;

	/**
	 *	Holds message type.
	 */
	private int messageType;

	/**
	 *	Holds weight thrown by process.
	 */
	private double weight;

	/**
	 *	Holds Decimal format.
	 *	It is used to format numbers using a formatting pattern you specify yourself.
	 *  In our case we want it round double types to 2 decimal places while displaying weight thrown.
	 */
	private DecimalFormat df;


	/**
	 *	Constructor.
	 *
	 *	@param messageType Type of message (Control or Computation).
	 *	@param senderId	Message sender identification.
	 *  @param weight Weight thrown by process.
	 */	
	public HTAMessage(int messageType, double weight, int senderId) {
		this.messageType = messageType;
		this.senderId = senderId;
		this.weight = weight;
		df = new DecimalFormat("####0.0000");

	}

	/**
	 *	Returns description of the message travelling in the link.
	 *
	 *	@return Description of the message.
	 */
	public String getText() {

		return "Huang's termination message : \n" + TYPEDESCRIPTION[messageType] + "\nSender : " + senderId + "\nWeight thrown : " + df.format(weight);
	}
	
	/**
	 *	Returns message type.
	 *  Message type is 1 for computation message
	 *  Message type is 0 for control message
	 *
	 *	@return Type of message.
	 */
	public int getType() {
		return this.messageType;
	}

	/**
	 *	Returns weight that is thrown by a process.
	 *
	 *	@return Weight thrown by process.
	 */
	public double getWeight() {
		return this.weight;
	}
}
