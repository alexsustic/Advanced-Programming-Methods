package model.expression;

import exception.MyException;
import model.adt.IDictSymTable;
import model.adt.IHeap;
import model.types.Type;
import model.values.Value;

public class ValExp implements Exp{
    private Value val;

    public ValExp(Value val1){this.val = val1;};

    @Override
    public Value eval(IDictSymTable<String, Value> tbl, IHeap<Integer,Value> hp) throws MyException {
        return this.val;
    }

    @Override
    public Type typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        return this.val.getType();
    }

    @Override
    public String toString() {
        return this.val.toString();
    }
}
