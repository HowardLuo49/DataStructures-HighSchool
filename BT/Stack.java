import java.util.NoSuchElementException;

public class Stack<T> {
	private Node<T> top;
	private int size;
	
	public Stack() {
		top = null;
		size = 0;
	}
	
	public void push(T data) {
		top = new Node<T>(data, top);
		size++;
	}
	
	public T pop() throws NoSuchElementException {
		if(top == null)
			throw new NoSuchElementException("Empty Stack");
		
		T data = top.data;
		top = top.next;
		size--;
		return data;
	}
	
	public boolean isEmpty()
	{
		return (top == null) || (size == 0);
	}
	
	public int size() { return size; }
	
	public void clear() { top = null; size = 0; }
	
	public T peek() {
		if (top == null) return null;
		return top.data;
	}

}
