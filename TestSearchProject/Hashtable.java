package structures;

/**
 * HashTable class to store KeyValue Objects
 * 
 * @Howard Luo
 * @3/12
 * @4
 */
public class HashTable {

	private KeyValue[] table;
	private double loadFactor;
	private int numNodes;

	/**
	 * By default the size of the HashTable is 15
	 * 		with a load factor of 0.8 (80%)
	 * The load factor should be checked AFTER a new item is added.
	 * When the load of the HashTable exceeds the load factor, 
	 * 		the size of the HashTable should triple plus 1 and the
	 * 		load factor should increase by 10%.
	 */
	public HashTable(){
		table = new KeyValue[15];
		loadFactor = 0.8;
		numNodes = 0;
	}

	/**
	 * Given the index and the KeyValue, the KeyValue will be inserted
	 * 		into this HashTable at the correct index.  If the KeyValue
	 * 		already exists in the HashTable, the values of the KeyValue 
	 * 		passed by parameter will be appended to the values list of 
	 * 		the KeyValue object already stored in this HashTable.  If 
	 * 		the insert results in the HashTable exceeding the loadFactor, 
	 * 		the HashTable will expand by a factor of 3 plus 1, the loadFactor
	 * 		will increase by 10%, and all the data in the HashTable will be 
	 * 		rehashed accordingly. 
	 * 
	 * @param index is the HashTable index as calculated by the Indexer
	 * @param data a KeyValue object
	 * 
	 * POSTCONDITION: The load factor <= loadFactor, numNodes reflects the 
	 * 		number of KeyValue nodes that are in the HashTable
	 */
	public void insertKeyValue(int index, KeyValue data){
		/* Complete this method */
		if(table[index] == null)
		{
			table[index] = data;
			++numNodes;
		}
		else
		{
			boolean included = false;
			KeyValue temp = table[index];
			while(temp != null)
			{
				if(temp.equals(data))
				{
					while(data.getValues().size() > 0)
					{
						//temp.getValues().add(data.getValues().remove(0));
						temp.addValue(data.getValues().remove(0));
					}
					included = true;
				}
				temp = temp.next;
			}
			if(included == false)
			{
				data.next = table[index];
				table[index] = data;
				++numNodes;
			}
		}
		
		if((double)numNodes / table.length > loadFactor)
		{
			rehash();
		}
	}

	/**
	 * Given the index and a key return the KeyValue object from this
	 * 		HashTable. If there is no KeyValue object for the key passed
	 * 		by parameter, return null.
	 * @param index the index for key as determined by the Indexer
	 * @param key a String keyword that is being searched for
	 * @return the KeyValue object that holds the key passed by parameter,
	 * 		or null if this key is not stored as part of a KeyValue object
	 * 		in this HashTable.
	 */
	public KeyValue lookUpKey(int index, String key){
		/*  Complete this method */
		KeyValue temp = table[index];
		while(temp != null && !temp.getKey().equals(key))
		{
			temp = temp.next;
		}
		return temp;
		//return null; //quiets the compiler
	}
	
	/**
	 * A private helper method to resize the table and rehash all the values
	 * 		in the table.  When called, the HashTable will expand by a factor 
	 *      of 3  plus 1, the loadFactor will increase by 10% and all the data 
	 *      in the HashTable will be rehashed.
	 */
	private void rehash() {
		/*  Complete this method */
		loadFactor += 0.1;
		HashTable temp = new HashTable();
		if(3 * table.length + 1 > Integer.MAX_VALUE)
		{
			temp.table = new KeyValue[Integer.MAX_VALUE];
		}
		else
		{
			temp.table = new KeyValue[3 * table.length + 1];
		}
		for(int i = 0; i < table.length; i++)
		{
			KeyValue temp2 = table[i];
			while(temp2 != null)
			{
				KeyValue temp3 = new KeyValue(temp2.getKey());
				for(int j = 0; j < temp2.getValues().size(); j++)
				{
					temp3.addValue(temp2.getValues().get(j));
				}
				temp.insertKeyValue(Math.abs(temp2.hashCode()) % temp.table.length, temp3);
				temp2 = temp2.next;
			}
		}
		table = temp.table;
	}
	
	/**
	 * @return the current length of the HashTable to be used
	 * 		by the Indexer.
	 */
	public int size() {
		return table.length;
	}

	/**
	 * @return a String representation of this HashTable
	 */
	public String toString() {
		String tableString = new String();
		for(int index = 0; index < table.length; index++) {
			if(table[index] != null) {
				tableString += String.format("[%03d] -> ", index);
				KeyValue ptr = table[index];
				for(; ptr.next != null; ptr = ptr.next) {
					tableString += ptr.toString() + ", ";
				}
				tableString += ptr.toString() + "\n";
			}
		}
		tableString += String.format("\nLoad Factor: %.2f%c NumNodes: %d\n", loadFactor, '%', numNodes);
		return tableString;
	}
}
