package template;

import problem.Node;
import problem.Problem;

import java.util.ArrayList;
import java.util.Set;

import static java.lang.System.exit;

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
            addOrRedirect(closedList, currentState);
            openList.remove(currentState);
            addNewNodes(currentState);
//            currentState = openList.get(0);
            currentState = problem.getNextState(openList);
            if(currentState == null)
            {
                System.out.println("No Solution Possible!!!");
                exit(1);
            }
        }
       System.out.println(currentState.getPath());
       System.out.println(" Number of States Expanded : "+closedList.size());
    }

    private void addOrRedirect(ArrayList<Node> closedList, Node currentState) {

        if ( closedList.contains(currentState))
        {
            System.out.println(" Redirecting!");
          Node sameNode = closedList.get(closedList.indexOf(currentState));
          if ( sameNode.getGValue() > currentState.getGValue())
            {
                sameNode.setParent(currentState.getParent());
                System.out.println("Redirection");
            }
        }
        else
            closedList.add(currentState);
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
//
//            if (closedList.contains(node))
//            {
//                Node sameNode = closedList.get(closedList.indexOf(node));
//                if ( sameNode.getGValue() > node.getGValue())
//                {
//                    System.out.print("GGGGG");
//                    sameNode.setParent(currentState);
//                }
//            }

            if(!closedList.contains(node)) {
                openList.add(node);
            }
        }

    }
}
