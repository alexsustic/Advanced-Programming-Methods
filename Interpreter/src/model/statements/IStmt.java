package model.statements;

import exception.MyException;
import model.PrgState;
import model.adt.IDictSymTable;
import model.types.Type;

public interface IStmt {
    public PrgState execute(PrgState state) throws MyException;
    IDictSymTable<String, Type> typecheck(IDictSymTable<String,Type> typeEnv) throws MyException;
}
