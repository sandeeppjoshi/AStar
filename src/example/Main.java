package example;

import eightPuzzle.EightPuzzleProblem;
import mandc.MandCNode;
import mandc.MandCProblem;
import problem.Problem;
import template.AStar;

public class Main {
    public static void main(String args[]){



        //**********************UNCOMMENT FOLLOWING FOR EIGHT PUZZLE***********************

        Problem eightPuzzleProblem = new EightPuzzleProblem();
        int [][] startState = {{1,2,3},{4,7,5},{0,6,8}};

        //This input clearly shown the advantage of Manhattan Distance over mismatched tiles Heuristic
        //int [][] startState = {{1,2,3},{5,7,6},{4,8,0}};
        int [][] goalState = {{1,2,3},{4,0,5},{6,7,8}};
        eightPuzzleProblem.setStartState(startState);
        eightPuzzleProblem.setGoalState(goalState);
        AStar aStar = new AStar(eightPuzzleProblem);
        aStar.getOptimalPath();

        //***********************EIGHT PUZZLE END***********************************************

        //***********************UNCOMMENT FOLLOWING FOR MandC*****************************

//        Problem mAndCProblem = new MandCProblem();
//        int [] startState = {3,3, MandCProblem.LEFT};
//        int [] goalState = {0,0, MandCProblem.RIGHT};
//        mAndCProblem.setStartState(startState);
//        mAndCProblem.setGoalState(goalState);
//        AStar aStar = new AStar(mAndCProblem);
//        aStar.getOptimalPath();





        //*********************END MandC********************************************************
    }

}
