package model.adt;

import java.util.Stack;

public interface IExeStack<T> {
    T pop();
    void push(T v);
    boolean isEmpty();
    Stack<T> returnStack();
    String toString();
}
