package model.statements;

import exception.MyException;
import model.PrgState;
import model.adt.IDictSymTable;
import model.adt.IExeStack;
import model.adt.IHeap;
import model.expression.Exp;
import model.types.BoolType;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;

public class WhileStmt implements IStmt{
    private Exp expression;
    private IStmt stmt;

    public WhileStmt(Exp exp, IStmt s){this.expression = exp; this.stmt=s;}


    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDictSymTable<String, Value> symTable = state.getSymTable();
        IHeap<Integer,Value> heap = state.getHeap();

        Value expVal = this.expression.eval(symTable,heap);
        if(expVal.getType() instanceof BoolType) {
            BoolValue boolValue = (BoolValue) expVal;
            if(boolValue.getValue())
            {
                IExeStack<IStmt> exeStack = state.getExeStack();
                exeStack.push(this);
                exeStack.push(this.stmt);
            }
        }
        else
            throw new MyException("Expression is not a boolean type!");
        return null;
    }

    @Override
    public IDictSymTable<String, Type> typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        Type typExp = this.expression.typecheck(typeEnv);
        if(typExp.equals(new BoolType())){
            this.stmt.typecheck(typeEnv.deepcopy());
            return typeEnv;
        }
        else{
            throw new MyException("The while condition is not a boolean type!");
        }
    }

    @Override
    public String toString() {
        return "while("+ this.expression.toString() +")" + " " + "{ " + this.stmt.toString() + " }";
    }
}
