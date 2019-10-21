import java.util.Comparator;
/**
 * @author John and Matthew Knapp
 * Node is our class that stores information about each node examined in our search tree.
 */
public class NodePriorityComparator implements Comparator<Node> {

    @Override
    public int compare(Node x, Node y) {
        if (x.GetTotalCost() > y.GetTotalCost()) {
            return 1;
        }
        if (x.GetTotalCost() < y.GetTotalCost()) {
            return -1;
        }
        return 0;
    }
}