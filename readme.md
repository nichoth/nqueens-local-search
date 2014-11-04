# N Queens #

## Local Search ##
The first iteration uses a local search algorithm:

	* Start with a randomly arranged board with one queen per row
	* Generate neighboring states where each neighbor is a board where one queen has been moved to a random spot within its row. Each iteration generates 8 nieghbors.
	* Pick the neighbor with the best performance, then repeat.

This algorithm is incomplete. It will find a local maximum but not necessarilly a global maximum.

The program usually reacheas a maximum quickly, within around 50 iterations.

Originally it was implemented recursively in Java, which caused a stack overflow around 7000 iterations. It was changed to using while loops which use space efficiently. **Can you implement tail-call recursion in Java?**


## Simulated Annealing ##
Simulated Annealing will find a global maximum. It uses rondomeness to avoid getting stuck at a local maximum. The algorithm is similar to local search:

	* Start with a random board
	* Generate neighbor states, and choose a random neighbor.
	* If the nieghbor improves the situation, it is accepted.
	* If the neighbor is worse, it is accepted with a probablility that decreases exponentially with the badness of the move. The probability also decreases based on a "schedule" -- a function that maps time to "temperature".

See page 126. The probability of accepting a bad move is e ^ (∆E/T), where ∆E is the change in fitness, and T is the temperature (the value returned by the schedule).

The schedule `100 / iteration` seems to work well. It always finds a solution within a few hundred to ~3000 iterations.

**How do we find the best schedule function?**

