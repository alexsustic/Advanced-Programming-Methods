package model.adt;

import exception.MyException;

public interface IFileTable<TKey, TBufferedReader> {
    void add(TKey k, TBufferedReader br) throws MyException;
    boolean exist(TKey k);
    TBufferedReader getBufferedReader(TKey k);
    void remove(TKey k);

}
