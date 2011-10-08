package eightPuzzle;

import problem.Node;
import problem.Problem;

import java.util.ArrayList;

public class EightPuzzleProblem implements Problem{


    private EightPuzzleNode startState;
    private EightPuzzleNode goalState;

    public void setGoalState(int[][] goalState) {
        this.goalState = new EightPuzzleNode();
        this.goalState.state = goalState;
        this.goalState.updateWhiteTilePosition();
    }

    public void setStartState(int [][]startState) {
        this.startState = new EightPuzzleNode();
        this.startState.state = startState;
        this.startState.updateWhiteTilePosition();
        this.startState.parent = null;

    }

    public Node getNextState(ArrayList<Node> openList) {

        int min = 8000000;
        Node returnNode = null;
        for (Node node : openList)
        {
           if (min > node.getHeuristicValue(goalState))
                returnNode = node;
        }
        return returnNode;
    }


}
