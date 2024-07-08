import java.util.Comparator;
import java.util.ListIterator;

/**
 * A sorted implementation of a double linked list.
 *
 * @param <T> data type
 * 
 * @author JaegyoonLee
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	private Comparator<T> comparator;
	
	 /**
     * Constructor to set type of comparator for doubly-linked list 
     * @param comparator sorting tool 
     */
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	 /**
     * Add element with the compare method from 
     * to insert it into the right position
     * @param data data to be entered
     * @return instance of sorted doubly-linked list
     */
	public void add(T data) {
		Node newNode = new Node(data);
        if (head == null) { 
            head = newNode;
            tail = newNode;
        } else {
            Node current = head;
            Node prev = null;
            while (current != null && comparator.compare(data, current.data) > 0) {
                prev = current;
                current = current.next;
            }
            if (prev == null) { 
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else if (current == null) { 
                prev.next = newNode;
                newNode.prev = prev;
                tail = newNode;
            } else { 
                prev.next = newNode;
                newNode.prev = prev;
                newNode.next = current;
                current.prev = newNode;
            }
        }
        size++;
	}
	
	  /**
     * Add new element to the end of list, no longer needed here
     * @param data data to be inserted at the end of list
     * @return instance of doubly-linked list
     */    
	@Override
    public void addToEnd(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

	 /**
     * Add new element to the beginning of list, no longer needed here
     * @param data data to be inserted at the beginning of list
     * @return instance of doubly-linked list
     */
    @Override
    public void addToFront(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }
	
    /**
     * Returns instance of iterator from basic doubly-linked list's inner class 
     * to traverse over the doubly-linked list
     * @return Instance of iterator inner class in basic doubly-linked list
     * @throws UnsupportedOperationException method has not been implemented yet
     * @throws NoSuchElementException element does not exist
     */
    @Override
    public ListIterator<T> iterator() {
        return super.iterator(); 
    }

    /**
     * Delete first instance of data element in the doubly-linked list using
     * the remove method from the basic doubly-linked list class
     * @param data data to be deleted in list
     * @param comparator interface to compare data of elements
     * @return instance of sorted doubly-linked list
     */
    @Override
    public Node remove(T data, Comparator<T> comparator) {
        return super.remove(data, comparator);
    }
}