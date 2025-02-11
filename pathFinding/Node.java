package pathFinding;

// Constructor
class Node implements Comparable<Node> {
    // Initializes the ints
    int row, col, gCost, hCost, fCost;
    // Initializes the Node
    Node parent;

    public Node(int row, int col, int gCost, int hCost, Node parent) {
        this.row = row;
        this.col = col;
        this.gCost = gCost; // gCost is the actually current movement cost if you only move to the current node from start
        this.hCost = hCost; // estimated cost from this node to the target
        this.fCost = gCost + hCost; // This is used for priority to see what the "overall cost" is
        this.parent = parent; // Reference to previous node in the path
    }

    // Overrides the compareTo class in Comparable as I need it to be custom
    @Override
    public int compareTo(Node other) {
        // Compares and then return the fCost of the current node and the node given
        return Integer.compare(this.fCost, other.fCost);
    }
}
