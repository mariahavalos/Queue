package lafoodpackage;

/*******************************************************************************
 * QueueArray Class
 * 
 * This is a queue class that creates a default array or an array based on arguments, and performs the following 
 * on these arrays: 
 * -enqueue, to add elements
 * -dequeue, to remove and return elements
 * -makes the queue empty
 * -increases the queue size
 * -gets the front element of the queue
 * -returns the size of the queue
 * -checks whether or not the queue is empty
 * 
 * It will also throw an exception if the array is attempted to be dequeued when empty. 
 * 
 * Preconditions: 
 * Postconditions: 
 * 
 * @author Mariah Avalos
 * @date 10/15/17
 * @version 1.0
 * 
 ******************************************************************************/

public class QueueArray implements Queue{
	/*This class is the foundation for the program. It creates an array based queue,
	 * while also being unbounded and therefore never full, and uses a DEFCAP based on 
	 * the actual average number of customers a restaurant gets if capacity allows. The
	 * class allows for enqueuing, dequeing, checking capacity, and modifying the queue.
	 * Positioning of the methods matches exactly with the Queue Class for increased
	 * readability. The methods are based off the standard class for unbounded array based 
	 * queues. 
	 */
	
	  protected Object[] array; 
	  protected int front = 0, elements_in_queue = 0, DEFCAP = 600, back, starting_capacity;  
	  
	  public QueueArray() { 
		/*default constructor, initializes an array of size DEFCAP and defines the back and original capacity of array*/
		  
		array = (Object[]) new Object[DEFCAP];
		back = DEFCAP - 1;
	    starting_capacity = DEFCAP;
	  }

	  public QueueArray(int starting_capacity) { 
		/*constructor, initializes an array of size starting_capacity and defines the back and original capacity of array*/
		  
		array = (Object[]) new Object[starting_capacity];
		back = starting_capacity - 1;
	    this.starting_capacity = starting_capacity;
	  }
	  
	  // Transformers/Mutators
	  //------------------------------------------------------------------------------------------------------------------

	  public void enqueue(Object element_to_add){ 
		/* append element to end of queue */
		  
	    if (elements_in_queue == array.length) 
	      increase_size();

	    back = (back + 1) % array.length;
	    array[back] = element_to_add;
	    elements_in_queue += 1;
	  }

	  public Object dequeue(){
		/*removes the element from the front of the queue if queue is not empty, then returns the element.
		 *otherwise throws exception*/
		  
	   if (isEmpty())
	      throw new QueueUnderflowException("Error! Queue is empty!");
	    
	   else{
		   
	      Object dequeued_element = array[front];
	      array[front] = null;
	      front = (front + 1) % array.length;
	      elements_in_queue -= 1;
	      
	      return dequeued_element;
	    }
	  }
	  
	  public void makeEmpty() {
		/*dequeues all elements until queue is empty*/
		  
		for (int i = 0; i < elements_in_queue; i++){
			array[front] = null;
		    front = (front + 1) % array.length;
		    elements_in_queue -= 1;
	    }
	  }
	  
	  private void increase_size(){ 
		/*if the queue can hold no more, increase the queue by the current capacity plus the starting capacity. 
		 * This is an additional method needed, as we're maintaining an unbounded array*/
		  
	    Object[] larger_array = (Object[]) new Object[array.length + starting_capacity];
	    int smaller_array_index = front;
	    
	    for (int larger_array_index = 0; larger_array_index < elements_in_queue; larger_array_index++)
	    {
	      larger_array[larger_array_index] = array[smaller_array_index];
	      smaller_array_index = (smaller_array_index + 1) % array.length;
	    }

	    front = 0;
	    back = elements_in_queue - 1;
	    array = larger_array;
	  }
	  
	  // Observers/Accessors
	  //----------------------------------------------------------------------------------------------------------------------------

	  public Object getFront() {
		  /*gets the front of the queue*/ 
		  
		Object element = array[front];
		
		return element;
	  }  
	  
	  public int size(){ 
		/*returns size of the queue*/
		  
		return elements_in_queue;
	  }
	  
	  public boolean isEmpty(){ 
		/*checks if queue is empty and returns boolean value */  
		  
	    return (elements_in_queue == 0);
	  }
	  
	  public boolean isFull() { 
		/*normally checks if queue is full, but unbounded arrays won't get full so return is always false */
		  
	    return false;
	  }
}
