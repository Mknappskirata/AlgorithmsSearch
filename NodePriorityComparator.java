import java.util.Comparator;

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