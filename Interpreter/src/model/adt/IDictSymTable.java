package model.adt;

import java.util.Hashtable;

public interface IDictSymTable<TKey,TValue> {
    void add(TKey k, TValue v);
    void remove(TKey k);
    void update(TKey k, TValue v);
    TValue returnValue(TKey k);
    boolean isExisting(TKey k);
    String toString();
    Hashtable<TKey, TValue> getDictionary();

    IDictSymTable<TKey,TValue> deepcopy();
    void setContent(Hashtable<TKey, TValue> newMap);
}
