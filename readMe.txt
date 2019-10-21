This submission is for John and Matthew Knapp


1.	File list – also describe any set up that is required (e.g. all the files need to be in the same directory, a file named something located in a certain directory)

	EightPuzzle.java
	Node.java
	Search.java
	NodePriorityComparator.java

	
2.	How to compile and run code
	
	to compile use the following line- javac EightPuzzle.java Node.java Search.java NodePriorityComparator.java

	To run, here is an example of how the program would be run

	java EightPuzzle 123450678 123456780 -v

a.	If there are command line arguments – how do you pass them and order

	In order to run the program there are 2 required command line arguments and a 3rd optional command line argument

	The first two command line arguments specify the initial state of the puzzle and the desired end state. This should be listed 
	as nine numbers, with a 0 being used to represent the blank

	The third command line is a flag, -v, that can be used to run the program in Verbose Mode. It is optional.


3.	Design decisions you made

	We were able to get the extra credit portion completed, and unsolveable puzzles will be discovered and the program will not attempt
	to search for a solution and will give a message to the user.

4.	Issues that you encountered

	While developing the report, we struggled with some of the analysis of our results versus the algorithms theoretical performance.
	We were unsure of how to compare our data of average Nodes expanded and maximum Nodes stored to the algorithms theoretical time complexity or
	memory use. We were trying to use the time and space complexity of the algorithms as referenced on the Powerpoints, but we struggled to see
	how we could compare this to the data we collected. We did our best to analyze what our data meant that we had, but may not have performed 
	the correct analysis.
