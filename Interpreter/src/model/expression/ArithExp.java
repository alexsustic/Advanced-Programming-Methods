package model.expression;

import exception.MyException;
import model.adt.IDictSymTable;
import model.adt.IHeap;
import model.types.IntType;
import model.types.Type;
import model.values.IntValue;
import model.values.Value;

public class  ArithExp implements Exp {
    private Exp e1;
    private Exp e2;
    private int op; // 1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(int oper, Exp a1, Exp a2){ this.op=oper; this.e1 = a1; this.e2= a2;};


    @Override
    public Value eval(IDictSymTable<String, Value> tbl, IHeap<Integer,Value> hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl,hp);
        if(v1.getType().equals(new IntType()))
        {
            v2 = e2.eval(tbl,hp);
            if(v2.getType().equals(new IntType()))
            {
                IntValue i1 = (IntValue)v1;
                IntValue i2= (IntValue)v2;
                int nr1, nr2;
                nr1 = i1.getValue();
                nr2 = i2.getValue();
                if(this.op == 1) return new IntValue(nr1+nr2);
                if(this.op == 2) return new IntValue(nr1-nr2);
                if(this.op == 3) return new IntValue(nr1*nr2);
                if(this.op == 4) {
                    if(nr2 == 0)
                        throw  new MyException("division by zero");
                    else
                        return new IntValue(nr1/nr2);
                }


            }
            else throw new MyException("second operator is not an integer");
        }
        else throw new MyException("first operator is not an integer");
     return null;
    }

    @Override
    public Type typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1= e1.typecheck(typeEnv);
        typ2= e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            } else
                throw new MyException("second operand is not an integer");
        }
         else
             throw new MyException("first operand is not an integer");
    }

    @Override
    public String toString(){
        if(this.op == 1)
            return this.e1.toString() + " + " + this.e2.toString();
        else if(this.op == 2)
            return this.e1.toString() + " - " + this.e2.toString();
        else if(this.op == 3)
            return this.e1.toString() + " * " + this.e2.toString();
        else if(this.op == 4)
            return this.e1.toString() + " / " + this.e2.toString();
        return null;
    }
}
