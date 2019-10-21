import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
/**
 * @author John and Matthew Knapp
 * Search is a class that contains our search methods.  This is where our BreadthFirstSearch(),
 * DepthFirstSearch(), and AStar() methods are.
 */
public class Search
{
    public int[] goalState;
    private int nodesExpanded = 0;
    private int nodesStored = 0;

    public int getNodesExpanded()
    {
        return this.nodesExpanded;
    }

    public int getNodesStored()
    {
        return this.nodesStored;
    }

    public Search(int[] goalState)
    {
        this.goalState = goalState;
    }

    public List<Node> BreadthFirstSearch(Node root,boolean verboseMode, boolean allowRepeatedStates)
    {
        List<Node> PathToSolution = new ArrayList<>();
        List<Node> OpenList = new ArrayList<>();
        List<Node> ClosedList = new ArrayList<>();
        int currentDepthSearched = 0;
        nodesExpanded = 0;
        nodesStored = 0;

        OpenList.add(root);
        boolean goalFound = false;

        while(OpenList.size() > 0 && !goalFound && currentDepthSearched <= 10)
        {
            Node currentNode = OpenList.get(0);
            currentDepthSearched = currentNode.depth;
            if(!allowRepeatedStates)
            {
                ClosedList.add(currentNode);
            }

            OpenList.remove(0);

            if(verboseMode){
                System.out.print("Expanding node...  ");
                currentNode.PrintArray(currentNode.currentPuzzle);
            }
            currentNode.ExpandNode(currentNode.depth);
            nodesExpanded++;




            for(int i = 0; i <currentNode.children.size(); i++)
            {
                Node currentChild = currentNode.children.get(i);
                if(currentChild.GoalTest(goalState))
                {
                    System.out.println("Goal state found!");
                    goalFound = true;
                    PathTrace(PathToSolution, currentChild);
                }

                if(!allowRepeatedStates)
                {
                    if(!Contains(OpenList, currentChild) && !Contains(ClosedList, currentChild))
                    {
                        OpenList.add(currentChild);
                    }
                }
                else
                {
                    OpenList.add(currentChild);
                }



            }
            //System.out.println();
        }
        if(!allowRepeatedStates)
        {
            this.nodesStored = OpenList.size() + ClosedList.size();
        }
        else
        {
            this.nodesStored = OpenList.size() + nodesExpanded;
        }

        return PathToSolution;
    }

    public List<Node> DepthFirstSearch(Node root,boolean verboseMode, boolean allowRepeatedStates)
    {
        List<Node> PathToSolution = new ArrayList<>();
        List<Node> OpenList = new ArrayList<>();
        List<Node> ClosedList = new ArrayList<>();
        nodesExpanded = 0;
        nodesStored = 0;

        OpenList.add(root);
        root.depth = 0;
        boolean goalFound = false;

        while(OpenList.size() > 0 && !goalFound)
        {
            Node currentNode = OpenList.get(0);
            if(!allowRepeatedStates)
            {
                ClosedList.add(currentNode);
            }
            OpenList.remove(0);

            if(currentNode.parent != null)
            {
                currentNode.depth = currentNode.parent.depth + 1;
            }

            if(currentNode.depth < 10)
            {
                if(verboseMode){
                    System.out.print("Expanding node...  ");
                    currentNode.PrintArray(currentNode.currentPuzzle);
                }
                currentNode.ExpandNode(currentNode.depth);
                nodesExpanded++;
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

                if(!allowRepeatedStates)
                {
                    if(!Contains(OpenList, currentChild) && !Contains(ClosedList, currentChild))
                    {
                        OpenList.add(currentChild);
                    }
                }
                else
                {
                    OpenList.add(currentChild);
                }

            }
            //System.out.println();
        }
        this.nodesStored = OpenList.size() + 10;
        return PathToSolution;
    }

    public List<Node> AStar(Node root, boolean verboseMode)
    {
        List<Node> PathToSolution = new ArrayList<>();
        NodePriorityComparator nodePriorityComparator = new NodePriorityComparator();
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<Node>(10, nodePriorityComparator);
        List<Node> ClosedList = new ArrayList<>();
        nodesExpanded = 0;
        nodesStored = 0;

        nodePriorityQueue.add(root);
        root.SetTotalCost(root.FindTotalCost(goalState));
        boolean goalFound = false;

        while(nodePriorityQueue.size() > 0 && !goalFound)
        {
            Node currentNode = nodePriorityQueue.poll();
            currentNode.SetTotalCost(currentNode.FindTotalCost(goalState));
            ClosedList.add(currentNode);
            nodePriorityQueue.remove(currentNode);
            if(verboseMode){
                System.out.print("Expanding node...  ");
                currentNode.PrintArray(currentNode.currentPuzzle);
            }

            currentNode.ExpandNode(currentNode.depth);
            nodesExpanded++;

            for(int i = 0; i <currentNode.children.size(); i++)
            {
                Node currentChild = currentNode.children.get(i);
                currentChild.SetTotalCost(currentChild.FindTotalCost(goalState));
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
        this.nodesStored = nodePriorityQueue.size() + ClosedList.size();
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
            if(list.get(i).IsSamePuzzle(n.currentPuzzle))
                contains = true;
        }
        return contains;
    }



}
