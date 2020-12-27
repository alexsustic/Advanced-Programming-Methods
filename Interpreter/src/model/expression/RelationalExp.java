package model.expression;

import exception.MyException;
import model.adt.IDictSymTable;
import model.adt.IHeap;
import model.types.BoolType;
import model.types.IntType;
import model.types.Type;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

public class RelationalExp implements Exp{
    private final Exp exp1, exp2;
    private String op;

    public RelationalExp(Exp expr1, Exp expr2, String op) {
        this.exp1 = expr1;
        this.exp2 = expr2;
        this.op = op;
    }

    @Override
    public Value eval(IDictSymTable<String, Value> tbl, IHeap<Integer,Value> hp) throws MyException {
        Value v1, v2;
        v1 = exp1.eval(tbl,hp);
        if (v1.getType().equals(new IntType())) {
            v2 = exp2.eval(tbl,hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();
                if (op.equals("<"))
                    return new BoolValue(n1 < n2);
                if (op.equals("<="))
                    return new BoolValue(n1 <= n2);
                if (op.equals("=="))
                    return new BoolValue(n1 == n2);
                if (op.equals("!="))
                    return new BoolValue(n1 != n2);
                if (op.equals(">"))
                    return new BoolValue(n1 > n2);
                if (op.equals(">="))
                    return new BoolValue(n1 >= n2);

            } else
                throw new MyException("Second expression doesn't have an int type!");
        } else
            throw new MyException("First expression doesn't have an int type!");
        return new BoolValue(false);
    }

    @Override
    public Type typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        Type typ1 = this.exp1.typecheck(typeEnv);
        Type typ2 = this.exp2.typecheck(typeEnv);
        if(typ1.equals(new IntType())) {
            if (typ2.equals(new IntType()))
                return new BoolType();
            else
                throw new MyException("The second operator is not an integer !");
        }
        else
            throw new MyException("The first operator is not an integer!");

    }

    @Override
    public String toString(){
        if(this.op.equals(">"))
            return this.exp1.toString() + " >" + this.exp2.toString();
        else if(this.op.equals("<"))
            return this.exp1.toString() + " <" + this.exp2.toString();
        else if(this.op.equals(">="))
            return this.exp1.toString() + " >=" + this.exp2.toString();
        else if(this.op.equals("<="))
            return this.exp1.toString() + " <=" + this.exp2.toString();
        return null;
    }
}
