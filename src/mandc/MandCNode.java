package mandc;

import problem.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MandCNode implements Node{

    int [] state = new int[3];
    public MandCNode parent;
    private String moveFromParent;


    public void setParent(Node parent) {
        this.parent = (MandCNode) parent;
    }

    public int getGValue() {
        if (parent == null)
                return 0;
        return parent.getGValue() + 1;
    }

    public String getMoveFromParent() {
        return moveFromParent;
    }

    public void setMoveFromParent(String moveFromParent) {
        this.moveFromParent = moveFromParent;
    }

    public void copyState(Object stateToCopy) {
        int [] mAndC = (int [])stateToCopy;
        for (int i = 0 ;i < state.length ; i++){
            this.state[i] = mAndC[i];

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MandCNode mandCNode = (MandCNode) o;

        if (!Arrays.equals(state, mandCNode.state)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public int getHeuristicValue(Map<Integer, String> goalState) {

        Integer miCount = Integer.parseInt(goalState.get(0));
        Integer cbCount = Integer.parseInt(goalState.get(1));
        Integer boatPosition = Integer.parseInt(goalState.get(2));
//        return Math.abs(state[0]-miCount) + Math.abs(state[1] - cbCount) + Math.abs(state[2] - boatPosition) - MandCProblem.BOATCAPACITY -1 ;
        return 1;
    }

    public ArrayList<Node> getChildren() {
        ArrayList<Node> mAndCNodeSet = new ArrayList<Node>();
        for(int m=0;m<=MandCProblem.MAXMIS;m++){
            for(int c=0;c<=MandCProblem.MAXCAN;c++){
                if( 0 < m+c && m+c <= MandCProblem.BOATCAPACITY ){
                    if(movePossible(m,c))
                        makeChild(mAndCNodeSet,m,c,state[2],"M"+m+"C"+c);
                }
            }
        }
        return mAndCNodeSet;
    }

    private boolean movePossible(int mNumber, int cNumber) {
        int leftMi;
        int leftCb;
        int rightMi;
        int rightCb;
        if (state [2] == MandCProblem.LEFT){
            if (!(state[0] >= mNumber && state[1] >= cNumber))
                return false;
            leftMi = state[0] - mNumber;
            leftCb = state[1] - cNumber;
            rightCb = MandCProblem.MAXCAN - state[1] + cNumber;
            rightMi = MandCProblem.MAXMIS - state[0] + mNumber;

        }
        else {
            if (!(MandCProblem.MAXMIS-state[0] >= mNumber && MandCProblem.MAXCAN-state[1] >= cNumber))
                return false;
            leftMi = state[0] + mNumber;
            leftCb = state[1] + cNumber;
            rightCb = MandCProblem.MAXCAN - state[1] - cNumber;
            rightMi = MandCProblem.MAXMIS - state[0] - mNumber;

        }
          boolean possible;
            if ( leftMi != 0)
                possible = leftMi >= leftCb;
            else
                possible = true;
            if (rightMi != 0)
                possible = possible & (rightCb <= rightMi);
            return possible;

    }

    private void makeChild(ArrayList<Node> mAndCNodeSet, int mNumber, int cNumber, int boatPosition, String move) {
       MandCNode child = new MandCNode();
       child.state[2] = 1 - this.state[2];
       if(state[2] == MandCProblem.LEFT)
       {
            child.state[0] = state[0]- mNumber;
            child.state[1] = state[1] - cNumber;
       }
       if(state[2] == MandCProblem.RIGHT)
       {
            child.state[0] = state[0]+ mNumber;
           child.state[1] = state[1] + cNumber;
       }
       child.parent = this;
       child.setMoveFromParent(move);
       mAndCNodeSet.add(child);
    }

    public String getPath() {
        if(parent == null)
            return "";
        return parent.getPath()+"--->"+moveFromParent;
    }

    public Node getParent() {
        return parent;
    }
}
