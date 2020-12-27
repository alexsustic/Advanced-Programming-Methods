package model.expression;

import exception.MyException;
import model.adt.IDictSymTable;
import model.adt.IHeap;
import model.types.Type;
import model.values.Value;

public class VarExp implements Exp{
    private String key;

    public VarExp(String str){this.key=str;};

    @Override
    public Value eval(IDictSymTable<String, Value> tbl, IHeap<Integer,Value> hp) throws MyException {
        return tbl.returnValue(this.key);
    }

    @Override
    public Type typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        return typeEnv.returnValue(this.key);
    }

    @Override
    public String toString()
    {
        return this.key.toString();
    }
}
