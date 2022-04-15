package linear;

class Node<T> {
	T data;
	Node<T> next;

	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}

	public String toString() {
		return data.toString();
	}
}

public class LinkedList<T> {
	Node<T> front;
	int numNodes;

	public LinkedList(Node<T> front) {
		this.front = front;
		numNodes = countNodes();
	}

	private int countNodes() {
		int count = 0;
		Node<T> temp = front;
		while (temp != null) {
			count++;
			temp = temp.next;
		}
		return count;
	}

	public String toString() {
		if (front == null)
			return null;

		String str = "[" + front.toString();
		Node<T> temp = front;
		while (temp.next != null) {
			temp = temp.next;
			str += "," + temp.toString();
		}
		return str + "]";
	}
	
	
	public void addToFront(Node<T> newNode)
	{
		if(front == null)
		{
			front = newNode;
		}
		else
		{
			Node<T> temp = front;
			front = newNode;
			newNode.next = temp;
		}
		++numNodes;
	}
	
	public void removeHead()
	{
		if(front != null)
		{
			front = front.next;
			--numNodes;
		}
	}
	
	public T elementAt(int Index)
	{
		Node<T> temp = front;
		int counter = 0;
		while(temp !=null && counter < Index)
		{
			if(counter == Index)
			{
				return temp.data;
			}
			++counter;
			temp = temp.next;
		}
		return null;
	}
	
	public boolean contains(T data)
	{
		Node<T> temp = front;
		while(temp != null)
		{
			if(temp.data.equals(data))
			{
				return true;
			}
			temp = temp.next;
		}		
		return false;
	}
	
	public int indexOf(T data)
	{
		Node<T> temp = front;
		int counter = 0;
		while(temp != null)
		{
			if(temp.data.equals(data))
			{
				return counter;
			}
			++counter;
			temp = temp.next;
		}		
		return -1;
	}
	
	public void add(Node<T>newNode, T target)
	{
		if(front == null)
		{
			front = newNode;
		}
		else
		{
			Node<T> temp = front;
			front = newNode;
			newNode.next = temp;
		}
		++numNodes;
	}
	
	public void removeAfter(T target)
	{
		Node<T> temp = front;
		while(temp != null)
		{
			if(temp.data.equals(target))
			{
				temp = temp.next;
				--numNodes;
			}
			temp = temp.next;
		}
		front = temp;
	}
	
	public Node<T> last()
	{
		Node<T> temp = front;
		while(temp != null)
		{
			if(temp.next == null)
			{
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}
	
	public void add(Node<T>newNode)
	{
		Node<T> temp = front;
		boolean finished = false;
		while(temp != null)
		{
			if(temp.next == null && finished == false)
			{
				temp.next = newNode;
				++numNodes;
				finished = true;
			}
			temp = temp.next;
		}
	}
	
	public void removeLast()
	{
		Node<T> temp = front;
		while(temp != null)
		{
			if(temp.next == null)
			{
				temp = temp.next;
				--numNodes;
			}
			temp = temp.next;
		}
	}
	
	
	
	
}

