package aCompleteMatrixCount;

import java.io.IOException;
import java.util.Scanner;

public class Testing {
	public static void main(String[] args) throws NumberFormatException, IOException {
		long start = System.currentTimeMillis();;
	    
		int K,X,N,M;
		int[][]array;
		MatCounter m;
        /*Scanner scanner = new Scanner(System.in);
        
        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        X = scanner.nextInt();
        int[][] array = new int[N][M];
        for(int i = 0; i < N; i++){
        	for(int j = 0; j < M; j++){
        		array[i][j] = scanner.nextInt();
        	}
        }
        scanner.close();*/
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		K = 2;
        X = 1;
        /*array = new int[][]{
        	{0, 1, 0, 1},
        	{1, 0, 1, 0},
        	{0, 1, 0, 1},
        	{1, 0, 1, 0}
        };*/
        
        //m = new MatCounter(array,K,X);        
        //System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 5");
        System.out.println("------------------------------------------------------------------");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		K = 2;
        X = 1;
        array = new int[][]{
        	{1, 1, 1},
        	{1, 1, 1},
        	{1, 1, 1}
        };
        
        m = new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 5");
        System.out.println("------------------------------------------------------------------");
        long end = System.currentTimeMillis();

	    System.out.println((end - start) + " ms");
	}
}
