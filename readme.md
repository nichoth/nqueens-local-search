# 8 Queens local search #

Algorithm:

* Place all the queens on the board, 1 per row per column

* This state is a node. From here the neighbors are all the states in which you move just 1 queen to a random spot in the same row. 

* Create all neighboring states and then evaluate which one is best and move to that one and repeat the process.

*Cost* = the number of queens that are attacking each other

## Results ##
This is implemented recursively and produces a stack overflow error before it finds a solution. It is able to reduce the cost to 1 or 2 within 7000 iterations.