package model.adt;

import model.PrgState;

import java.util.LinkedList;
import java.util.Queue;

public class ListOut<T> implements IListOut<T> {
    private Queue<T> list;
    public ListOut(){this.list = new LinkedList<T>();}

    @Override
    public boolean isEmpty()
    {
        if(this.list.isEmpty())
            return true;
        return false;
    }

    @Override
    public T pop() {
        return this.list.poll();
    }

    @Override
    public void push(T elem) {
       this.list.add(elem);
    }

    @Override
    public String toString() {
        String s = "";
        for (T t : this.list)
            s += t.toString() + "\n";
        return s;
    }
}
