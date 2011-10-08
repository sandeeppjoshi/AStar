package eightPuzzle;

import problem.Node;

import java.util.ArrayList;

public class EightPuzzleNode implements Node {


    int state[][] = new int[3][3];
    int whiteTilePositionI;
    int whiteTilePositionJ;
    EightPuzzleNode parent;
    int gValue;
    private int mismatchCount;

    public void setParent(Node parent) {
        this.parent = (EightPuzzleNode) parent;
    }

    public int getGValue() {
        return gValue;

    }

    public int getHeuristicValue(Node goalState) {

        EightPuzzleNode goalNode = (EightPuzzleNode)goalState;

        mismatchCount = 0;
        for (int i =0; i<state[0].length;i++ ){
            for (int j = 0 ; j < state.length;j++)
            {
               if ( state[i][j]!=goalNode.state[i][j])
                        mismatchCount++;
            }
        }

        return mismatchCount;
    }

    public void updateWhiteTilePosition()
    {
        for (int i =0; i<state[0].length;i++ ){
            for (int j = 0 ; j < state.length;j++)
            {
               if ( state[i][j] == 0)
               {
                        whiteTilePositionJ = j;
                        whiteTilePositionI = i;
                        return;
               }
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EightPuzzleNode that = (EightPuzzleNode) o;
        for (int i =0; i<state[0].length;i++ ){
            for (int j = 0 ; j < state.length;j++)
            {
               if ( state[i][j] != that.state[i][j])
               {
                   return false;
               }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = whiteTilePositionI;
        result = 31 * result + whiteTilePositionJ;
        result = 31 * result + mismatchCount;
        return result;
    }

    public ArrayList<Node> getChildren() {

        ArrayList<Node> eightPuzzleNodeArrayList = new ArrayList<Node>();


        int newNodeState[][] = new int[3][3];


        if (whiteTilePositionJ != 0)
        {
            newNodeState = state;
            newNodeState[whiteTilePositionI][whiteTilePositionJ] = newNodeState[whiteTilePositionI][whiteTilePositionJ - 1];
            newNodeState[whiteTilePositionI][whiteTilePositionJ - 1] = 0;
            EightPuzzleNode eightPuzzleNodeLeft = new EightPuzzleNode();
            eightPuzzleNodeLeft.state = newNodeState;
            eightPuzzleNodeLeft.whiteTilePositionI = whiteTilePositionI;
            eightPuzzleNodeLeft.whiteTilePositionJ = whiteTilePositionJ -1;
            eightPuzzleNodeLeft.parent = this;
            eightPuzzleNodeArrayList.add(eightPuzzleNodeLeft);
        }

        if (whiteTilePositionJ != 2)
        {
            newNodeState = state;
            newNodeState[whiteTilePositionI][whiteTilePositionJ] = newNodeState[whiteTilePositionI][whiteTilePositionJ + 1];
            newNodeState[whiteTilePositionI][whiteTilePositionJ + 1] = 0;
            EightPuzzleNode eightPuzzleNodeRight = new EightPuzzleNode();
            eightPuzzleNodeRight.state = newNodeState;
            eightPuzzleNodeRight.whiteTilePositionI = whiteTilePositionI;
            eightPuzzleNodeRight.whiteTilePositionJ = whiteTilePositionJ +1;
            eightPuzzleNodeRight.parent = this;
            eightPuzzleNodeArrayList.add(eightPuzzleNodeRight);
        }


        if (whiteTilePositionI != 0)
        {
            newNodeState = state;
            newNodeState[whiteTilePositionI][whiteTilePositionJ] = newNodeState[whiteTilePositionI -1][whiteTilePositionJ];
            newNodeState[whiteTilePositionI - 1][whiteTilePositionJ] = 0;
            EightPuzzleNode eightPuzzleNodeTop = new EightPuzzleNode();
            eightPuzzleNodeTop.state = newNodeState;
            eightPuzzleNodeTop.whiteTilePositionI = whiteTilePositionI - 1;
            eightPuzzleNodeTop.whiteTilePositionJ = whiteTilePositionJ;
            eightPuzzleNodeTop.parent = this;
            eightPuzzleNodeArrayList.add(eightPuzzleNodeTop);
        }

        if (whiteTilePositionI != 2)
        {
            newNodeState = state;
            newNodeState[whiteTilePositionI][whiteTilePositionJ] = newNodeState[whiteTilePositionI +1][whiteTilePositionJ];
            newNodeState[whiteTilePositionI + 1][whiteTilePositionJ] = 0;
            EightPuzzleNode eightPuzzleNodeDown = new EightPuzzleNode();
            eightPuzzleNodeDown.state = newNodeState;
            eightPuzzleNodeDown.whiteTilePositionI = whiteTilePositionI + 1;
            eightPuzzleNodeDown.whiteTilePositionJ = whiteTilePositionJ;
            eightPuzzleNodeDown.parent = this;
            eightPuzzleNodeArrayList.add(eightPuzzleNodeDown);
        }





        return eightPuzzleNodeArrayList;
    }
}
