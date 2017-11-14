package lafoodpackage;

/*******************************************************************************
 * Queue Interface
 * 
 * Unmodified class from assignment documentation. This class is an interface
 * that provides the enqueue, dequeue, makeEmpty, getFromt, size, isEmpty and
 * isFull methods for the Queue array class. Because it is not an actual class
 * and is indeed an interface, these methods are not implemented. 
 * 
 * Preconditions: Declarations must match those of the QueueArray Class.
 * Postconditions: QueueArray class works. 
 * 
 * @author Mariah Avalos
 * @date 10/15/2017
 * @version 1.0
 * 
 ******************************************************************************/

public interface Queue 
{
  // Transformers/Mutators
  public void enqueue(Object x);
  public Object dequeue();
  public void makeEmpty();
  
  // Observers/Accessors
  public Object getFront();
  public int size();
  public boolean isEmpty();
  public boolean isFull();
}