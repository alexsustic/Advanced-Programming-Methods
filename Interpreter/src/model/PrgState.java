package model;
import exception.MyException;
import model.adt.*;
import model.values.Value;
import model.statements.IStmt;

import java.io.BufferedReader;

public class PrgState {

    IExeStack<IStmt> exeStack;
    IDictSymTable<String, Value> symTable;
    IListOut <Value > out;
    IStmt initialProgram;
    IHeap<Integer,Value> heap;
    IFileTable<String, BufferedReader> fileTable;
    int id;
    static int defaultID = 0;


    public PrgState(IExeStack<IStmt> stack, IDictSymTable<String, Value> dict, IListOut<Value> list, IHeap<Integer, Value> h,IFileTable<String, BufferedReader> f, IStmt prg){
        this.exeStack = stack;
        this.symTable = dict;
        this.out = list;
        this.fileTable = f;
        this.heap = h;
        this.initialProgram = prg;
        this.exeStack.push(this.initialProgram);
        this.id = getSynchronizedID();
    }
    public boolean isNotCompleted(){
        if (this.exeStack.isEmpty())
            return false;
        return true;
    }

    public static synchronized int getSynchronizedID(){
        ++ defaultID;
        return defaultID;
    }

    public int getId() {return this.id;}

    public PrgState oneStep() throws MyException{
        IExeStack<IStmt> exeStack = this.getExeStack();
        if(exeStack.isEmpty())
            throw new MyException("The stack of the current program is empty!");
        IStmt currentStatement = exeStack.pop();
        return  currentStatement.execute(this);

    }


    public void setExeStack(IExeStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(IDictSymTable<String, Value> symTable) {
        this.symTable = symTable;
    }

    public void setOut(IListOut<Value> out) {
        this.out = out;
    }

    public void setFileTable(FileTable<String, BufferedReader> f) {this.fileTable = f;}

    public void setHeap(IHeap<Integer,Value> h){this.heap = h;}

    public void setInitialProgram(IStmt initialProgram) {
        this.initialProgram = initialProgram;
    }

    public IExeStack<IStmt> getExeStack() {
        return exeStack;
    }

    public IDictSymTable<String, Value> getSymTable() {
        return symTable;
    }

    public IListOut<Value> getOut() {
        return out;
    }

    public IFileTable<String, BufferedReader> getFileTable(){return this.fileTable;}

    public IStmt getInitialProgram() {
        return initialProgram;
    }

    public IHeap<Integer,Value> getHeap(){return this.heap;}

    @Override
    public String toString(){
        return "--------------------------------\nID: " + this.getId() +"\n"+ "ExeStack: " + this.exeStack.toString() + "\n" + "SymTable: " +this.symTable.toString() + "\n" + "Out: " +this.out.toString() + "\n" + "FileTable: " +this.fileTable.toString() +"\n" + "Heap: " +this.heap.toString() +"\n--------------------------------\n\n\n";
    }

}