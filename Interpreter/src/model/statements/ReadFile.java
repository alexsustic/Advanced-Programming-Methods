package model.statements;

import exception.MyException;
import model.PrgState;
import model.adt.IDictSymTable;
import model.adt.IFileTable;
import model.adt.IHeap;
import model.expression.Exp;
import model.types.IntType;
import model.types.StrType;
import model.types.Type;
import model.values.IntValue;
import model.values.StrValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt {
    private final Exp exp;
    private final String varName;

    public ReadFile(Exp e, String s){this.exp = e; this.varName = s;}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IFileTable<String, BufferedReader> fileTable = state.getFileTable();
        IDictSymTable<String, Value> symTable = state.getSymTable();
        IHeap<Integer,Value > heap = state.getHeap();
        if(symTable.isExisting(this.varName))
        {
            StrValue val = (StrValue) this.exp.eval(symTable,heap);
            StrType type = (StrType) val.getType();
            if(type.toString().equals("string"))
            {
                String fileName = val.getValue();
                try{
                    BufferedReader fileReader = fileTable.getBufferedReader(fileName);
                    String line = fileReader.readLine();
                    if (line == null) {
                        symTable.update(varName, new IntValue(0));
                    } else {
                        String newStr = line.replace("<NL>", "");
                        symTable.update(varName, new IntValue(Integer.parseInt(newStr)));
                    }
                }catch(IOException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
            }
            else
                throw  new MyException("This expression doesn't have a string type!");
        }
        else
          throw new MyException("This variable is not defined !");
        return null;
    }

    @Override
    public IDictSymTable<String, Type> typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        Type typVar = typeEnv.returnValue(this.varName);
        Type typExp = this.exp.typecheck(typeEnv);
        if(typVar.equals(new IntType())){
            if(typExp.equals(new StrType())){
                return typeEnv;
            }
            else{
                throw new MyException("Expression doesn't have an integer type!");
            }
        }
        else{
            throw new MyException("Variable name is not of string type!");
        }
    }

    @Override
    public String toString() {
        return "ReadFile(" + exp + ", " + varName + ")";
    }
}
