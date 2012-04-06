package eightPuzzle;

import problem.Node;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class EightPuzzleNode implements Node {


    int state[][] = new int[3][3];
    int whiteTilePositionI;
    int whiteTilePositionJ;
    EightPuzzleNode parent;
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

    public int getHeuristicValue(Map<Integer, String> goalStateMap) {
        //H1
//        numberOfMisplacedTiles(goalStateMap);
        //H2
        manhattanDistance(goalStateMap);
//        return mismatchCount ;
//        return 0;
        //H3
        return overEstimate();
//        H4
//       Random random = new Random();
//        if ( mismatchCount >0)
//            return random.nextInt(mismatchCount);
//        else
//            return 0;
    }

    private int overEstimate() {
        return mismatchCount*5000;
//        Random r = new Random();
//
//        if ( mismatchCount == 0)
//            return 0;
//        if (mismatchCount ==2 && this.getGValue() == 1)
//            return 19;
//        if (mismatchCount ==4 && this.getGValue() == 1)
//            return 1;
//        else return r.nextInt(20);
    }

    private void numberOfMisplacedTiles(Map<Integer, String> goalStateMap) {
        mismatchCount = 0;
        for (int i =0; i<state[0].length;i++ ){
            for (int j = 0 ; j < state.length;j++)
            {
               if (goalStateMap.get(state[i][j]) != i+","+j)
                        mismatchCount++;
            }
        }
    }

    private void manhattanDistance(Map<Integer, String> goalStateMap){
        mismatchCount = 0;
        for (int i =0; i<state[0].length;i++ ){
            for (int j = 0 ; j < state.length;j++)
            {
               int iGoal = Integer.parseInt(goalStateMap.get(state[i][j]).split(",")[0]);
               int jGoal = Integer.parseInt(goalStateMap.get(state[i][j]).split(",")[1]);
               mismatchCount = mismatchCount + Math.abs(i-iGoal)+Math.abs((j-jGoal));
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

        if (whiteTilePositionJ != state[0].length -1)
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
        if (whiteTilePositionI != state[0].length -1 )
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
