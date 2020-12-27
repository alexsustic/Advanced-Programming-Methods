package model.adt;

import java.util.Stack;

public class ExeStack<T> implements IExeStack<T> {
    private Stack<T> stack;

    public ExeStack(){this.stack = new Stack<T>();}
    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
       this.stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public Stack<T> returnStack() {
        return this.stack;
    }

    @Override
    public String toString(){
        String s="";
        for(T t: this.stack)
            s += t.toString() + "\n";
        return s;
    }

}
