package model.adt;

import java.util.*;

public class DictSymTable<TKey,TValue> implements IDictSymTable<TKey,TValue> {
    private Hashtable<TKey, TValue> dict;

    public DictSymTable() {
        this.dict = new Hashtable<TKey, TValue>();
    }

    @Override
    public void add(TKey k, TValue v) {
        this.dict.put(k, v);
    }

    @Override
    public void remove(TKey k) {
        this.dict.remove(k);
    }

    @Override
    public void update(TKey k, TValue v) {

        this.dict.put(k, v);

    }

    @Override
    public TValue returnValue(TKey k) {
        return this.dict.get(k);
    }

    @Override
    public boolean isExisting(TKey k) {
        return this.dict.containsKey(k);
    }

    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<TKey, TValue> t : this.dict.entrySet())
            s += t.getKey().toString() + "->" + t.getValue().toString() + "\n" ;
        return s;
    }

    @Override
    public Hashtable<TKey, TValue> getDictionary(){return this.dict;}

    @Override
    public IDictSymTable<TKey, TValue> deepcopy() {
        Hashtable<TKey, TValue> newMap = new Hashtable<>(this.dict);
        DictSymTable<TKey, TValue> newDict = new DictSymTable<>();
        newDict.setContent(newMap);
        return newDict;
    }

    @Override
    public void setContent(Hashtable<TKey, TValue> newMap) {
        this.dict = newMap;
    }


}
