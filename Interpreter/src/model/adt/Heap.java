package model.adt;

import exception.MyException;
import model.values.Value;

import java.util.HashMap;
import java.util.Map;

public class Heap<TKey,TValue> implements IHeap<TKey,TValue> {
    private HashMap<Integer, Value> heap;
    private int freeLocation = 1;

    public Heap(){this.heap = new HashMap<Integer, Value>();}


    @Override
    public int add(Integer k, Value v) throws MyException {
        if(k == 0)
           throw new MyException("Invalid address !");

        int previousFreeLocation = this.freeLocation;

        if(isExisting(k)) {
            this.heap.put(freeLocation, v);
            freeLocation = 1;
        }
        else
            this.heap.put(k,v);

        while(isExisting(freeLocation))
            freeLocation = freeLocation + 1;
        return previousFreeLocation;
    }

    @Override
    public void remove(Integer k) {

        this.heap.remove(k);
    }

    @Override
    public void update(Integer k, Value v)  {

        this.heap.replace(k,returnValue(k), v);
    }

    @Override
    public Value returnValue(Integer k) {
        return this.heap.get(k);
    }

    @Override
    public boolean isExisting(Integer k) {
        return this.heap.containsKey(k);
    }

    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<Integer, Value> t : this.heap.entrySet())
            s += t.getKey().toString() + "->" + t.getValue().toString() + "\n" ;
        return s;
    }

    @Override
    public void setContent(Map<Integer, Value> content) {
        Map<Integer, Value> heap1 = this.heap;
        heap1 = content;
        this.heap = new HashMap<Integer, Value>(heap1);
    }

    @Override
    public HashMap<Integer, Value> getContent() {
        return this.heap;
    }
}
