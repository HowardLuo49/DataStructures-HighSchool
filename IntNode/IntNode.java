
/**
 * Problem Set 2 Writing methods for an IntNode class
 * 
 * @Howard Luo
 * @11/8/20
 * @4
 */
public class IntNode {
	public int data;
	public IntNode next;

	public IntNode() {
		this.data = 0;
		this.next = null;
	}

	public IntNode(int data, IntNode next) {
		this.data = data;
		this.next = next;
	}

	public String toString() {
		if (next != null)
			return data + " " + next.toString();
		return data + "";
	}

	/**
	 * #1 Implement the addBefore method such that newItem will be added before
	 * target in the list starting with front. If the target does not exist,
	 * addBefore should return the original list, unchanged.
	 * 
	 * @param front,
	 *            the first node in the linked list
	 * @param target
	 * @param newItem
	 *            item to be inserted
	 * @return the front node of the updated linked list
	 */
	public static IntNode addBefore(IntNode front, int target, int newItem) {
		/* COMPLETE THIS METHOD */

		if (front == null)
		{
			return front;
		}

		if (front.data == target)
		{
			IntNode newNode = new IntNode(front.data, front.next);
			front = new IntNode(newItem, newNode);
			return front;
		}

		IntNode temp = front;

		while (temp.next != null)
		{
			if (temp.next.data == target)
			{
				IntNode newNode = new IntNode(newItem, temp.next);
				temp.next = newNode;
				return front;
			}
			temp = temp.next;
		}
		return front; // quiets the compiler
	}

	/**
	 * #2 Implement the addBeforeLast method such that newItem will be added just
	 * before the last item in the linked list. If the initial list is empty
	 * addBeforeLast should return null, returning the original list, unchanged.
	 * 
	 * @param front,
	 *            the first node in the linked list
	 * @param newItem
	 * @return the front node of the updated linked list
	 */
	public static IntNode addBeforeLast(IntNode front, int newItem) {
		/* COMPLETE THIS METHOD */

		if (front == null)
		{
			return front;
		}

		if (front.next == null)
		{
			IntNode newNode = new IntNode(front.data, front.next);
			front = new IntNode(newItem, newNode);
			return front;
		}

		IntNode temp = front;

		while (temp.next != null)
		{
			if (temp.next.next == null)
			{
				IntNode newNode = new IntNode(newItem, temp.next);
				temp.next = newNode;
				return front;
			}
			temp = temp.next;
		}
		return front;

	}

	/**
	 * #3 Implement the method numberOfOccurrances that will search a given linked
	 * list for a target int, and return the number of occurrences of the target
	 * 
	 * @param front,
	 *            the first node in the linked list
	 * @param target
	 * @return the number of occurrences of the target
	 */
	public static int numberOfOccurrences(IntNode front, int target) {
		/* COMPLETE THIS METHOD */

		if (front == null)
		{
			return 0;
		}

		int counter = 0;
		IntNode temp = front;

		while (temp != null)
		{
			if (temp.data == target)
			{
				++counter;
			}
			temp = temp.next;
		}
		return counter;

	}

	/**
	 * #4 Implement the method deleteEveryOther to delete EVERY OTHER item from an
	 * integer linked list. For example: before: 3->9->12->15->21 after: 3->12->21
	 * 
	 * before: 3->9->12->15 after: 3->12
	 *
	 * before: 3->9 after: 3
	 * 
	 * before: 3 after: 3
	 * 
	 * If the list is empty, the method should do nothing.
	 * 
	 * @param front,
	 *            the first node in the linked list
	 */
	public static void deleteEveryOther(IntNode front) {
		/* COMPLETE THIS METHOD */

		if (front != null)
		{
			IntNode temp = front;
			while (temp != null)
			{
				if (temp.next == null)
				{
					temp = temp.next;
				}
				else
				{
					temp.next = temp.next.next;
					temp = temp.next;
				}
			}
		}

	}

	/**
	 * #5 Implement the method deleteAllOccurrences that will delete all occurrences
	 * of a given target int from a linked list, and return a pointer to the first
	 * node of the resulting linked list.
	 * 
	 * @param front,
	 *            the first node in the linked list
	 * @param target
	 * @return the front node of the updated linked list
	 */
	public static IntNode deleteAllOccurrences(IntNode front, int target) {
		/* COMPLETE THIS METHOD */

		if (front != null)
		{
			if (front.data == target && front.next == null)
			{
				front = null;
				return front;
			}
			while (front.data == target)
			{
				if(front.next == null)
				{
					front = null;
					return front;
				}
				front = front.next;
			}
			IntNode temp = front;
			while (temp != null)
			{
				if (temp.next != null && temp.next.data == target)
				{
					temp.next = temp.next.next;
				}
				else
				{
					temp = temp.next;
				}
			}
		}
		return front;

	}

	/** #6
       * Implement the method commonElements to find the common elements 
       * in two SORTED linked lists, and return the common elements in 
       * sorted order in a NEW linked list. The original linked lists 
       * should not be modified. 
       * For instance:
       *  	l1 = 3->9->12->15->21
       *  	l2 = 2->3->6->12->19
       *  should produce a new linked list:
       *  	3->12
       *  
       * You may assume that the original lists do not have any duplicate items.
       * Return null if no common elements exist.
       * 
       * @param frontL1, the first node in the linked list 1
       * @param frontL2, the first node in the linked list 2
       * @return A reference to the front node of a new linked list
       * 	which holds the common elements of L1 and L2 in sorted order.
       * 	Or null if no common elements exist.
       */
      public static IntNode commonElements(IntNode frontL1, IntNode frontL2) {
    	  /* COMPLETE THIS METHOD */
    	      	 
    	  IntNode temp1 = frontL1;
    	  IntNode temp2 = frontL2;
    	  IntNode front = null;
    	  IntNode temp = null;
    	  while(temp1 != null && temp2 != null)
    	  {
    	      if (temp1.data < temp2.data)
    	      {
    	    	  temp1 = temp1.next;
    	      }
    	      else if (temp1.data > temp2.data)
    	      {
    	    	  temp2 = temp2.next;
    	      }
    	      else
    	      {
    	    	  if(front == null)
    			  {
    				  temp = new IntNode(temp1.data, null);
    				  front = temp;
    			  }
    			  else
    			  {
    				  temp.next = new IntNode(temp1.data, null);
    				  temp = temp.next;
    			  }
    			  if(temp1.next != null)
    			  {
    				  temp1 = temp1.next;
    			  }
    			  else if (temp2.next != null)
    			  {
    				  temp2 = temp2.next;
    			  }
    			  else if(temp1.next == null && temp2.next == null)
    			  {
    				  temp1 = temp1.next;
        	    	  temp2 = temp2.next;
    			  }
    	      }
    	  }
    	  return front;
    	  
      }
}
