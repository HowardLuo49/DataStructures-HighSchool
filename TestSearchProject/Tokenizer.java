package structures;
import java.util.ArrayList;
/**
 * A String Tokenizer class
 * 
 * @Howard Luo
 * @3/12
 * @4
 */
public class Tokenizer {
	private ArrayList<String> tokens;

    /**
     * Constructor for the Tokenizer object.  A tokenizer takes in a String and
     * 	delimiters and splits the String into tokens based on the demlimiters.
     * The individual tokens should be stored in the tokens arraylist in the 
     * 	order for which they exist in the String passed by parameter.
     * @param delims a String of character for which string should be split
     * @param string the String to be split into tokens
     */
	public Tokenizer(String delims, String string){
		/* Complete this method */
		tokens = new ArrayList<String>();
		int index1 = 0;
		boolean found = false;
		
		
		String temp = string;
		while(temp.length() > 0)
		{
			while(index1 < temp.length() && !found)
			{
				if(delims.contains(temp.substring(index1, index1 + 1)))
				{
					found = true;
				}
				++index1;
			}
			if(index1 != temp.length())
			{
				if(!temp.substring(0, index1-1).equals(""))
				{
					tokens.add(temp.substring(0, index1-1));
				}
			}
			if(index1 >= temp.length())
			{
				if(found == true)
				{
					if(!temp.substring(0, index1-1).equals(""))
					{
						tokens.add(temp.substring(0, index1-1));
					}
					temp = "";
				}
				else
				{
					if(!temp.equals(""))
					{
						tokens.add(temp);
					}
					temp = "";
				}
			}
			else
			{
				temp = temp.substring(index1);
				index1 = 0;
				found = false;
			}
		}
    }
	
    /**
	 * Returns one word at a time from the String that was split
	 * 		into tokens
	 * @return the first token from the tokens list or 
	 * 			null if the list is empty
	 */
    public String nextToken() throws NoMoreTokensException{
    	if(this.hasToken())
    		return tokens.remove(0);
    	throw new NoMoreTokensException();
    }
    
    /**
     * @return true if more tokens exist
     */
    public boolean hasToken() 
    {	return tokens.size() > 0;  }
}

