package model.statements;

import exception.MyException;
import model.PrgState;
import model.adt.IDictSymTable;
import model.adt.IFileTable;
import model.adt.IHeap;
import model.expression.Exp;
import model.types.StrType;
import model.types.Type;
import model.values.StrValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStmt {
    private final Exp exp;

    public CloseRFile(Exp e){this.exp = e;}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IFileTable<String, BufferedReader> fileTable = state.getFileTable();
        IDictSymTable<String, Value> symTable = state.getSymTable();
        IHeap<Integer,Value > heap = state.getHeap();
        StrValue val = (StrValue)this.exp.eval(symTable,heap);
        String key = val.getValue();
        try {
            if (fileTable.getBufferedReader(key) != null) {
                BufferedReader bufferedReader = fileTable.getBufferedReader(key);
                bufferedReader.close();
                fileTable.remove(key);
            } else
                throw new MyException("A file that doesn't exist can't  be closed !");
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public IDictSymTable<String, Type> typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        Type typExp = exp.typecheck(typeEnv);
        if(typExp.equals(new StrType())){
            return typeEnv;
        }
        else{
            throw new MyException("The expression for closing the file is not String");
        }
    }
}
