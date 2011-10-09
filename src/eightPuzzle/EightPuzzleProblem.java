package eightPuzzle;

import problem.Node;
import problem.Problem;

import java.util.ArrayList;

public class EightPuzzleProblem implements Problem{


    private EightPuzzleNode startState;
    private EightPuzzleNode goalState;

    public void setGoalState(Object goalState) {
        int [][] eightPuzzleGoalState = ( int [][]) goalState;
        this.goalState = new EightPuzzleNode();
        this.goalState.state = eightPuzzleGoalState;
        this.goalState.updateWhiteTilePosition();
    }

    public void setStartState(Object startState) {
        int [][] eightPuzzleStartState = ( int [][]) startState;
        this.startState = new EightPuzzleNode();
        this.startState.setMoveFromParent("");
        this.startState.state = eightPuzzleStartState;
        this.startState.updateWhiteTilePosition();
        this.startState.parent = null;

    }

    public Node getStartState() {
        return startState;
    }

    public Node getGoalState() {
        return goalState;
    }

    public Node getNextState(ArrayList<Node> openList) {

        int min = 8000000;
        Node returnNode = null;
        for (Node node : openList)
        {
           int hValue = node.getHeuristicValue(goalState);
           if (min > hValue+node.getGValue())
           {
               min = hValue+node.getGValue();
               returnNode = node;
           }
        }
        return returnNode;
    }

    public boolean isSolvable() {

        int [] list = new int[9];
        for (int i = 0,k=0;i< 3 ; i++)
        {
            for (int j=0;j<3;j++,k++)
            {
                list[k] = startState.state[i][j];
            }
        }

        int inversions=0;
        for ( int i=0;i<list.length;i++)
        {
            for (int j=i+1;j<list.length;j++)
            {

                if ( list [i] > list[j])
                    inversions++;
            }

        }
        if ( inversions % 2 == 0)
            return true;
        return false;
    }


}
