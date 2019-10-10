import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class UninformedSearch
{
    public int[] goalState;

    public UninformedSearch(int[] goalState)
    {
        this.goalState = goalState;
    }

    public List<Node> BreadthFirstSearch(Node root)
    {
         List<Node> PathToSolution = new ArrayList<>();
         List<Node> OpenList = new ArrayList<>();
         List<Node> ClosedList = new ArrayList<>();
         int currentDepthSearched = 0;

         OpenList.add(root);
         boolean goalFound = false;

         while(OpenList.size() > 0 && !goalFound && currentDepthSearched <= 10)
         {
             Node currentNode = OpenList.get(0);
             currentDepthSearched = currentNode.depth;
             ClosedList.add(currentNode);
             OpenList.remove(0);

             currentNode.ExpandNode(currentNode.depth);



             for(int i = 0; i <currentNode.children.size(); i++)
             {
                 Node currentChild = currentNode.children.get(i);
                 if(currentChild.GoalTest(goalState))
                 {
                     System.out.println("Goal state found!");
                     goalFound = true;
                     PathTrace(PathToSolution, currentChild);
                 }

                 if(!Contains(OpenList, currentChild) && !Contains(ClosedList, currentChild))
                 {
                     OpenList.add(currentChild);
                 }

             }
             //System.out.println();
         }
         return PathToSolution;
    }

    public List<Node> DepthFirstSearch(Node root)
    {
        List<Node> PathToSolution = new ArrayList<>();
        List<Node> OpenList = new ArrayList<>();
        List<Node> ClosedList = new ArrayList<>();

        OpenList.add(root);
        root.depth = 0;
        boolean goalFound = false;

        while(OpenList.size() > 0 && !goalFound)
        {
            Node currentNode = OpenList.get(0);
            ClosedList.add(currentNode);
            OpenList.remove(0);

            if(currentNode.parent != null)
            {
                currentNode.depth = currentNode.parent.depth + 1;
            }

            if(currentNode.depth <= 10)
            {
                currentNode.ExpandNode(currentNode.depth);
            }

            for(int i = 0; i <currentNode.children.size(); i++)
            {
                Node currentChild = currentNode.children.get(i);
                if(currentChild.GoalTest(goalState))
                {
                    System.out.println("Goal state found!");
                    goalFound = true;
                    PathTrace(PathToSolution, currentChild);
                }

                if(!Contains(OpenList, currentChild) && !Contains(ClosedList, currentChild))
                {
                    OpenList.add(0,currentChild);
                }

            }
            //System.out.println();
        }
        return PathToSolution;
    }

    public List<Node> AStar(Node root)
    {
        List<Node> PathToSolution = new ArrayList<>();
        NodePriorityComparator nodePriorityComparator = new NodePriorityComparator();
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<Node>(10, nodePriorityComparator);
        List<Node> ClosedList = new ArrayList<>();

        nodePriorityQueue.add(root);
        root.SetTotalCost(root.FindTotalCost(goalState));
        boolean goalFound = false;

        while(nodePriorityQueue.size() > 0 && !goalFound)
        {
            Node currentNode = nodePriorityQueue.poll();
            ClosedList.add(currentNode);
            nodePriorityQueue.remove(currentNode);

            currentNode.ExpandNode(currentNode.depth);

            for(int i = 0; i <currentNode.children.size(); i++)
            {
                Node currentChild = currentNode.children.get(i);
                if(currentChild.GoalTest(goalState))
                {
                    System.out.println("Goal state found!");
                    goalFound = true;
                    PathTrace(PathToSolution, currentChild);
                }

               // List<Node> asList = Arrays.asList(nodePriorityQueue.toArray(new Node[nodePriorityQueue.size() + 1]));
                List<Node> arr = Arrays.asList(nodePriorityQueue.toArray(new Node[nodePriorityQueue.size()]));
                if(!Contains(ClosedList, currentChild))
                {
                    nodePriorityQueue.add(currentChild);
                }

            }

        }

        return PathToSolution;
    }



    public void PathTrace(List<Node> path, Node n)
    {
        System.out.println("Tracing path");
        Node current = n;
        path.add(current);

        while(current.parent != null)
        {
            current = current.parent;
            path.add(current);
        }
    }

    public static boolean Contains(List<Node> list, Node n)
    {
        boolean contains = false;

        for(int i =0; i<list.size(); i++)
        {
            if(list.get(i).IsSamePuzzle(n.puzzle))
                contains = true;
        }
        return contains;
    }



}
