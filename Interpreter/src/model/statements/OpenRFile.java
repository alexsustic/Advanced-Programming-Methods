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
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements IStmt {
    private final Exp exp;

    public OpenRFile(Exp e){this.exp = e;}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IFileTable<String, BufferedReader> fileTable = state.getFileTable();
        IDictSymTable<String, Value> symTable = state.getSymTable();
        IHeap<Integer,Value > heap = state.getHeap();
        Value v = this.exp.eval(symTable,heap);
        if(v.getType().toString().equals("string")) {
            String str = ((StrValue) v).getValue();
            if(fileTable.exist(str))
                throw new MyException("This filename is already used!");
            else
            {
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(str));
                    fileTable.add(str, reader);

                }catch(IOException ex){
                    System.out.println(ex.getMessage());
                }

            }

        }
        else
            throw new MyException("This expression doesn't have a string type !");
        return null;
    }

    @Override
    public IDictSymTable<String, Type> typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        Type typExp = this.exp.typecheck(typeEnv);
        if(typExp.equals(new StrType())){
            return typeEnv;
        }
        else{
            throw new MyException("The expression for opening the file doesn't have a string type !");
        }
    }

    @Override
    public String toString() {
        return "OpenRFile(" + this.exp.toString() + ")";
    }
}
