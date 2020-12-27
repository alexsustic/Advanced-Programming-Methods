package model.statements;

import exception.MyException;
import model.PrgState;
import model.adt.IDictSymTable;
import model.types.BoolType;
import model.types.IntType;
import model.types.Type;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

public class VarDeclStmt implements IStmt{
    private String key;
    private Type type;

    public VarDeclStmt(String k, Type t){ this.key = k; this.type = t;};

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDictSymTable<String, Value> symTable = state.getSymTable();
        if(!symTable.isExisting(this.key))
        {
            symTable.add(this.key, this.type.defaultValue());
        }
        else
            throw new MyException("This variable is already declared");
        return null;
    }

    @Override
    public IDictSymTable<String, Type> typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        typeEnv.add(key,type);
        return typeEnv;
    }

    @Override
    public String toString()
    {
        return  this.type.toString()+ " " + this.key;
    }


}
