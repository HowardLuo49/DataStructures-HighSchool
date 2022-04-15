/*
 * Howard Luo
 * P4
 * 1/13/21
 */

import java.util.*;


public class BTNodeClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//test code for my own purposes
		BTNodeClient client = new BTNodeClient();
		BTNode<String> result = client.buildTree("6+3*6/2+2*5*3*2");
		System.out.println(client.toInfix(result));
		System.out.println(client.toPrefix(result));
		System.out.println(client.toPostfix(result));
		System.out.println(client.evaluate(result));
	}
	
	public BTNode<String> buildTree(String infix)
	{
		if(!infix.contains("+") && !infix.contains("-") && !infix.contains("*") && !infix.contains("/") && !infix.contains("%"))
		{
			return new BTNode(infix, null, null);
		}
		String lowOperator = "+-";
		String highOperator = "*/%";
		int elementIndex = infix.length() - 1;
		if(!infix.contains("+") && !infix.contains("-"))
		{
			while(!highOperator.contains(infix.substring(elementIndex, elementIndex + 1)))
			{
				--elementIndex;
			}
		}
		else
		{
			while(!lowOperator.contains(infix.substring(elementIndex, elementIndex + 1)) && elementIndex != 0)
			{
				--elementIndex;
			}
		}
		if(elementIndex == 0)
		{
			++elementIndex;
		}
		String element = infix.substring(elementIndex, elementIndex+1);
		return new BTNode(element, buildTree(infix.substring(0, elementIndex)), buildTree(infix.substring(elementIndex+1)));
	}	
	
	
	
	public String toInfix(BTNode<String> tree)
	{
		String initial = "";
		BTNode<String> temp = tree;
		
		String result = recurse1(tree, initial);
		
		return result;
	}
	private String recurse1(BTNode node, String infix)
	{
		if(node == null)
			return "";
		else
		{
			String s1 = recurse1(node.left, infix);
			String s2 = recurse1(node.right, infix);
			infix = s1 + node.data + s2;
		}
		return infix;
	}
	
	public String toPrefix(BTNode<String> tree)
	{
		String initial = "";
		BTNode<String> temp = tree;
		
		String result = recurse2(tree, initial);
		
		return result;
	}
	private String recurse2(BTNode node, String infix)
	{
		if(node == null)
			return "";
		else
		{
			String s1 = recurse2(node.left, infix);
			String s2 = recurse2(node.right, infix);
			infix = node.data + s1 + s2;
		}
		return infix;
	}
	
	public String toPostfix(BTNode<String> tree)
	{
		String initial = "";
		BTNode<String> temp = tree;
		
		String result = recurse3(temp, initial);
		
		return result;
	}
	private String recurse3(BTNode node, String infix)
	{
		if(node == null)
			return "";
		else
		{
			String s1 = recurse3(node.left, infix);
			String s2 = recurse3(node.right, infix);
			infix = s1 + s2 + node.data;
		}
		return infix;
	}
	
	
	
	public double evaluate(BTNode<String> tree)
	{
		double initial = 0.0;
		BTNode<String> temp = tree;
		
		double result = recurseEval(temp, initial);
		
		return result;
	}
	
	private double recurseEval(BTNode<String> node, double number)
	{
		if(node == null)
			return 0.0;
		
		if(node.data.equals("+"))
			number = recurseEval(node.left, number) + recurseEval(node.right, number);
		else if(node.data.equals("-"))
			number = recurseEval(node.left, number) - recurseEval(node.right, number);
		else if(node.data.equals("*"))
			number = recurseEval(node.left, number) * recurseEval(node.right, number);
		else if(node.data.equals("/"))
			number = recurseEval(node.left, number) / recurseEval(node.right, number);
		else if(node.data.equals("%"))
			number = recurseEval(node.left, number) % recurseEval(node.right, number);
		else
			return number += Double.parseDouble(node.data);
		return number;
	}
	
	
}

