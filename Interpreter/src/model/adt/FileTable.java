package model.adt;

import exception.MyException;

import java.util.Hashtable;
import java.util.Map;

public class FileTable<TKey, TBufferedReader> implements IFileTable<TKey, TBufferedReader> {
    private final Hashtable<TKey, TBufferedReader> fileTable;

    public FileTable(){ this.fileTable = new Hashtable<TKey, TBufferedReader>();
    }

    public Hashtable<TKey, TBufferedReader> getTable(){return this.fileTable;}

    @Override
    public void add(TKey k, TBufferedReader br) throws MyException {
      if(exist(k))
          throw new MyException("There is already a file with this key!");
      else
          this.fileTable.put(k,br);
    }

    @Override
    public boolean exist(TKey k) {
        return this.fileTable.containsKey(k);
    }

    @Override
    public TBufferedReader getBufferedReader(TKey k) {
        return this.fileTable.get(k);
    }

    @Override
    public void remove(TKey k) {
       this.fileTable.remove(k);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<TKey, TBufferedReader> h : this.fileTable.entrySet()) {
            s.append("File ").append(h.getKey()).append("\n");
        }
        return s.toString();
    }
}
