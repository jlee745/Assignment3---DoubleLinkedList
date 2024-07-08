import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.w3c.dom.Node;

/**
 * A basic implementation of a double linked list.
 *
 * @param <T> data type
 * 
 * @author Jaegyoon Lee
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {
	protected Node head;
	protected Node tail;
	protected int size;
	
	/*
	 * Initializes an empty double linked list
	 */
	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/*
	 * Adds a new element to the end of the list.
	 * 
	 * @param data to be added
	 */
	public void addToEnd(T data) {
		Node newNode = new Node(data);
		if(tail == null) {
			tail = newNode;
			head = tail;
		}else { 
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}
	
	/*
	 * Adds a new element to the front of the list.
	 * 
	 * @param data to be added
	 */
	 public void addToFront(T data) {
		Node newNode = new Node(data);
		if (head == null) {
			tail = newNode;
			head = tail;
		}else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		size++;
	}
	 
	 /*
	  * Returns the data in the first node of the list.
	  * 
	  * @return the first node, or return null if the list is empty
	  */
	public T getFirst() {
		return head != null ? head.data : null;
		
	}
	
	 /*
	  * Returns the data in the last node of the list.
	  * 
	  * @return the last node, or return null if the list is empty
	  */
	public T getLast() {
		return tail != null ? tail.data : null;
	}
	
	 /*
	  * Returns the current number of elements in the list.
	  * 
	  * @return the current number of elements in the list.
	  */
	public int getSize() {
		return size;
	}
	
	/*
	 * Provides a ListIterator over the elements in the list, 
	 * allowing forward and backward traversal.
	 * 
	 * @return a list iterator over the elements in this list
	 */
	public ListIterator<T> iterator(){
		return new DoubleLinkedListIterator();
	}
	
	/**
	 * Searches for and removes the first node that matches the targetData, according to the provided comparator.
	 *
	 * @param targetData the data element to find and remove from the list
	 * @param comparator the tool used to compare elements for equivalence
	 * @return the removed Node if found, otherwise returns null
	 */

	public Node remove(T targetData, Comparator <T> comparator) {
		Node current = head;
		while (current != null) {
			if (comparator.compare(targetData, current.data)== 0) {
				if (current == head) {
					head = current.next;
					if(head != null) {
						head.prev = null;
					}
				}else {
					current.prev.next = current.next;
				}
				size--;
				return current;

		}
			current = current.next;
	}
	return null;
}
	
	/**
	 * Removes and returns the first element from the list, if it exists.
	 *
	 * @return the data from the first node if the list is not empty; otherwise, returns null.
	 */
	public T retrieveFirstElement() {
		if (head == null) {
			return null;
		}
		T data = head.data;
		head = head.next;
		if(head != null) {
			head.prev = null;
		}else {
			tail = null;
		}
		size--;
		return data;
	}
	
	/**
	 * Removes and returns the last element from the list, if it exists.
	 *
	 * @return the data from the last node if the list is not empty; otherwise, returns null.
	 */
	public T retrieveLastElement() {
		if(tail == null) {
			return null;
		}
		T data = tail.data;
		tail = tail.prev;
		if (tail != null) {
			tail.next = null;
		}else {
			head = null;
		}
		size--;
		return data;
	}
	
	 /**
     * Return doubly-linked list in the form of an array list
     * @return array-list form of current list
     */
	public ArrayList<T> toArrayList(){
		ArrayList<T> list = new ArrayList<>();
		Node current = head;
		while (current != null) {
			list.add(current.data);
			current = current.next;
		}
		return list;
	}
	
	 /**
     * This inner class contains the structure of an element
     * in the doubly-linked list with data as well as
     * previous and next elements
     */
	protected class Node {
        T data;
        Node prev;
        Node next;
        
        /**
         * Constructs a new node with the specified data.
         * 
         * @param data the data to be stored 
         */
        Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

	/**
     * List iterator implementation for the double linked list.
     */
	protected class DoubleLinkedListIterator implements ListIterator<T> {
		private Node current;
		private int index;
		
		/**
         * Constructs a new list iterator starting at the head of the list.
         */
		DoubleLinkedListIterator() {
			current = head;
			index = 0;
		}
		
		/**
         * Checks if there is a next element in the list.
         *
         * @return true if there is a next element, false otherwise
         */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
         * Returns the next element in the list and advances the iterator.
         *
         * @return the next element in the list
         * @throws NoSuchElementException if there are no more elements
         */
		@Override
	    public T next() {
	        if (!hasNext()) {
	            throw new NoSuchElementException();
	        }
	        T data = current.data;
	        current = current.next;
	        index++;
	        return data;
	    }

		/**
         * Checks if there is a previous element in the list.
         *
         * @return true if there is a previous element, false otherwise
         */
		@Override
		public boolean hasPrevious() {
		    return (current != head);
		}

		/**
         * Returns the previous element in the list and moves the iterator backwards.
         *
         * @return the previous element in the list
         * @throws NoSuchElementException if there are no previous elements
         */
		@Override
		public T previous() {
		    if (!hasPrevious()) {
		        throw new NoSuchElementException();
		    }
		    if (current == null) {
		        current = tail; 
		    } else {
		        current = current.prev;
		    }
		    index--;
		    return current.data;
		}
		
		/**
         * Unsupported operation.
         *
         * @throws UnsupportedOperationException when this method is called
         */
		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		/**
         * Unsupported operation.
         *
         * @throws UnsupportedOperationException when this method is called
         */
		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		/**
         * Unsupported operation.
         *
         * @throws UnsupportedOperationException when this method is called
         */
		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
			
		}

		/**
         * Unsupported operation.
         *
         * @throws UnsupportedOperationException when this method is called
         */
		@Override
		public void set(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
			
		}

		/**
         * Unsupported operation.
         *
         * @throws UnsupportedOperationException when this method is called
         */
		@Override
		public void add(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}
	
	
}
