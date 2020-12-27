package model.expression;

import exception.MyException;
import model.PrgState;
import model.adt.IDictSymTable;
import model.adt.IHeap;
import model.expression.Exp;
import model.statements.IStmt;
import model.types.RefType;
import model.types.Type;
import model.values.RefValue;
import model.values.Value;

public class HeapReadingExp implements Exp {

    private Exp expression;

    public HeapReadingExp(Exp exp){this.expression = exp;}

    @Override
    public String toString(){return "rH(" + this.expression.toString() +")";}


    @Override
    public Value eval(IDictSymTable<String,Value> tbl, IHeap<Integer,Value> hp) throws MyException {
        Value val = this.expression.eval(tbl,hp);
        if(val instanceof RefValue){
            int address = ((RefValue) val).getAddress();
            if(hp.isExisting(address))
            {
                return hp.returnValue(address);
            }
            else
                throw new MyException("This heap doesn't contain the value's addres!");

        }

        else
            throw new MyException("The type of the expression's value is not a RefType!");
    }

    @Override
    public Type typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        Type typ = expression.typecheck(typeEnv);
        if(typ instanceof RefType){
            RefType ref_typ = (RefType) typ;
            return ref_typ.getInner();
        }
        else{
            throw new MyException("The rH argument is not RefType");
        }
    }
}

