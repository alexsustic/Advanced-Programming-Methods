package model.statements;

import exception.MyException;
import model.PrgState;
import model.adt.*;
import model.types.Type;
import model.values.Value;

import java.io.BufferedReader;
import java.util.Map;

public class ForkStmt implements IStmt {
    private final IStmt stmt;

    public ForkStmt(IStmt st) {this.stmt = st;}

    @Override
    public String toString(){ return "fork("+ this.stmt.toString() +")";}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDictSymTable<String, Value> symTable = state.getSymTable();
        IHeap<Integer,Value> heap = state.getHeap();
        IListOut<Value> out = state.getOut();
        IFileTable<String,BufferedReader> fileTable = state.getFileTable();
        IExeStack<IStmt> newExeStack = new ExeStack<>();
        IDictSymTable<String, Value> newSymTable = new DictSymTable<>();
        for(Map.Entry<String, Value> entry : symTable.getDictionary().entrySet()) {
            newSymTable.update(entry.getKey(), entry.getValue());
        }

        return new PrgState(newExeStack,newSymTable,out,heap,fileTable,this.stmt);

    }

    @Override
    public IDictSymTable<String, Type> typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        stmt.typecheck(typeEnv.deepcopy());
        return typeEnv;
    }
}
