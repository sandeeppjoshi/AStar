package template;

import problem.Node;
import problem.Problem;

import java.util.ArrayList;
import java.util.Set;

public class AStar {

    private Problem problem;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private Node startState;
    private Node goalState;

    public AStar(Problem problem) {
        this.problem = problem;
        openList = new ArrayList<Node>();
        closedList = new ArrayList<Node>();
        startState = problem.getStartState();
        goalState = problem.getGoalState();
    }

    public void getOptimalPath(){
        if (!problem.isSolvable())
        {
            System.out.println("Unsolvable");
            return;
        }

        openList.add(startState);
        Node currentState = startState;
        while (!currentState.equals(goalState))
        {
            closedList.add(currentState);
            openList.remove(currentState);
            addNewNodes(currentState);
            currentState = problem.getNextState(openList);
            System.out.println(currentState.getPath());
            //System.out.println(currentState.getMoveFromParent() + "-------->" +String.valueOf(currentState.getGValue() + currentState.getHeuristicValue(goalState)));
        }
       System.out.println(currentState.getPath());
       System.out.println(" Number of States Expanded : "+closedList.size());
//        String path="";
//        while (currentState != null)
//        {
//          path = currentState.getMoveFromParent() + " " + path;
//          currentState = currentState.getParent();
//        }
//        System.out.println(path);
    }

    private void addNewNodes(Node currentState) {

        ArrayList<Node> newNodesList = currentState.getChildren();
        for ( Node node : newNodesList)
        {

            if (openList.contains(node))
            {
                Node sameNode = openList.get(openList.indexOf(node));
                if ( sameNode.getGValue() > node.getGValue())
                {
                    sameNode.setParent(currentState);
                }
            }
            else if (closedList.contains(node))
            {
                Node sameNode = closedList.get(closedList.indexOf(node));
                if ( sameNode.getGValue() > node.getGValue())
                {
                    sameNode.setParent(currentState);
                }
            }
            else {
                openList.add(node);
            }
        }

    }
}
