import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * @author John and Matthew Knapp
 * Eightpuzzle is our main class. This handles all the command line arguments coming in. Our command line arguments
 * allow the user to specify a initial state of the puzzle and a goal state of the puzzle.
 */
public class EightPuzzle
{
    public static void main(String[] args)
    {

        if(args.length == 2 || args.length==3 || args[0].length()!=9 || args[1].length()!=9) {


            boolean verboseMode=false;
            if(args.length==3 && args[2].equals("-v"))
            {
                verboseMode=true;
            }

            String[] arg1 = args[0].split("");
            String[] arg2 = args[1].split("");

            if (arg2.length == 9 && arg1.length == 9) {

                int[] puzzle = new int[9];
                int[] goalState = new int[9];

                for(int x=0;x<9;x++){
                    puzzle[x]=Integer.parseInt(arg1[x]);
                    goalState[x]=Integer.parseInt(arg2[x]);
                }

                if(!solutionPossible(puzzle,goalState)){
                    System.out.println("No solution possible given initial state");
                    return;
                }



                Search ui = new Search(goalState);

                Node root1 = new Node(puzzle, 1,"");
                List<Node> solution1 = ui.BreadthFirstSearch(root1,verboseMode,true);
                System.out.println("Breadth First Search with Repeated States...");
                printSearchResults(solution1, ui,verboseMode);

                Node root2 = new Node(puzzle, 1,"");
                List<Node> solution2 = ui.BreadthFirstSearch(root2,verboseMode,false);
                System.out.println("Breadth First Search without Repeated States...");
                printSearchResults(solution2, ui,verboseMode);

                Node root3 = new Node(puzzle, 1,"");
                List<Node> solution3 = ui.DepthFirstSearch(root3,verboseMode,true);
                System.out.println("Depth First Search with Repeated States...");
                printSearchResults(solution3, ui,verboseMode);

                Node root4 = new Node(puzzle, 1,"");
                List<Node> solution4 = ui.DepthFirstSearch(root4,verboseMode,false);
                System.out.println("Depth First Search without Repeated States...");
                printSearchResults(solution4, ui,verboseMode);


                Node root5 = new Node(puzzle, 1,"");
                List<Node> solution5 = ui.AStar(root5,verboseMode);
                System.out.println("A* Search...");
                printSearchResults(solution5, ui,verboseMode);


            }
            else{
                System.out.println("Command line arguments not formatted correctly");
                System.out.println("Please use form java EightPuzzle 123456780 123456780 -v");
            }
        }
        else{
            System.out.println("Incorrect command line arguments");
        }





    }

    /**
     * This method is used to reverse to order of the list as it lists the order of the solution from leaf to root. It also
     * formats the printing of the solution
     * @param solution This is the List of Nodes leading from the leaf to the root Node that traces a path to the solution
     * @param ui this is the Search Object that was used to perform the search. Used to print information about the search performance
     * @param verboseMode Used to tell if the method to print something only if verbose mode toggled when running
     */
    public static void printSearchResults(List<Node> solution, Search ui, boolean verboseMode)
    {
        if (solution.size() > 0)
        {
            Collections.reverse(solution);
            System.out.print("Moves: ");
            for (int i = 0; i < solution.size(); i++) {
                System.out.print(solution.get(i).getMove());
                if(verboseMode){
                    System.out.println(Arrays.toString(solution.get(i).currentPuzzle));
                }

            }
            System.out.println("\nNumber of Nodes Expanded: " + ui.getNodesExpanded());
            System.out.println("Number of Nodes Stored: " + ui.getNodesStored());
            System.out.println();
        }
        else
        {
            System.out.println("Solution not able to be found");
        }
    }

    /**
     * takes in the initial state of the puzzle and determines if the puzzle is solvable. This method does this
     * by counting the number of inversions in the initial state. Inversions count the number of times a number is
     * behind or ahead of where it is supposed to be in the goal state. If the number of inversions is even,
     * the puzzle is solvable, whereas if the number of inversions in odd, the puzzle is unsolvable.
     * @param puzzle contains the initial state of the puzzle
     * @param goal contains the desired goal of the puzzle
     * @return returns true if the puzzle is solvable and returns false if it is not solvable
     */
    public static boolean solutionPossible(int[] puzzle, int[] goal)
    {
        int count = 0;
        for (int i = 0; i < 9; i++)
        {
            int goalIndex1=0;
            if(puzzle[i]!=0) {

                for (int v = 0; v < 9; v++) {
                    if (goal[v] == puzzle[i]) {
                        goalIndex1 = v;
                    }
                }


                for (int j = 0; j < 9; j++) {
                    if (puzzle[i] != 0 && goal[j]!=0) {
                        int puzzleIndex2=0;
                        for (int y = 0;y < 9;y++) {
                            if (puzzle[y]==goal[j]) {
                                puzzleIndex2 = y;
                            }
                        }
                        //System.out.println(puzzleIndex2);
                        if(i<puzzleIndex2 && goalIndex1>j){
                            count++;
                        }
//
                    }
                }
            }
        }

        if(count%2==0){
            return true;
        }
        return false;
    }
}
