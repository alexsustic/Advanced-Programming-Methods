package model.statements;

import exception.MyException;
import model.PrgState;
import model.adt.IDictSymTable;
import model.types.Type;

public class NopStmt implements IStmt{

    NopStmt(){ };

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public IDictSymTable<String, Type> typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "No operation statement!";
    }
}
