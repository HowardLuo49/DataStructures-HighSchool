
public class BTNode <T> {
	T data;
	BTNode<T> left;
	BTNode<T> right;
	
	
	BTNode (T data, BTNode<T> left, BTNode<T> right)
	{
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public int getHeight()
	{
		if(left == null && right == null)
		{
			return 0;
		}
		if(left == null)
		{
			return 1 + right.getHeight();
		}
		if(right == null)
		{
			return 1+left.getHeight();
		}
		return 1 + Math.max(right.getHeight(), left.getHeight());
	}
}
