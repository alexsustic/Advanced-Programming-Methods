package model.statements;

import exception.MyException;
import model.PrgState;
import model.adt.IDictSymTable;
import model.adt.IExeStack;
import model.types.Type;

public class CompStmt implements IStmt{
    private IStmt stmt1;
    private IStmt stmt2;

    public CompStmt(IStmt s1, IStmt s2) {this.stmt1 = s1; this.stmt2 =s2;};


    @Override
    public PrgState execute(PrgState state) throws MyException {
        IExeStack<IStmt> exeStack = state.getExeStack();
        exeStack.push(stmt2);
        exeStack.push(stmt1);
        return null;
    }

    @Override
    public IDictSymTable<String, Type> typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        return stmt2.typecheck(stmt1.typecheck(typeEnv));
    }

    @Override
    public String toString() {
        return "" + this.stmt1.toString() + " ; " + this.stmt2.toString() + "";
    }

}
