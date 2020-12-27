package model.statements;

import exception.MyException;
import model.PrgState;
import model.adt.IDictSymTable;
import model.adt.IHeap;
import model.adt.IListOut;
import model.expression.Exp;
import model.types.Type;
import model.values.Value;

public class PrintStmt implements IStmt {
    private Exp expression;
    public PrintStmt(Exp expr){this.expression= expr;};

    public Exp getExpr(){ return this.expression; }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IListOut<Value> list = state.getOut();
        IDictSymTable<String, Value> symbolTable = state.getSymTable();
        IHeap<Integer,Value > heap = state.getHeap();
        list.push(this.expression.eval(symbolTable,heap));
        return null;
    }

    @Override
    public IDictSymTable<String, Type> typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        this.expression.typecheck(typeEnv);
        return typeEnv;
    }

    ;

    @Override
    public String toString(){
        return " print(" + this.expression.toString() +") " ;
    }



}
