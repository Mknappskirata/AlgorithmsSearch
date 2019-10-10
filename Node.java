import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node
{
    public List<Node> children = new ArrayList<>();
    public Node parent;
    public int[] puzzle = new int[9];
    public int depth;
    public int x = 0;
    public int col = 3;
    private int totalCost;

    public Node(int[] p, int depth)
    {
        puzzle = p;
        this.depth = depth;
    }

    public int GetTotalCost() {
        return totalCost;
    }

    public void SetTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public boolean GoalTest(int[] goalState)
    {
        for(int i = 0; i<goalState.length; i++)
        {
            if(puzzle[i] != goalState[i])
            {
                return false;
            }
        }
        return true;
    }

    public void ExpandNode(int depth)
    {
        for(int i = 0; i<puzzle.length; i++)
        {
            if(puzzle[i] == 0)
            {
                x = i;
            }
        }

        MoveUp(puzzle, x, depth);
        MoveRight(puzzle, x,depth);
        MoveDown(puzzle, x,depth);
        MoveLeft(puzzle, x,depth);

    }

    public void MoveUp(int[] p, int i, int depth)
    {
        if(i-col > 0)
        {
            int[] pc = new int[9];
            CopyPuzzle(pc, p);

            int temp = pc[i-3];
            pc[i-3] = pc[i];
            pc[i] = temp;

            Node child = new Node(pc,depth+1);
            children.add(child);
            child.parent = this;
        }
    }

    public void MoveRight(int[] p, int i, int depth)
    {
        if(i % col < col - 1)
        {
            int[] pc = new int[9];
            CopyPuzzle(pc, p);

            int temp = pc[i+1];
            pc[i+1] = pc[i];
            pc[i] = temp;

            Node child = new Node(pc,depth+1);
            children.add(child);
            child.parent = this;

        }
    }

    public void MoveDown(int[] p, int i, int depth)
    {
        if(i+col< puzzle.length)
        {
            int[] pc = new int[9];
            CopyPuzzle(pc, p);

            int temp = pc[i+3];
            pc[i+3] = pc[i];
            pc[i] = temp;

            Node child = new Node(pc,depth+1);
            children.add(child);
            child.parent = this;
        }
    }

    public void MoveLeft(int[] p, int i, int depth)
    {
        if(i % col > 0)
        {
            int[] pc = new int[9];
            CopyPuzzle(pc, p);

            int temp = pc[i-1];
            pc[i-1] = pc[i];
            pc[i] = temp;

            Node child = new Node(pc,depth+1);
            children.add(child);
            child.parent = this;

        }
    }

    public void CopyPuzzle(int[] a, int[] b)
    {
        for(int i = 0; i < b.length; i++)
        {
            a[i] = b[i];
        }
    }

    public void PrintArray(int[] p)
    {
        System.out.println(Arrays.toString(p));
    }


    public boolean IsSamePuzzle(int[] p)
    {
        boolean samePuzzle = true;
        for(int i=0; i< p.length; i++)
        {
            if(puzzle[i] != p[i])
            {
                samePuzzle = false;
            }
        }
        return samePuzzle;
    }

    public int FindTotalCost(int[] goalState)
    {
        int cost = 0;
        for(int x =0; x < puzzle.length; x++)
        {
            if(puzzle[x] != goalState[x])
            {
                cost++;
            }

        }
        return cost;
    }






}
