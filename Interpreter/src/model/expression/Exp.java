package model.expression;

import exception.MyException;
import model.adt.IDictSymTable;
import model.adt.IHeap;
import model.types.Type;
import model.values.Value;

public interface Exp {
    Value eval(IDictSymTable<String,Value> tbl, IHeap<Integer,Value> hp) throws MyException ;
    Type typecheck(IDictSymTable<String,Type> typeEnv) throws MyException;
}
