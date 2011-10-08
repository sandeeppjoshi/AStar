package template;

import problem.Node;
import problem.Problem;

import java.util.ArrayList;

public class AStar {

    public Problem problem;
    public ArrayList<Node> openList;
    public ArrayList<Node> closedList;
    public Node startState;
    public Node goalState;

    public AStar(Problem problem) {
        this.problem = problem;
    }

    public void getOptimalPath(){
        openList.add(startState);
        Node currentState = startState;
        while (!currentState.equals(goalState))
        {
            closedList.add(currentState);
            //This shoild find children of current state,Add them to OL,update parent pointers
            addNewNodes(currentState);
            currentState = problem.getNextState(openList);
        }
    }

    private void addNewNodes(Node currentState) {

        ArrayList<Node> newNodesList = currentState.getChildren();
        for ( Node node : newNodesList)
        {
            if (openList.contains(node))
            {
                Node sameNode = openList.get(openList.indexOf(node));
                if ( sameNode.getGValue() > node.getGValue())
                        sameNode.setParent(currentState);
            }
            else {
                openList.add(node);
            }
        }

    }




}
