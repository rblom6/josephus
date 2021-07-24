/*
This program solves the Josephus problem. The Josephus problem
involves a circle of N people who are to be killed.  Every
Kth person is killed until one person is left.  This program 
computes the position of that final person.

The program computes this position by implementing a recursive 
algorithm with fixed length FIFO queues.  For a lineup of N>1 people
the algorithm performs one cycle of removing the Kth person and then
uses the index of the answer to the Josephus problem with N-1 people
to return the final answer.
*/

package com.josephus;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class JosephusRecursion{
    
    
    public static int josephus(int N, int K){
        
        ArrayBlockingQueue<Integer> lineup = new ArrayBlockingQueue<Integer>(N, true);

        //populate lineup
        for(int i = 1; i <= N; i++){
            lineup.add(i);
        }
        
        if(N == 1){
        	// return 1 if there's only one person
            return 1;
        }else{
            // otherwise, the answer to the problem can be found
        	// by cycling the queue, polling the first value to be
        	// removed, and then using the value of josephus(N-1, K)
        	// to calculate an array index of the cycled queue to 
        	// get the answer to josephus(N, K).
        	
        	// cycle the array so that the kth person 
        	// is at the head of the queue. Then remove that kth person.
            for(int i = 1; i < K; i++){
                int temp = lineup.peek();
                lineup.poll();
                lineup.add(temp);
            }
            lineup.poll();
            //System.out.println(lineup.toString());
            
            int index = josephus(N-1, K) - 1;
            for(int i = 0; i < index; i++){
                lineup.poll();
            }

            int answer = lineup.peek();
            return answer;
        }

               
    }


    public static void main(String[] args)
    {
    	Scanner scan = new Scanner(System.in);
        
        System.out.println("Hello! Let's solve the Josephus Problem!");
        
        String again = "yes";
        do {
	       
	        System.out.println("\nHow many people are in the circle?");
	        int N = scan.nextInt();
	        
	        System.out.println("How many people to count before the unlucky thing happens?");
	        int K = scan.nextInt();
	    	
	        System.out.println("You wanna be at position " + josephus(N, K) + " to keep surviving!");
	        
	        System.out.println("Wanna play again? (yes/no)");
	        scan.nextLine();
	        again = scan.nextLine();
	        
	        
        } while(again.equals("yes"));
        
        System.out.println("See ya later!");
    }
}
