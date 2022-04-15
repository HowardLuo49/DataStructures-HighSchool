import java.io.IOException;
import java.util.Scanner;

public class GraphTest {

	Neighbor[] vertexList;

	public void main(String[] args) 
			throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter graph input file name: ");
		String file = sc.nextLine();
		Graph graph = new Graph(file);
		
		numberIslands();
	}
	
	public int[] numberIslands() {
		int[] result = new int[vertexList.length];
		////Instead of creating an array to see what nodes are visited or not, I use the result array.
		//If the value is the zero default, that means it has not been visited yet.
		//Therefore, my first island number is 1 not 0.
		//At the end I remove 1 from every island number to compensate for this fact.
		
		int islandNumber = 1;
		
		for(int index = 0; vertexList.length > index; index++)
		{
			recurse(index, result, islandNumber);
			++islandNumber;
		}

		for (int i = 0; i < result.length; i++) {
			--result[i];
		}

		return result;
	}

	public void recurse(int n, int[] r, int t) {
		r[n] = t;
		Neighbor temp = vertexList[n];
		while (temp != null){
			if (r[n] == 0) {
				recurse(temp.vertexNum, r, t);
			}
			temp = temp.next;
		}
	}

	public void dumb()
	{
		return;
	}
	
	//break
	
	public void dfs() {
		 boolean[] visited = new boolean[vertexList.length];
		 for (int v=0; v < visited.length; v++) {
			 visited[v] = false;
		 }
		 for (int v=0; v < visited.length; v++) {
			 if (!visited[v]) {
				 dfs(v, visited);
			 }
		 }
	 }
	
	// recursive dfs
	private void dfs(int v, boolean[] visited) {
		visited[v] = true;
		for (Neighbor e=vertexList[v].next; e != null; e=e.next) {
			if (!visited[e.vertexNum]) {
				dfs(e.vertexNum, visited);
			}
		}
	}
}
