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
    private String moveFromParent;

    public void setParent(Node parent) {
        this.parent = (EightPuzzleNode) parent;
    }

    public int getGValue() {
        if (parent == null)
            return 0;
        return parent.getGValue()+1;

    }

    public void copyState(Object stateToCopy) {
        int [][] eightPuzzleState = (int [][])stateToCopy;

        for (int i =0; i<state[0].length;i++ ){
            for (int j = 0 ; j < state.length;j++)
            {
              state[i][j] = eightPuzzleState[i][j];
            }
        }
    }

    public int getHeuristicValue(Node goalState) {

        EightPuzzleNode goalNode = (EightPuzzleNode)goalState;

        numberOfMisplacedTiles(goalNode);

        return mismatchCount;
    }

    private void numberOfMisplacedTiles(EightPuzzleNode goalNode) {
        mismatchCount = 0;
        for (int i =0; i<state[0].length;i++ ){
            for (int j = 0 ; j < state.length;j++)
            {
               if ( state[i][j]!=goalNode.state[i][j] && state[i][j] != 0)
                        mismatchCount++;
            }
        }
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
        return 0;
    }

    public ArrayList<Node> getChildren() {

        ArrayList<Node> eightPuzzleNodeSet = new ArrayList<Node>();
        int jOffset;
        int iOffset;
        String move;
        if (whiteTilePositionJ != 0)
        {
            jOffset = -1;
            iOffset = 0;
            move = "left";
            makeChild(eightPuzzleNodeSet, jOffset, iOffset ,move);
        }

        if (whiteTilePositionJ != 2)
        {
            jOffset = 1;
            iOffset = 0;
            move = "right";
            makeChild(eightPuzzleNodeSet, jOffset, iOffset, move);
        }
        if (whiteTilePositionI != 0)
        {
            move = "up";
            jOffset = 0;
            iOffset = -1;
            makeChild(eightPuzzleNodeSet, jOffset, iOffset, move);
        }
        if (whiteTilePositionI != 2)
        {
            move = "down";
            jOffset = 0;
            iOffset = 1;
            makeChild(eightPuzzleNodeSet, jOffset, iOffset, move);

        }
        return eightPuzzleNodeSet;
    }

    public String getPath() {
        if(parent == null)
            return "";
        return parent.getPath()+"--->"+moveFromParent;
    }

    public Node getParent() {
        return parent;
    }

    private void makeChild(ArrayList<Node> eightPuzzleNodeArrayList, int jOffset, int iOffset, String move) {

        EightPuzzleNode eightPuzzleNodeChild = new EightPuzzleNode();
        eightPuzzleNodeChild.copyState(state);
        eightPuzzleNodeChild.state[whiteTilePositionI][whiteTilePositionJ] = eightPuzzleNodeChild.state[whiteTilePositionI + iOffset][whiteTilePositionJ + jOffset];
        eightPuzzleNodeChild.state[whiteTilePositionI + iOffset][whiteTilePositionJ + jOffset] = 0;
        eightPuzzleNodeChild.whiteTilePositionI = whiteTilePositionI + iOffset ;
        eightPuzzleNodeChild.whiteTilePositionJ = whiteTilePositionJ + jOffset ;
        eightPuzzleNodeChild.parent = this;
        eightPuzzleNodeChild.setMoveFromParent(move);
        eightPuzzleNodeArrayList.add(eightPuzzleNodeChild);
    }

    public String getMoveFromParent() {
        return moveFromParent;
    }

    public void setMoveFromParent(String moveFromParent) {
        this.moveFromParent = moveFromParent;
    }
}
