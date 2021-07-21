package model.searchresult;

import java.util.List;

/**
 * @author Wenjie FU
 * @create 2021-07-19 10:51
 */
public interface Path<E> {
    public List<E> getPath();

    public void addNodeToPath(E node);

    public void removeLastNodeFromPath();

    public E getLastNode();

}
