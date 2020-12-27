package model.statements;

import exception.MyException;
import model.PrgState;
import model.adt.IDictSymTable;
import model.adt.IExeStack;
import model.adt.IHeap;
import model.expression.Exp;
import model.types.BoolType;
import model.types.IntType;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;

public class IfStmt implements IStmt {
    private Exp expr;
    private IStmt stmt1, stmt2;

    public IfStmt(Exp e, IStmt s1, IStmt s2) {this.expr = e; this.stmt1 = s1; this.stmt2 = s2;};

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDictSymTable<String, Value> symTable = state.getSymTable();
        IExeStack<IStmt> exeStack = state.getExeStack();
        IHeap<Integer,Value > heap = state.getHeap();
        Value v = this.expr.eval(symTable,heap);
        if(v.getType().equals(new IntType()))
            throw new MyException("This is not a boolean expression");
        else
        {
            BoolValue boolValue = (BoolValue) v;
            if(boolValue.getValue())
                exeStack.push(stmt1);
            else
                exeStack.push(stmt2);

        }
        return null;
    }

    @Override
    public IDictSymTable<String, Type> typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        Type typeExp = this.expr.typecheck(typeEnv);
        if (typeExp.equals(new BoolType())) {
            this.stmt1.typecheck(typeEnv.deepcopy());
            this.stmt2.typecheck(typeEnv.deepcopy());
            return typeEnv;
        } else
            throw new MyException("The condition of IF has not the type bool");
    }

    @Override
    public String toString() {
        return "If ( " + this.expr.toString() + ") Then ( " + this.stmt1.toString() + " ) Else ( " + this.stmt2.toString() + " ) ";
    }


}
