package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenjie FU
 * @create 2021-07-12 17:17
 */
public class SearchResult {
    private boolean result;
    private List<Node> path;

    public SearchResult() {
        path = new ArrayList<>();
    }

    public SearchResult(boolean result, List<Node> path) {
        this.result = result;
        this.path = path;
    }

    public void addPath(Node node) {
        path.add(node);
    }

    public void removeLastPath() {
        path.remove(path.size() - 1);
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public List<Node> getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "result=" + result +
                ", path=" + path +
                '}';
    }
}
