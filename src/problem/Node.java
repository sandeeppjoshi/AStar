package problem;

import eightPuzzle.EightPuzzleNode;

import java.util.ArrayList;

public interface Node {


    public void setParent(Node parent);
    public int getGValue();
    public void setGValue(int gValue);
    public String getMoveFromParent();
    public void setMoveFromParent(String moveFromParent);
    public void copyState(Object stateToCopy);
    public int getHeuristicValue(Node goalState);
    public ArrayList<Node> getChildren();

    Node getParent();
}
