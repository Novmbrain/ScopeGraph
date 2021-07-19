package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenjie FU
 * @create 2021-07-12 17:17
 */
public class SearchResult {

    private PathImpl<Node> curPathImpl;
    private List<PathImpl> allPathImpl;

    public SearchResult() {
        curPathImpl = new PathImpl<>();
        allPathImpl = new ArrayList<>();
    }

    public void addCurrentPath(){
        allPathImpl.add(new PathImpl<Node>(curPathImpl.getPath()));
    }

    public int pathNumber() {
        return allPathImpl.size();
    }

    public List<PathImpl> getAllPath() {
        return allPathImpl;
    }

    public void addNodeToCurrentPath(Node node) {
        curPathImpl.addNodeToPath(node);
    }

    public void removeLastNodeFromCurrentPath(){
        curPathImpl.removeLastNodeFromPath();
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "allPath=" + allPathImpl +
                '}';
    }
}
