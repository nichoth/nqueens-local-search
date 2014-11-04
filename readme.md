# 8 Queens - Simulated Annealing #

Algorithm:

* Place all the queens on the board, 1 per row per column

* This state is a node. From here the neighbors are all the states in which you move just 1 queen to a random spot in the same row. 

*Cost* = the number of queens that are attacking each other

## Results ##
The simulated annealing 'schedule' (the function from time to probability) needs to be adjusted. As it is naive local search performs better.

Bug in saved data for solutions?
