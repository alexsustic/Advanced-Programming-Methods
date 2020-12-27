package model.statements;

import exception.MyException;
import model.PrgState;
import model.adt.IDictSymTable;
import model.adt.IHeap;
import model.expression.Exp;
import model.types.RefType;
import model.types.Type;
import model.values.RefValue;
import model.values.Value;


public class NewStmt implements IStmt{
    private String varName;
    private Exp expression;

    public NewStmt(String name, Exp exp){this.varName = name; this.expression = exp;}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDictSymTable<String, Value> symTable = state.getSymTable();
        IHeap<Integer,Value> heap = state.getHeap();
        if(symTable.isExisting(this.varName)) {
            Type varType = symTable.returnValue(this.varName).getType();
           if( varType instanceof RefType) {
               RefType refType = (RefType) varType;
               Value val = this.expression.eval(symTable,heap);
                if( val.getType().equals(refType.getInner())) {
                    int positionGenerated = heap.add(1, val);
                    symTable.update(varName, new RefValue(positionGenerated,val.getType()));
                }

                else
                    throw new MyException("The type of the variable doesn't match with the type of the expression's value! ");

           }
           else
               throw  new MyException("This variable doesn't have reference type!");
        }
        else
            throw new MyException("There is no variable with this name declared !");
        return null;
    }

    @Override
    public IDictSymTable<String, Type> typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.returnValue(this.varName);
        Type typExp = this.expression.typecheck(typeEnv);
        if(typeVar.equals(new RefType(typExp))){
            return typeEnv;
        }
        else{
            throw new MyException("Right hand side and left hand side have different types!");
        }
    }

    @Override
    public String toString() {
        return "new(" + this.varName + "," + this.expression.toString() + ")";
    }
}
