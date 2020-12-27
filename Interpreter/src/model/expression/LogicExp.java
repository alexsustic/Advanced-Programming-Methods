package model.expression;

import exception.MyException;
import model.adt.IDictSymTable;
import model.adt.IHeap;
import model.types.BoolType;
import model.types.IntType;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;

public class LogicExp implements Exp{
    private Exp e1;
    private Exp e2;
    private int op; // "&&" - 1 and "||" - 2

    public LogicExp(int oper, Exp a1, Exp a2){this.op=oper ; this.e1= a1 ; this.e2= a2;};

    @Override
    public Value eval(IDictSymTable<String, Value> tbl, IHeap<Integer,Value> hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl,hp);
        if(v1.getType().equals(new BoolType()))
        {
            v2 = e2.eval(tbl,hp);
            if(v2.getType().equals(new BoolType())){
                BoolValue b1,b2;
                b1=(BoolValue)v1;
                b2=(BoolValue) v2;
                boolean bool1, bool2;
                bool1 = b1.getValue();
                bool2 = b2.getValue();
                if(this.op == 1)
                    return new BoolValue(bool1 && bool2);
                if(this.op == 2)
                    return new BoolValue(bool1 || bool2);

            }
            else throw new MyException("the second operand doesn't have boolean type");
        }
        else throw new MyException("the first operand doesn't have boolean type");
        return null;
    }

    @Override
    public Type typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        Type typ1 = e1.typecheck(typeEnv);
        Type typ2 = e2.typecheck(typeEnv);
        if(typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType()))
                return new BoolType();
            else
                throw new MyException("The second operator is not boolean!");
        }
        else
            throw new MyException("The first operator is not boolean!");

    }

    @Override
    public String toString(){
        if(this.op == 1)
            return this.e1.toString() + " && " + this.e2.toString();
        else if(this.op == 2)
            return this.e1.toString() + " || " + this.e2.toString();

        return null;
    }

}
