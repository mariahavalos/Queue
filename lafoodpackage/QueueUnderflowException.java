package lafoodpackage;

/*******************************************************************************
 * QueueUnderflowException Class
 * 
 * This is a queue class that enables the Queue class to throw a QueueUnderflowException
 * when an empty array is attempted to be dequeued on. If no string is provided in the
 * class that it is called from, the default runtime exception is used. Otherwise the
 * class' string will be displayed to the user. 
 * 
 * Preconditions: 
 * Postconditions: 
 * 
 * @author Mariah Avalos
 * @date 10/15/17
 * @version 1.0
 * 
 ******************************************************************************/

public class QueueUnderflowException extends RuntimeException{

  public QueueUnderflowException(){
	  /*default constructor*/
    super();
  }

  public QueueUnderflowException(String message){
	  /*throws exception using message from calling class*/
    super(message);
  }
}
