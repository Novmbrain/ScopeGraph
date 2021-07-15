package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenjie FU
 * @create 2021-07-12 17:17
 */
public class SearchResult {
    private boolean curResult;
//    private List<Boolean> allResult;

    private List<Node> curPath;
    private List<List<Node>> allPath;

    public SearchResult() {
//        allResult = new ArrayList<>();
        curPath = new ArrayList<>();
        allPath = new ArrayList<>();
    }

    public SearchResult(boolean result, List<Node> path) {
        this.curResult = result;
        this.curPath = path;
    }

    public void addNodeToPath(Node node) {
        curPath.add(node);
    }

    public void removeLastNodeInPath() {
        curPath.remove(curPath.size() - 1);
    }

    public void addCurrentPath(){
        allPath.add(new ArrayList<Node>(curPath));
//        allResult.add(true);
    }

    public void setCurResult(boolean curResult) {
        this.curResult = curResult;
    }

    public boolean isCurResult() {
        return curResult;
    }

    public List<Node> getPath() {
        return curPath;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "allPath=" + allPath +
                '}';
    }
}
