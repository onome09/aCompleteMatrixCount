package aCompleteMatrixCount;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Fuck {
	public static void main(String[] args) throws NumberFormatException, IOException {
		long start = System.currentTimeMillis();;
	    
		int K,X,N,M;
		int[][]array;
		MatCounter m;
        /*Scanner scanner = new Scanner(new FileReader("data.txt"));
        
        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        X = scanner.nextInt();
        array = new int[N][M];
        for(int i = 0; i < N; i++){
        	for(int j = 0; j < M; j++){
        		array[i][j] = scanner.nextInt();
        	}
        }
        scanner.close();
        m = new MatCounter(array,K,X); 
        System.out.println(m.getNumberOfSubMatrices());*/
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 5;
        array = new int[][]{
				{1, 2, 1, 3, 4, 2, 1, 5},
				{4, 5, 2, 5, 5, 3, 2, 2},
				{5, 5, 5, 3, 4, 4, 5, 3},
				{3, 5, 5, 3, 3, 5, 5, 2},
				{2, 3, 1, 2, 3, 5, 1, 1},
				{1, 4, 3, 1, 5, 3, 3, 4},
				{2, 1, 2, 5, 1, 2, 1, 5},
				{4, 1, 1, 1, 5, 4, 4, 3},
				{1, 1, 5, 5, 5, 1, 5, 1},
				{2, 2, 2, 2, 3, 5, 3, 4},
        };
        m = new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 552");
        System.out.println("------------------------------------------------------------------");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 1;
        array = new int[][]{
				{3, 5, 2, 3, 5, 1, 1, 5},
				{4, 3, 3, 2, 1, 2, 3, 1},
				{3, 5, 3, 1, 2, 1, 2, 4},
				{4, 5, 1, 5, 5, 4, 4, 4},
				{5, 5, 5, 4, 3, 1, 1, 5},
				{2, 2, 4, 2, 4, 2, 4, 4},
				{4, 3, 4, 2, 4, 3, 1, 1},
				{2, 5, 5, 4, 5, 4, 3, 1},
				{3, 3, 3, 2, 4, 2, 3, 5},
				{2, 3, 4, 4, 5, 5, 3, 3}
        };
        m = new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 240");
        System.out.println("------------------------------------------------------------------");		
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 3;
        array = new int[][]{
				{2, 5, 2, 1, 1, 4, 2, 3, 1},
				{2, 5, 2, 2, 4, 3, 5, 4, 4},
				{5, 2, 5, 3, 1, 5, 4, 4, 3},
				{5, 1, 3, 3, 4, 2, 4, 5, 5},
				{1, 2, 3, 3, 4, 5, 2, 4, 5},
				{2, 2, 4, 1, 3, 5, 1, 5, 5},
				{5, 2, 4, 3, 1, 2, 2, 2, 1},
				{4, 2, 4, 2, 3, 5, 2, 5, 1},
				{1, 3, 2, 1, 5, 3, 2, 1, 5},
				{1, 1, 1, 5, 5, 1, 4, 5, 3}
        };
        m = new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 461");
        System.out.println("------------------------------------------------------------------");	
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 5;
        array = new int[][]{
				{2, 4, 2, 2, 3, 1, 5, 4},
			    {3, 3, 4, 5, 5, 1, 5, 3},
			    {1, 2, 2, 5, 3, 3, 2, 3},
		        {5, 2, 5, 2, 5, 4, 2, 1},
		        {1, 2, 3, 1, 1, 3, 1, 4},
		        {5, 5, 4, 2, 1, 1, 2, 1},
		        {3, 5, 5, 5, 2, 4, 3, 4},
		        {2, 1, 1, 5, 4, 4, 2, 3},
		        {3, 1, 1, 1, 2, 1, 3, 2},
		        {2, 1, 5, 1, 5, 5, 1, 1}
        };
        m = new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 380");
        System.out.println("------------------------------------------------------------------");	
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 2;
        array = new int[][]{
				{2, 2, 5, 1, 3, 2, 5, 5},
				{2, 3, 4, 3, 3, 5, 3, 2},
				{4, 3, 4, 3, 4, 5, 3, 2},
				{5, 3, 1, 4, 3, 5, 5, 2},
				{2, 3, 4, 4, 3, 4, 2, 1},
				{2, 3, 1, 4, 1, 5, 2, 4},
				{5, 1, 2, 4, 5, 2, 1, 4},
				{3, 4, 2, 1, 4, 3, 3, 5},
				{4, 5, 2, 1, 2, 1, 5, 2},
				{2, 5, 3, 4, 4, 5, 1, 1}
        };
        m = new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 268");
        System.out.println("------------------------------------------------------------------");	
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 1;
        array = new int[][]{
				{4, 3 ,4 ,2 ,5 ,3, 2, 4},
				{3, 5, 4, 1, 4, 5, 2, 5},
				{5, 1, 4, 2, 2, 3, 3, 5},
				{5, 4, 3, 5, 4, 1, 5, 3},
				{3, 3, 2, 3, 5, 2, 5, 2},
				{3, 2, 3, 1, 5, 2, 3, 2},
				{4, 3, 4, 3, 3, 1, 3, 4},
				{3, 2, 2, 5, 3, 1, 4, 4},
				{4, 2, 3, 4, 2, 3, 4, 4},
				{1, 3, 4, 4, 1, 5, 5, 5}
        };
        m = new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 105");
        System.out.println("------------------------------------------------------------------");	
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 2;
        array = new int[][]{
				{2, 5, 1, 4, 4, 5, 5, 2},
				{2, 3, 2, 1, 2, 5, 3, 4},
				{5, 1, 4, 4, 1, 3, 3, 5},
				{3, 1, 4, 2, 4, 2, 3, 5},
				{2, 3, 2, 5, 5, 2, 4, 1},
				{5, 4, 3, 4, 2, 5, 1, 2},
				{4, 5, 5, 3, 4, 5, 4, 3},
				{4, 3, 3, 5, 1, 5, 4, 4},
				{1, 4, 4, 4, 5, 1, 3, 5},
				{5, 5, 2, 5, 4, 1, 5, 4}
        };
        m = new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 273");
        System.out.println("------------------------------------------------------------------");	
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 4;
        array = new int[][]{
				{1, 5, 4, 1, 3, 1, 4, 1},
				{4, 2, 1, 5, 5, 3, 1, 5},
				{1, 4, 5, 1, 2, 5, 4, 3},
				{1, 1, 2, 3, 5, 3, 2, 2},
				{1, 2, 1, 5, 5, 5, 5, 3},
				{5, 4, 1, 3, 3, 4, 3, 1},
				{4, 5, 2, 4, 3, 4, 3, 2},
				{1, 3, 2, 1, 2, 4, 3, 3},
				{5, 4, 2, 1, 3, 1, 3, 3},
				{3, 5, 2, 1, 3, 4, 1, 1}
        };
        m = new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 218");
        System.out.println("------------------------------------------------------------------");	
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 5;
        array = new int[][]{
				{5, 2, 1, 1, 4, 4, 1, 3, 5},
				{5, 2, 3, 5, 3, 1, 1, 2, 4},
				{2, 4, 4, 2, 2, 1, 4, 2, 5},
				{5, 4, 2, 2, 5, 3, 4, 5, 4},
				{2, 4, 4, 2, 5, 5, 2, 5, 2},
				{3, 3, 2, 1, 2, 5, 4, 1, 5},
				{4, 4, 5, 2, 5, 5, 4, 4, 1},
				{2, 4, 2, 4, 4, 1, 4, 3, 2},
				{2, 4, 4, 2, 5, 2, 5, 5, 4},
				{4, 5, 5, 4, 3, 2, 3, 2, 1}
        };
        m = new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 583");
        System.out.println("------------------------------------------------------------------");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 2;
        array = new int[][]{
				{4, 4, 3, 3, 3, 3, 5, 3},
				{1, 1, 4, 1, 1, 3, 4, 3},
				{3, 5, 4, 3, 4, 2, 4, 5},
				{5, 5, 5, 5, 2, 5, 2, 1},
				{5, 1, 5, 2, 4, 4, 4, 5},
				{1, 4, 3, 5, 1, 1, 5, 3},
				{5, 4, 2, 5, 1, 1, 5, 4},
				{1, 5, 4, 3, 2, 3, 5, 2},
				{1, 2, 1, 5, 3, 4, 5, 5},
				{2, 1, 1, 1, 4, 1, 2, 2}
        };
        m = new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 161");
        System.out.println("------------------------------------------------------------------");
        long end = System.currentTimeMillis();

	    System.out.println((end - start) + " ms");
	}
}
