package model.adt;

import model.PrgState;

public interface IListOut<T> {
    boolean isEmpty();
    T pop();
    void push(T elem);
    String toString();
}
