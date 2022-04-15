package Circular;

/**
 * A node for a circular linked list
 */
public class Node<T extends Comparable<T>> {
	T data;
	Node<T> next;
	
	public Node() {
		data = null;
		next = null;
	}
	
	public Node(T data) {
		this.data = data;
		this.next = this;
	}
	
}
