/*
This program solves the Josephus problem. The Josephus problem
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


import java.util.LinkedList;

public class Josephus{


    //initializes a lineup of entries ascending from 1 to N
    public static LinkedList<Integer> makeList(int N){
            LinkedList<Integer> lineup = new LinkedList<Integer>();
            for(int i = 1; i < N + 1; i++){
            lineup.add(i);
        }

        return lineup;
    }


    //removes an entry from the list at a given position
    public static int removeOne(int position, LinkedList<Integer> name){
         int index = position - 1;
         name.remove(index);

         return index;
    }


    //removes all the entries in the list except the last one (which is
    //the answer)
    public static LinkedList<Integer> removeNumbers(int N,int K){
     
        LinkedList<Integer> temp = makeList(N);
        int indexOfNumRemoved = 0;
        for(int i = 0; i < N-1; i++){
            int position = indexOfNumRemoved + K;
            position = resetPosition(i,N,position);
            indexOfNumRemoved = removeOne(position, temp);
        }
        
        return temp;
    }


    //allows the position to wrap around the list
    public static int resetPosition(int i, int N, int position){
                if((N-i)>=position){
                    return position;
                }else if((N-i)<position && position%(N-i) == 0){
                    return (N-i);
                }else{
                    return position%(N-i);
                }
    }
 

    //solves Josephus problem
    public static int josephus(int N, int K){
        LinkedList<Integer> lineup = makeList(N);
        lineup = removeNumbers(N,K);
        int answer = lineup.poll();
        
        return answer;
    }


    public static void main(String[] args)
    {
        int N = Integer.parseInt(args[0]);
        int K = Integer.parseInt(args[1]);
        System.out.println("Calling josephus(N="+N+", K="+K+")");
        System.out.println(josephus(N, K));
    }
}
