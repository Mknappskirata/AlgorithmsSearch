import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EightPuzzle
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");

        int[] puzzle = {1,8,2,0,4,3,7,6,5};
        int[] goalState = {1,2,3,4,5,6,7,8,0};

        Node root= new Node(puzzle,1, "Root");
        UninformedSearch ui = new UninformedSearch(goalState);

        //List<Node> solution = ui.BreadthFirstSearch(root);

        //List<Node> solution = ui.DepthFirstSearch(root);

        List<Node> solution = ui.AStar(root);

        if(solution.size() > 0)
        {
            Collections.reverse(solution);
            for(int i = 0; i < solution.size(); i++)
            {
                System.out.println("Move: " + solution.get(i).getMove());
                System.out.println(Arrays.toString(solution.get(i).puzzle));
            }
        }
        else
        {
            System.out.println("No path to solution");
        }



    }
}
