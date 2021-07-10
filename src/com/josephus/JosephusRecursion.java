/*
This program solves the Josephus problem.  The Josephus problem
involves a circle of N people who are to be killed.  Every
Kth person is killed until one person is left.  This program 
computes the position of that final person.

The program computes this position by implementing a linked list
with its entries representing people in the circle.  The position
of the next entry to remove is found by the index of the entry
removed before it and K.  Adjustments in the position are made so
that the position wraps around the list.  After removing N-1
entries, the entry that remains is the position of the final
person.   
*/

package com.josephus;

import java.util.concurrent.ArrayBlockingQueue;

public class JosephusRecursion{
    
    
    public static int josephus(int N, int K){
        
        ArrayBlockingQueue<Integer> lineup = new ArrayBlockingQueue<Integer>(N, true);

        //populate lineup
        for(int i = 1; i < N + 1; i++){
            lineup.add(i);
        }

        if(N ==1){
            return 1;
        }else{
            
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

       
/*
        while(lineup.size() > 1){
        //do the procedure for one
            for(int i = 1; i < K; i++){
                int temp = lineup.peek();
                lineup.poll();
                lineup.add(temp);
            }

            lineup.poll();
            //System.out.println(lineup.toString());

        }

        int answer = lineup.poll();*/
        //return answer;
               
    }

    // Test from command line:
    //     java Josephus N K
    public static void main(String[] args)
    {
        int N = Integer.parseInt(args[0]);
        int K = Integer.parseInt(args[1]);
        System.out.println("Calling josephus(N="+N+", K="+K+")");
        System.out.println(josephus(N, K));
    }
}
