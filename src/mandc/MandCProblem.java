package mandc;

import eightPuzzle.EightPuzzleNode;
import problem.Node;
import problem.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MandCProblem implements Problem{


    private MandCNode startState;
    private MandCNode goalState;
    private Map<Integer,String> goalStateMap;
    public static final int MAXCAN = 3;
    public static final int MAXMIS = 3;
    public static final int LEFT = 1;
    public static final int RIGHT = 0;
    public static final int BOATCAPACITY = 2;

    public void setGoalState(Object goalState) {
        int [] mAndCGoalState = ( int []) goalState;
        this.goalState = new MandCNode();
        this.goalState.state = mAndCGoalState;
    }

    public void setStartState(Object startState) {
       int [] mAndCStartState = ( int []) startState;
        this.startState = new MandCNode();
        this.startState.setMoveFromParent("");
        this.startState.state = mAndCStartState;
        this.startState.parent = null;
    }

    public Node getStartState() {
        return startState;
    }

    public Node getGoalState() {
        goalStateMap = new HashMap<Integer, String>();
        for(int i=0 ; i< goalState.state.length;i++){
               goalStateMap.put(i,Integer.toString(goalState.state[i]));
        }
        return goalState;
    }

    public Node getNextState(ArrayList<Node> openList) {
        int min = 8000000;
        Node returnNode = null;
        for (Node node : openList)
        {
           int hValue = node.getHeuristicValue(goalStateMap);
           if (min > hValue+node.getGValue())
           {
               min = hValue+node.getGValue();
               returnNode = node;
           }
        }
        return returnNode;
    }

    public boolean isSolvable() {
        return true;
    }
}
