package problem;

import eightPuzzle.EightPuzzleNode;

import java.util.ArrayList;

public interface Node {


    public void setParent(Node parent);
    public int getGValue();
    public int getHeuristicValue(Node goalState);
    public ArrayList<Node> getChildren();
}
