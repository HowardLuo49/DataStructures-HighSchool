package structures;

/**
 * A Binary Search Tree of WordNodes.
 * 
 * @author Howard Luo
 * @date 2/6/21
 * @period 4
 */
public class WordTree {
	
	WordNode root;
	int numWords;
	
	public WordTree(){
		root = null;
		numWords = 0;
	}
	
	/**
	 * If word doesn't exist in this word tree, it should be inserted in
	 * 		the appropriate place based on Binary Search.  If words already
	 * 		exists in this tree, the quantity should be updated to reflect 
	 * 		the appropriate number of times word appears in the text
	 * @param word a word to be inserted into this Binary Search Tree
	 * @return the quantity, or number of times that word has been added to the list
	 */
	public int insertWord(String word) {
		//complete this method
		if(root == null)
		{
			root = new WordNode(word, null, null);
			++numWords;
			return root.getQuantity();
		}
		else
		{
			return insertHelper(word, root);
		}
		
		//return 0; //quiets the compiler
	}
	
	private int insertHelper(String word, WordNode temp)
	{
		if(word.compareTo(temp.word) == 0)
		{
			temp.updateQuantity();
			return temp.getQuantity();
		}
		else if(word.compareTo(temp.word) < 0)
		{
			if(temp.left == null)
			{
				temp.left = new WordNode(word, null, null);
				++numWords;
				return 1;
			}
			return insertHelper(word, temp.left);
		}
		else if(word.compareTo(temp.word) > 0)
		{
			if(temp.right == null)
			{
				temp.right = new WordNode(word, null, null);
				++numWords;
				return 1;
			}
			return insertHelper(word, temp.right);
		}
		return temp.getQuantity();
		
		//return 0; //compiler quieter
	}
	
	/**
	 * Performs a search in this word tree for the word.  Determines and returns
	 *      the depth of the word in the tree.  The root is at depth 0, a child of
	 *      the root is at depth 1, etc... If the word is not in this word tree, -1
	 *      is returned to reflect that the word does not exist in the tree.
	 * 
	 * @param word a word to be searched for in this tree
	 * @return the depth of the word in the tree, or -1 if word is not found.
	 */
	public int wordSearch(String word) {
		//complete this method
		
		int depth = 0;
		WordNode temp = root;
		if(temp == null)
		{
			return -1;
		}
		while(word.compareTo(temp.word) != 0)
		{
			if(temp.left == null && temp.right == null)
			{
				return -1;
			}
			if(word.compareTo(temp.word) < 0)
			{
				++depth;
				temp = temp.left;
			}
			else if(word.compareTo(temp.word) > 0)
			{
				++depth;
				temp = temp.right;
			}
			if(temp == null)
			{
				return -1;
			}
		}
		return depth;
		
		//return -1; //quiets the compiler
	}

}
