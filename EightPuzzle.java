import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EightPuzzle
{
    public static void main(String[] args)
    {

        if(args.length == 2 || args.length==3) {


             boolean verboseMode=false;
             if(args.length==3 && args[2].equals("-v"))
             {
                 verboseMode=true;
             }

            String[] arg1 = args[0].split("");
            String[] arg2 = args[1].split("");

            if (arg2.length == 9 || arg1.length == 9) {

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


                Node root = new Node(puzzle, 1,"Root");
                UninformedSearch ui = new UninformedSearch(goalState);

                //List<Node> solution = ui.BreadthFirstSearch(root,verboseMode,true);

                //List<Node> solution = ui.BreadthFirstSearch(root,verboseMode,false);

                //List<Node> solution = ui.DepthFirstSearch(root,verboseMode,true);

                List<Node> solution = ui.DepthFirstSearch(root,verboseMode,false);

                //List<Node> solution = ui.AStar(root,verboseMode);

                if (solution.size() > 0) {
                    Collections.reverse(solution);
                    for (int i = 0; i < solution.size(); i++) {
                        System.out.println("Move: " + solution.get(i).getMove());
                        if(verboseMode){
                            System.out.println(Arrays.toString(solution.get(i).puzzle));
                        }

                    }
                } else {
                    System.out.println("Solution not able to be found");
                }
            }
            else{
                System.out.println("Command line arguments not formatted correctly");
                System.out.println("Please use form java EightPuzzle 123456780 123456780 V/N");
            }
        }
        else{
            System.out.println("Incorrect number of command line arguments");
        }





    }

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
