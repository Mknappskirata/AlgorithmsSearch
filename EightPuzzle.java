import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EightPuzzle
{
    public static void main(String[] args)
    {

       if(args.length == 2) {

           String[] arg1 = args[0].split("");
           String[] arg2 = args[1].split("");

           if (arg2.length == 9 || arg1.length == 9) {

               int[] puzzle = new int[9];
               int[] goalState = new int[9];

               for(int x=0;x<9;x++){
                   puzzle[x]=Integer.parseInt(arg1[x]);
                   goalState[x]=Integer.parseInt(arg2[x]);
               }

               Node root = new Node(puzzle, 1);
               UninformedSearch ui = new UninformedSearch(goalState);

               //List<Node> solution = ui.BreadthFirstSearch(root);

               //List<Node> solution = ui.DepthFirstSearch(root);

               List<Node> solution = ui.AStar(root);

               if (solution.size() > 0) {
                   Collections.reverse(solution);
                   for (int i = 0; i < solution.size(); i++) {
                       System.out.println(Arrays.toString(solution.get(i).puzzle));
                   }
               } else {
                   System.out.println("No path to solution");
               }
           }
           else{
               System.out.println("Command line arguments not formatted correctly");
               System.out.println("Please use form java EightPuzzle 123456780 123456780");
           }
       }
       else{
           System.out.println("Incorrect number of command line arguments");
       }



        

    }
}
