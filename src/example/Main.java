package example;

import eightPuzzle.EightPuzzleProblem;
import problem.Problem;
import template.AStar;

public class Main {
    public static void main(String args[]){

        Problem eightPuzzleProblem = new EightPuzzleProblem();
        int [][] startState = {{1,2,3},{4,5,6},{8,7,0}};
        int [][] goalState = {{1,2,3},{4,5,6},{7,8,0}};
        eightPuzzleProblem.setStartState(startState);
        eightPuzzleProblem.setGoalState(goalState);
        AStar aStar = new AStar(eightPuzzleProblem);
        aStar.getOptimalPath();


    }

}
