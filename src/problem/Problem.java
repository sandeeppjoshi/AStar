package problem;
import java.util.ArrayList;

public interface Problem {
    void setGoalState(Object o);
    void setStartState(Object o);
    Node getStartState();
    Node getGoalState();
    Node getNextState(ArrayList<Node> openList);
    boolean isSolvable();
}
