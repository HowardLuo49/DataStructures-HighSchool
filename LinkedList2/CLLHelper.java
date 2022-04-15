package Circular;
/**
 * Unit 2 Linked List Coding Task
 * 	Complete the two methods below. Submit ONLY this file.
 * 
 * @Howard Luo
 * @4
 * @11/13/20
 */
public class CLLHelper {
	
	/**
	 * Deletes all nodes with data that is greater than target 
	 * 		from a CLL as referenced by tail. 
	 * @param target, a Comparable dataType value, not null
	 * @param tail, the tail Node of a circular linked list
	 * @return the tail reference to a circular linked list with 
	 * 			all Nodes with data of less or equal value to target, or null
	 */
	public static <T extends Comparable<T>> 
		Node<T> removeGreaterThan(T target, Node<T> tail) {
		/* your code goes here */
		
		
		
		Node<T> temp = tail;
		Node<T> prev = null;
		
		prev = temp;
		temp = temp.next;
		


		while (temp != tail)
		{
			if(temp.data.compareTo(target) > 0)
			{
				prev.next = temp.next;
				temp = prev.next;
				if(prev.next == prev)
				{
					return null;
				}
			}
			else
			{
				prev = temp;
				temp = temp.next;
			}
		}
		if(temp.data.compareTo(target) > 0)
		{
			prev.next = temp.next;
			tail = prev;
		}
		else
		{
			tail = temp;
			return temp;
		}
		return tail;
	}

	/** 
	 * Deletes the last occurrence of item from a CLL
	 * @param item to be removed, is not null.
	 * @param rear, a reference to the last node
	 * @return a reference to the rear of the resulting CLL
	 * If item is not in the list, return the original CLL
	 */
	public static <T extends Comparable<T>> 
		Node<T> deleteLast(T item, Node<T> rear) {
		/* your code goes here */
		
		Node<T> temp = rear;
		Node<T> prev = null;
		
		prev = temp;
		temp = temp.next;
		
		int counter = 0;
		
		if(rear.data.compareTo(item) == 0)
		{
			++counter;
		}
		while(temp != rear)
		{
			if(temp.data.compareTo(item) == 0)
			{
				++counter;
			}
			prev = temp;
			temp = temp.next;
		}
		
		prev = temp;
		temp = temp.next;
		
		if(counter == 0)
		{
			return rear;
		}
		
		else
		{
			while(counter != 0)
			{
				if(temp.data.compareTo(item) == 0)
				{
					if(counter == 1)
					{
						if(temp == rear)
						{
							rear = prev;
						}
						prev.next = temp.next;
						temp = prev.next;
						
					}
					--counter;
				}
				prev = temp;
				temp = temp.next;
			}
			
			return rear;
		}
		
	}
}
