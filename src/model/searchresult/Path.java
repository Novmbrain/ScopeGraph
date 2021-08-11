package model.searchresult;

import java.util.List;

/**
 * @author Wenjie FU
 * @create 2021-07-19 10:51
 */
public interface Path<E> {

     List<E> getPath();

     void addNodeToPath(E node);

     void removeLastNodeFromPath();

     E getLastNode();

}
