import java.util.ArrayList;
import java.util.List;
/**
 * @author John and Matthew Knapp
 * Node is our class that stores information about each node examined in our search tree.
 */
public class Node
{
    public List<Node> children = new ArrayList<>();
    public Node parent;
    public int[] currentPuzzle = new int[9];
    public int depth;
    public int blankPosition = 0;
    private int col = 3;
    private int totalCost;
    private String move;

    public void setMove(String move) {
        this.move = move;
    }

    public String getMove() {
        return move;
    }


    /**
     * Contructor for Node objects
     * @param p puzzle being passed in for initial state of Node
     * @param depth depth of Node in tree
     * @param move specifies how the puzzle changed from parent state(up,left,down,right)
     */
    public Node(int[] p, int depth, String move)
    {
        currentPuzzle = p;
        this.depth = depth;
        this.move = move;
    }


    /**
     * Getter Method for totalCost
     * @return totalCost of Node
     */
    public int GetTotalCost() {
        return totalCost;
    }

    /**
     * Setter Method for totalCost
     * @param totalCost specifies new cost
     */
    public void SetTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * this method tests to see if the Node’s puzzle is equivalent to the goal state sent in through the command line
     * @param goalState this is the goal of the puzzle when complete
     * @return will return true if the Node calling this method is the goal state and false if not
     */
    public boolean GoalTest(int[] goalState)
    {
        for(int i = 0; i<goalState.length; i++)
        {
            if(currentPuzzle[i] != goalState[i])
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This method sets the position of the blank for the Node that calls it and then expands the Node for all legal
     * moves based off the blank’s position
     * @param depth the current depth of the Node trying to expand
     */
    public void ExpandNode(int depth)
    {
        for(int i = 0; i<currentPuzzle.length; i++)
        {
            if(currentPuzzle[i] == 0)
            {
                blankPosition = i;
            }
        }

        MoveUp(currentPuzzle, blankPosition, depth);
        MoveRight(currentPuzzle, blankPosition,depth);
        MoveDown(currentPuzzle, blankPosition,depth);
        MoveLeft(currentPuzzle, blankPosition,depth);

    }

    /**
     * called when ExpandNode is called. Determines if a move is legal to make based off the blank, then creates a
     * child Node that is based off of that move being made and adds it to the parents children ArrayList.
     * @param p the currentState of the puzzle in this node
     * @param i tracks the blankPosition of the puzzle
     * @param depth current depth of Node
     */
    public void MoveUp(int[] p, int i, int depth)
    {
        if(i-col >= 0)
        {
            int[] pc = new int[9];
            CopyPuzzle(pc, p);

            int temp = pc[i-3];
            pc[i-3] = pc[i];
            pc[i] = temp;

            Node child = new Node(pc,depth+1, "U");
            children.add(child);
            child.parent = this;
        }
    }

    /**
     * called when ExpandNode is called. Determines if a move is legal to make based off the blank, then creates a
     * child Node that is based off of that move being made and adds it to the parents children ArrayList.
     * @param p the currentState of the puzzle in this node
     * @param i tracks the blankPosition of the puzzle
     * @param depth current depth of Node
     */
    public void MoveRight(int[] p, int i, int depth)
    {
        if(i % col < col - 1)
        {
            int[] pc = new int[9];
            CopyPuzzle(pc, p);

            int temp = pc[i+1];
            pc[i+1] = pc[i];
            pc[i] = temp;

            Node child = new Node(pc,depth+1, "R");
            children.add(child);
            child.parent = this;

        }
    }

    /**
     * called when ExpandNode is called. Determines if a move is legal to make based off the blank, then creates a
     * child Node that is based off of that move being made and adds it to the parents children ArrayList.
     * @param p the currentState of the puzzle in this node
     * @param i tracks the blankPosition of the puzzle
     * @param depth current depth of Node
     */
    public void MoveDown(int[] p, int i, int depth)
    {
        if(i+col< currentPuzzle.length)
        {
            int[] pc = new int[9];
            CopyPuzzle(pc, p);

            int temp = pc[i+3];
            pc[i+3] = pc[i];
            pc[i] = temp;

            Node child = new Node(pc,depth+1, "D");
            children.add(child);
            child.parent = this;
        }
    }

    /**
     * called when ExpandNode is called. Determines if a move is legal to make based off the blank, then creates a
     * child Node that is based off of that move being made and adds it to the parents children ArrayList.
     * @param p the currentState of the puzzle in this node
     * @param i tracks the blankPosition of the puzzle
     * @param depth current depth of Node
     */
    public void MoveLeft(int[] p, int i, int depth)
    {
        if(i % col > 0)
        {
            int[] pc = new int[9];
            CopyPuzzle(pc, p);

            int temp = pc[i-1];
            pc[i-1] = pc[i];
            pc[i] = temp;

            Node child = new Node(pc,depth+1, "L");
            children.add(child);
            child.parent = this;

        }
    }

    /**
     *
     * @param a puzzle to be copied
     * @param b array to hold new copy of puzzle
     */
    public void CopyPuzzle(int[] a, int[] b)
    {
        for(int i = 0; i < b.length; i++)
        {
            a[i] = b[i];
        }
    }

    /**
     *prints the array a in the specified way(to look like a 3x3 matrix or the 8 tile puzzle
     * @param p puzzle array to be printed
     */
    public void PrintArray(int[] p)
    {
        System.out.println("{" + p[0] + " " + p[1] + " " + p[2]);
        System.out.println("{" + p[3] + " " + p[4] + " " + p[5]);
        System.out.println("{" + p[6] + " " + p[7] + " " + p[8] + "}");
    }


    /**
     * compares the Node’s puzzle to the int[] p to see if the puzzles are the same
     * @param p the puzzle that will be compared to the Nodes puzzle
     * @return returns true if the Node's puzzle is the same as the puzzle passed in else returns false
     */
    public boolean IsSamePuzzle(int[] p)
    {
        boolean samePuzzle = true;
        for(int i=0; i< p.length; i++)
        {
            if(currentPuzzle[i] != p[i])
            {
                samePuzzle = false;
            }
        }
        return samePuzzle;
    }

    /**
     * finds the total tiles out of place. This is the method each Node calls during A* to call the heuristic
     * @param goalState
     * @return
     */
    public int FindTotalCost(int[] goalState)
    {
        int cost = 0;
        for(int x =0; x < currentPuzzle.length; x++)
        {
            if(this.currentPuzzle[x] != goalState[x] && this.currentPuzzle[x] != 0)
            {
                cost++;
            }

        }
        return cost;
    }






}
