package model.statements;

import exception.MyException;
import model.PrgState;
import model.adt.IDictSymTable;
import model.adt.IExeStack;
import model.adt.IHeap;
import model.expression.Exp;
import model.types.Type;
import model.values.Value;

public class AssignStmt implements IStmt {
    private String key;
    private Exp expr;

    public AssignStmt(String k, Exp e){this.key=k; this.expr=e;}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDictSymTable<String, Value> symTable = state.getSymTable();
        IHeap<Integer,Value > heap = state.getHeap();
        IExeStack<IStmt> exeStack = state.getExeStack();

        if(symTable.isExisting(this.key)){
            Value val = this.expr.eval(symTable,heap);
            Type typeKey = symTable.returnValue(this.key).getType();
            if(val.getType().equals(typeKey))
                symTable.update(key,val);
            else
                throw new MyException("Types do not match ! ");

        }
        else
            throw  new MyException("This variable is not declared !" );
        return null;
    }

    @Override
    public IDictSymTable<String, Type> typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        Type type_var = typeEnv.returnValue(this.key);
        Type typ_exp = this.expr.typecheck(typeEnv);
        if (type_var.equals(typ_exp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return this.key + " = " + this.expr.toString();
    }


}
