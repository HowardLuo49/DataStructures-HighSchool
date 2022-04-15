package structures;

import java.util.ArrayList;
/**
 * SimpleSearch class to search an inverted index
 * 
 * @name Howard Luo
 * @date 3/26/21
 * @period 4
 */
public class SimpleSearch {
	
	/**
	 * Searches the InvertedIndex indexer for any and all keywords found in 
	 * 		the String query and returns an ArrayList of all subjects 
	 * 		related to the keywords in the String query. 
	 * If the query results in a failed search return null.
	 * @param query a String that includes one or more key words.
	 * @param indexer an Inverted Indexer object to be searched
	 * @return an ArrayList of values or "subjects" that relate to any and all 
	 * 		of the "key" words in the String query. Or null if the key words in
	 * 		query result in a failed search. ie. key NOT in the InvertedIndex
	 * PRECONDTION: query may include upper and lower case letter, period, 
	 * 		comma, semicolon, colon, apostrophe, question mark, exclamation, 
	 * 		and spaces. But no other characters.
	 * POSTCONDITION: the ArrayList returned by the method query does NOT
	 * 		contain any duplicate values.
	 */
    public static ArrayList<String> query(String query, Indexer indexer) 
    		throws NoMoreTokensException{
    	/*  Complete this method */
    	
    	ArrayList<String> result = new ArrayList<>();
    	
    	Tokenizer temp = new Tokenizer(" .,;:'!?", query);
    	
    	while(temp.hasToken())
    	{
    		String temporary = temp.nextToken();
    		while(indexer.getKeyValue(temporary.toLowerCase()) == null)
    		{
    			if(!temp.hasToken())
        		{
        			return null;
        		}
    			temporary = temp.nextToken();
    		}
    		
    		for(String s : indexer.getKeyValue(temporary.toLowerCase()).getValues())
    		{
    			boolean contains = false;
    			for(String str : result)
    			{
    				if(str.equalsIgnoreCase(s))
    				{
    					contains = true;
    				}
    			}
    			if(contains == false)
    			{
    				result.add(s);
    			}
    		}
    	}
    	return result;
    	
        //return null;//Quiets the compiler
    }

}
