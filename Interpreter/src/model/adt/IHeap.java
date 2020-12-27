package model.adt;

import exception.MyException;
import model.values.Value;

import java.util.HashMap;
import java.util.Map;

public interface IHeap<TKey,TValue > {
        int add(Integer k, Value v) throws MyException;
        void remove(Integer k);
        void update(Integer k, Value v);
        Value returnValue(Integer k);
        boolean isExisting(Integer k);
        String toString();
        void setContent(Map<Integer, Value> content);
        HashMap<Integer, Value> getContent();
    }

