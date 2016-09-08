
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;


public class Shortestpath_impl 
{
	protected static int in;
	protected static int inp;
	static djikstras obj1 = new djikstras();
	
	public static int[][] obtainFile() throws IOException, Exception 
	{
		System.out.println("Please provide correct txt file present in the directory");
		System.out.println("Enter file name-->");
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		int matrixrow = 0;
		int matrixcolumn = 0;
		String line[]=null;
		try{
			
		FileReader fileReader = new FileReader(str);
		BufferedReader br =  new BufferedReader(fileReader);
		
		String row_val ;
		System.out.println("The given input matrix is ");
		
			while ((row_val= br.readLine()) != null)
			{
				matrixrow++;
				line = row_val.split("[\\s]+");
				matrixcolumn= line.length;
				
			}
			int matrix[][]= new int[matrixrow][matrixcolumn];
		br.close();
		}
		
		catch (FileNotFoundException fnfe)
		{
		 System.out.println("File was not found!");
		 main(null);
		}
		catch (IOException ioe)
		{
		 System.out.println("Error while reading the file. Possible wrong type");
		 main(null);
		} 
		
		return store(str,matrixcolumn);
	
	}

	public static int[][] store(String s,int column) throws IOException
	{
		int rows=0;
		String row_val1;
		int matrix[][] = new int[column][column];
		FileReader fileReader = new FileReader(s);
		BufferedReader br1 =  new BufferedReader(fileReader);
		
			while ((row_val1 = br1.readLine()) != null)
			{
				String line[] = row_val1.split("[\\s]+");

				for (int i = 0; i < column; i++) 
				{
					matrix[rows][i] = Integer.parseInt(line[i]);
				}
				rows++;
			}
		
		
			br1.close();
		    
			return matrix;	
		
	}

	

	public static void compute_table_all(int matrix[][]) throws Exception
	{
		/*
		System.out.println("Select a source router: ");
		Scanner sc = new Scanner(System.in);
		int in =sc.nextInt();
		
		*/
		
		for(int n=1; n<=matrix.length; n++){
			
		
		int len = matrix.length;
		
		Object comp_arr[][]=new Object[1][3];
		System.out.println("Router "+n +" Connection Table");
		System.out.println("Source          | Destination      | Interface");
		
		System.out.println("===============================================");
		
		System.out.println("Router "+n+"        |   Router "+n+"       |  -");
		
		try{
			for(int i=0; i<=len; i++)
				{
				if(i==(n-1))
				  { 
					i++;
				  }
				   
					comp_arr[0][0]="Router "+(n);
					comp_arr[0][1]="|   Router "+(i+1);
					
					comp_arr[0][2]= obj1.dijkstra(matrix,n-1,i,1);
					displaymatrix1(comp_arr);
					
				}
	           
			System.out.println("");
			
		}
		catch(Exception e)
		{
			System.out.println();
		}
	}
	
	}

	

	public static void compute_Table(int matrix[][], int in) throws Exception
	{
		/*
		System.out.println("Select a source router: ");
		Scanner sc = new Scanner(System.in);
		int in =sc.nextInt();
		
		*/
		
		int temp = matrix[0].length;
		if(in<=0 || in>temp){
			System.out.println("Given router doesn't exists from the input matrix");
			main(null);
		}
		
		int len = matrix.length;
		
		Object comp_arr[][]=new Object[1][3];
		System.out.println("Router "+in +" Connection Table");
		System.out.println("Source          | Destination      | Interface");
		
		System.out.println("===============================================");
		
		System.out.println("Router "+in+"        |   Router "+in+"       |  -");
		try{
			for(int i=0; i<=len; i++)
				{
				if(i==(in-1))
				  { 
					i++;
				  }
				   
					comp_arr[0][0]="Router "+(in);
					comp_arr[0][1]="|   Router "+(i+1);
					
					comp_arr[0][2]= obj1.dijkstra(matrix,in-1,i,1);
					displaymatrix1(comp_arr);
					
				}
	           
			System.out.println("");
			
		}
		catch(Exception e)
		{
			System.out.println();
		}
	}
		

	public static void dijkstra_1 (int matrix[][], int src) throws IOException, Exception
	{
		
		
		int srce = src;
		System.out.println("Enter the destination router");
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String[] dest_arr = line.split(" ");
		
		int dest = Integer.parseInt(dest_arr[0]) - 1;
		int temp = matrix[0].length;
		
		if(dest>temp || dest<0){
			System.out.println("Given destination router is wrong! Back to choice");
			main(null);
		}
		else if(dest+1==srce){
			System.out.println("Both source and destination same! Back to choice");
			main(null);
		}
		
		obj1.dijkstra(matrix,src-1,dest,2);
				
	}
	

	public static void displaymatrix(int matrix[][])
	{
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix.length;j++)
			{
				System.out.print(matrix[i][j] + "	");
			}
			System.out.println();
		}
	}
	
	
	public static void displaymatrix1(Object arr_mat[][])
	{
		for(int i=0; i<arr_mat.length; i++)
		{
			for(int j=0; j<arr_mat[0].length; j++)
			{
				System.out.print(arr_mat[i][j]+"	");
			}
			System.out.println();
		}
		
	}
		
	public static void deleteRouter(int delrouter, int matrix[][]) throws Exception
	{
			
		    try{
		    	
		    int deletenode = delrouter;
			int rows = matrix.length;
	        int columns = matrix.length;
	        int updatedMatrix[][] = new int[rows][columns];

	        int REMOVE_ROW = delrouter-1;
	        int REMOVE_COLUMN = delrouter-1;
	        int n = 0;
	        for(int i = 0; i < rows; ++i)
	        {
	            int m = 0;
	            for(int j = 0; j < columns; ++j)
	            {
	               
	            	if (i == REMOVE_ROW || j == REMOVE_COLUMN)
	            		updatedMatrix[n][m] = -1;
	            	else
	                updatedMatrix[n][m] = matrix[i][j];
	                ++m;
	            }

	            ++n;
	        }
	        System.out.println("Updated Matrix Table:");
	        displaymatrix(updatedMatrix);
	        for( int i = 0; i < columns; ++i)
            {
	        	for(int j = 0; j< columns; ++j)
	        	{
	        		matrix[i][j]=updatedMatrix[i][j];
	        	}
            }
                    
	        //checking shortest path, cost, connection table after the modification of network topology.
	        compute_Table(matrix, in);
	        dijkstra_1(matrix, in);
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
	        
	}
	
	
	public static void main(String[] args) throws Exception 
	{
		
		int matrix[][]=null;
		for(;;){
			System.out.println("-----------------------------------------------");
			System.out.println("CS542 Link State Routing Simulator : Choose 1-5");
			System.out.println("1 - Import file");
			System.out.println("2 - Compute Connection Table");
			System.out.println("3 - Shortest Path to Destination Router + Cost");
			System.out.println("4 - Modify a topology - Delete router");
			System.out.println("5 - End Program");
			System.out.println("-----------------------------------------------");
			
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			
			switch (input)
			   {
				case 1:
					matrix = obtainFile();
					displaymatrix(matrix);
					compute_table_all(matrix);
					break;
					
				case 2:
					System.out.println("Select a source router: ");
					Scanner scan = new Scanner(System.in);
					in =scan.nextInt();
					
					compute_Table(matrix, in);
					break;
				

				case 3:
					if(in==0){
						System.out.println("No source router seleted yet. Please select a router using choice 2");
						main(null);
					}
					dijkstra_1(matrix, in);
					break;
				
				case 4:
					System.out.println("Enter the router to be removed");
					Scanner s = new Scanner(System.in);
					inp = s.nextInt();
					deleteRouter(inp, matrix);
					break;
						
				case 5:
					System.out.println("****Exit CS542 project. Good Bye!****");
					System.exit(0);
					
				default: System.out.println("Invalid input please try again");
				}
		}		
	}
}
	
	
	
