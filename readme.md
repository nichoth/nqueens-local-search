# 8 Queens local search #

Algorithm:

* Place all the queens on the board, 1 per row per column

* This state is a node. From here the neighbors are all the states in which you move just 1 queen to a random spot in the same row. 

* Create all neighboring states and then evaluate which one is best and move to that one and repeat the process.

*Cost* = the number of queens that are attacking each other

## Results ##
This is local search implemented with a while loop in Java.

The algorithm reaches local maximums that can't be solved by increasing iterations.