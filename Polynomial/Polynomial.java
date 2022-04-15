package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @Howard Luo
 * @12/6/20
 * @4
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextDouble(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** Your code goes here **/
		
		Node front = new Node(0, 0, null);
		Node poly = front;
		Node temp1 = poly1;
		Node temp2 = poly2;
		
		if(temp1 == null)
		{
			if(temp2 == null)
			{
				return null;
			}
			return poly2;
		}
		else if(temp2 == null)
		{
			return poly1;
		}
		
		while(temp1 != null && temp2 != null)
		{
			if(temp1.term.degree < temp2.term.degree)
			{
				if(temp1.term.coeff != 0)
				{
					poly.term.degree = temp1.term.degree;
					poly.term.coeff = temp1.term.coeff;
					poly.next = new Node(0, 0, null);
					poly = poly.next;
				}				
				temp1 = temp1.next;
			}
			else if(temp1.term.degree > temp2.term.degree)
			{
				if(temp2.term.coeff != 0)
				{
					poly.term.degree = temp2.term.degree;
					poly.term.coeff = temp2.term.coeff;
					poly.next = new Node(0, 0, null);
					poly = poly.next;
				}
				temp2 = temp2.next;
			}
			else if(temp1.term.degree == temp2.term.degree)
			{
				if(temp1.term.coeff + temp2.term.coeff != 0)
				{
					poly.term.degree = temp1.term.degree;
					poly.term.coeff = temp1.term.coeff + temp2.term.coeff;
					poly.next = new Node(0, 0, null);
					poly = poly.next;
				}
				temp1 = temp1.next;
				temp2 = temp2.next;
			}
		}
		
		if(temp1 == null)
		{
			while(temp2 != null)
			{
				if(temp2.term.coeff != 0)
				{
					poly.term.degree = temp2.term.degree;
					poly.term.coeff = temp2.term.coeff;
					if(temp2.next != null)
					{
						poly.next = new Node(0, 0, null);
						poly = poly.next;
					}
				}
				temp2 = temp2.next;
			}
		}
		else if (temp2 == null)
		{
			while(temp1 != null)
			{
				if(temp1.term.coeff != 0)
				{
					poly.term.degree = temp1.term.degree;
					poly.term.coeff = temp1.term.coeff;
					if(temp1.next != null)
					{
						poly.next = new Node(0, 0, null);
						poly = poly.next;
					}
				}				
				temp1 = temp1.next;
			}
		}
		
		if(front.term.coeff == 0 && front.term.degree == 0)
		{
			return null;
		}
		
		Node check = front;
		while(check.term.coeff == 0)
		{
			front = front.next;
			check = poly;
		}
		while(check.next != null)
		{
			if(check.next.term.coeff == 0)
			{
				check.next = check.next.next;
			}
			if(check.next != null)
			{
				check = check.next;
			}
		}
		
		return front;
		//return null; //Quiets the Compiler
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** Your code goes here **/
		
		Node poly = new Node(0, 0, null);
		Node temp1 = poly1;
		Node temp2 = poly2;
		
		if(poly1 == null || poly2 == null)
		{
			return null;
		}
		
		while(temp1 != null)
		{
			while(temp2 != null)
			{
				Node hold = new Node (temp1.term.coeff * temp2.term.coeff, temp1.term.degree + temp2.term.degree, null);
				poly = insert(poly, hold);
				temp2 = temp2.next;
			}
			temp1 = temp1.next;
			temp2 = poly2;
		}
		
		
		if(poly.term.coeff == 0 && poly.term.degree == 0)
		{
			return null;
		}
		
		Node check = poly;
		while(check.term.coeff == 0)
		{
			poly = poly.next;
			check = poly;
		}
		while(check.next != null)
		{
			if(check.next.term.coeff == 0)
			{
				check.next = check.next.next;
			}
			if(check.next != null)
			{
				check = check.next;
			}
		}
		
		return poly;
		//return null; //Quiets the compiler
	}

	private static Node insert(Node poly, Node hold) {
		// TODO Auto-generated method stub
		Node temp = poly;
		
		while(temp.next != null && temp.next.term.degree < hold.term.degree)
		{
			temp = temp.next;
		}
		
		if(temp.next == null)
		{
			temp.next = hold;
		}
		else if(temp.next.term.degree == hold.term.degree)
		{
			temp.next.term.coeff = temp.next.term.coeff + hold.term.coeff;
		}
		else
		{
			hold.next = temp.next;
			temp.next = hold;
		}
		if(poly.term.coeff == 0)
		{
			poly = poly.next;
		}
		return poly;
	}

	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static double evaluate(Node poly, double x) {
		/** Your code goes here **/
		
		Node temp = poly;
		double sum = 0;
		while(temp != null)
		{
			if(temp.term.degree == 0)
			{
				sum += temp.term.coeff;
				temp = temp.next;
			}
			else
			{
				double product = x;
				for (int i = 0; i <temp.term.degree-1; i++)
				{
					product *= x;
				}
				sum += temp.term.coeff * product;
				temp = temp.next;
			}
		}
		
		return sum;
		//return 0; //Quiets the compiler
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
