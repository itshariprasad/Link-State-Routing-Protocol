public class djikstras
{
	
	
	public static Object dijkstra(int[][] matrix, int src , int dest ,int choice) 
	{
		int matrix_index = src;
		int count = 0 ;
		int[] path_length = matrix[src].clone();		
		boolean[] tof = new boolean[matrix[0].length];
		int[] N = new int[matrix[0].length];
		N[count] = matrix_index;
		
		
		
		int[] pIndex = new int[matrix[0].length];
		for (int i = 0; i < pIndex.length; i++) 
		{
			pIndex[i] = src;
		}
		
		
		tof[matrix_index] = true;
		while (count < matrix[0].length) 
		{
			
			int min = Integer.MAX_VALUE;
			
			for (int i = 0; i < path_length.length; i++) {
				if (!tof[i] && path_length[i] != -1 && i != matrix_index) {
			
					if (path_length[i] < min) {
						min = path_length[i];
						matrix_index = i;
					}
				}
			}
			
			tof[matrix_index] = true;
			count++;
			N[count] = matrix_index;
			
			for (int i = 0; i < path_length.length; i++) {
				
				if (path_length[i] == -1 && matrix[matrix_index][i] != -1 && ! tof[i]) 
				{
					path_length[i] = path_length[matrix_index] + matrix[matrix_index][i];
					pIndex[i] = matrix_index;
				}
				else if (matrix[matrix_index][i] != -1 && path_length[matrix_index] + matrix[matrix_index][i] < path_length[i])
				{
					
					path_length[i] = path_length[matrix_index] + matrix[matrix_index][i];
					pIndex[i] = matrix_index;
				}
			}

			if (matrix_index == dest) 
			{
				break;
			}
		}

		if(choice==1)
			return routernextHop(pIndex, src, dest, path_length.length);
		
		if(choice==2)
		{
		System.out.print("The shortest path from router " + (src + 1) + " to router " + (dest + 1) + " is --> ");
		display_LowestCost(pIndex, src, dest, path_length.length);
		System.out.println();
		System.out.println(" . The total cost is "+ (path_length[dest] - path_length[src]) + ".");

		return path_length[dest];
		}
		else return null;
		
	}
	
	
public static String routernextHop(int[] pIndex, int src, int dest, int len)
{
	int[] choosen_route = new int[len];
	int i = dest;
	choosen_route[0] = i;
	int k = 1;
	
	while (pIndex[i] != src)
	{
		i = pIndex[i];
		choosen_route[k] = i;
		k++;
	}
	choosen_route[k] = src;
	int h=0;
	for (int j = k; j > 0; j--)
	{
		
		h++;
		if(h==2)
			return "   | Router "+(choosen_route[j]+1);
	}

	 if (h==1)
		{
		dest += 1; 
		return "   | Router "+ dest ;
		}
	 return null;
}
	
	public static void display_LowestCost(int[] pathIndex, int src, int dest, int len)
	{
		int[] choosen_route = new int[len];
		int i = dest;
		choosen_route[0] = i;
		int k = 1;
		while (pathIndex[i] != src) 
		{
			i = pathIndex[i];
			choosen_route[k] = i;
			k++;
		}
		choosen_route[k] = src;
		for (int j = k; j > 0; j--)
		{
			System.out.print("Router " + (choosen_route[j] + 1) + " to ");
			
		}
		System.out.print("Router " + (choosen_route[0] + 1));
	}
	
}