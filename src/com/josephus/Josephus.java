/*
This program solves the Josephus problem. The Josephus problem
involves a circle of N people who are to be killed.  Every
Kth person is killed until one person is left.  This program 
computes the position of that final person.

The program computes this position by implementing a linked list
with its entries representing people in the circle.  The position
of the next person to remove is found by the position of the last
person removed and K.  Adjustments in the position are made so
that the position wraps around the list.  After removing N-1
people, we arrive at the entry of the linked list representing
the final person.   
*/

package com.josephus;


import java.util.LinkedList;
import java.util.Scanner;

public class Josephus{


    // Initializes a lineup of people with 
	// positions ascending from 1 to N.
    public static LinkedList<Integer> makeList(int N){
            LinkedList<Integer> lineup = new LinkedList<Integer>();
            for(int i = 1; i < N + 1; i++){
            lineup.add(i);
        }

        return lineup;
    }


    // Removes a person from the list at a given position
    public static int removeOne(int positionToRemove, LinkedList<Integer> name){
         int index = positionToRemove - 1;
         name.remove(index);

         return positionToRemove;
    }


    // Removes all the people in the list except the last one (which is
    // the answer).
    public static LinkedList<Integer> removePeople(int N,int K){
     
        LinkedList<Integer> temp = makeList(N);
        int totalToRemove = N - 1;
        int positionOfLastRemoved = -1;
        for(int removalNumber = 1; removalNumber <= totalToRemove; removalNumber++){
            int positionToRemove = calculatePositionToRemove(N - removalNumber + 1, K, positionOfLastRemoved);
            positionOfLastRemoved = removeOne(positionToRemove, temp);
        }
        
        return temp;
    }


    // Calculates the next person to remove
    // Allows for wrapping around the list
    public static int calculatePositionToRemove(int numRemaining, int K, int positionOfLastRemoved){
    			
    			// Add K for an initial positionToRemove
    			int positionToRemove;
    			if(positionOfLastRemoved == -1) {
    				positionToRemove = K;
    			}else {
    				positionToRemove = positionOfLastRemoved + K - 1;
    			}
    			
    			// Adjust positionToRemove to account for wrapping around the list
    			if((numRemaining) >= positionToRemove){
                    return positionToRemove;
                }else if((numRemaining)<positionToRemove && positionToRemove%(numRemaining) == 0){
                	return (numRemaining);
                }else{
                    return ( positionToRemove%(numRemaining) );
                }
    }
 

    // Solves the Josephus problem
    public static int josephus(int N, int K){
        LinkedList<Integer> lineup = makeList(N);
        lineup = removePeople(N,K);
        int answer = lineup.poll();
        
        return answer;
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
