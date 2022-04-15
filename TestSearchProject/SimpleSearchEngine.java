package application;
import structures.*;
import java.io.*;
import java.util.*;

public class SimpleSearchEngine {
	static Scanner s1 = new Scanner(System.in);
	
	static final int BUILD = 1;
	static final int SEARCH = 2;
	static final int PRINT = 3;
	static final int QUIT = 4;

	static Indexer indexer = null;

	public static void main(String[] args) throws IOException, NoMoreTokensException{
		int choice = getChoice();
		while (choice != QUIT) {
			if (choice < 1 || choice > QUIT) {
				System.out.println("\tIncorrect choice " + choice);
			} else {
				switch (choice) {
				case BUILD: buildInvertedIndex(); break;
				case SEARCH: locate(); break;
				case PRINT: printIndexer(); break;
				default: break;
				}
			}
			choice = getChoice();
		}
	}

	public static void buildInvertedIndex() throws IOException, NoMoreTokensException{	
		System.out.print("Enter the name of the text file to build the InvertedIndex => ");
		indexer = new Indexer(s1.nextLine());
	}

	public static void locate() throws IOException, NoMoreTokensException{
		if(indexer == null) {
			System.out.println("No InvertedIndex to be Searched");
			return;
		}
		System.out.print("Search InvertedIndex => ");
		System.out.println(SimpleSearch.query(s1.nextLine(), indexer));		
	}
	
	public static void printIndexer() {
		if(indexer == null)
			System.out.println("No Inverted Index created");
		else
			System.out.println(indexer);
	}

	public static int getChoice() 
			throws IOException {
		System.out.println();
		System.out.println(BUILD + ". BUILD a new InvertedIndex from a text file");
		System.out.println(SEARCH + ". SEARCH InvertedIndex");
		System.out.println(PRINT + ". PRINT InvertedIndex representation");
		System.out.println(QUIT + ". QUIT");
		System.out.print("\tEnter choice # => ");
		return (Integer.parseInt(s1.nextLine()));
	}
}



