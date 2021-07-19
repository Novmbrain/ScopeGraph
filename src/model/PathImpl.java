package model;

import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Wenjie FU
 * @create 2021-07-19 9:22
 */
public class PathImpl<E> implements Path<E>{

    private List<E> path;

    public PathImpl() {
        this.path = new LinkedList<>();
    }

    public PathImpl(List<E> path) {
        this();
        this.path = new LinkedList<E>(path);
    }


    @Override
    public List<E> getPath() {
        return path;
    }

    @Override
    public void addNodeToPath(E node) {
        path.add(node);
    }

    @Override
    public void removeLastNodeFromPath() {
        ((LinkedList)path).removeLast();
    }

    @Override
    public E getLastNode() {
        return ((LinkedList<E>) path).getLast();
    }

    @Override
    public String toString() {
        return "\n Path{" +
                "path=" + path +
                '}';
    }
}
