# josephus


The programs in this repository solve the Josephus problem. The Josephus problem
involves a circle of N people who are to be killed.  Every
Kth person is killed until one person is left.  These programs 
compute the position of that final person.

Check out an interactive walkthrough on solving the Josephus
Problem at the site here:
https://robbieblom.com/?page_id=123

Josephus.java computes this position by implementing a linked list
with its entries representing people in the circle.  The position
of the next person to remove is found by the position of the last
person removed and K.  Adjustments in the position are made so
that the position wraps around the list.  After removing N-1
people, we arrive at the entry of the linked list representing
the final person. 

JosephusRecursion.java computes the position by implementing a recursive 
algorithm with fixed length FIFO queues.  For a lineup of N>1 people
the algorithm performs one cycle of removing the Kth person and then
uses the index of the answer to the Josephus problem with N-1 people
to return the final answer.
