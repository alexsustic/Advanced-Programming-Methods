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

public class HeapWritingStmt implements IStmt {
    private String varName;
    private Exp expression;

    public HeapWritingStmt(String name, Exp expr){this.varName = name; this.expression = expr;}


    @Override
    public PrgState execute(PrgState state) throws MyException {
        IDictSymTable<String, Value> symTable= state.getSymTable();
        IHeap<Integer,Value> heap = state.getHeap();
        if(symTable.isExisting(this.varName))
        {
            Value varValue = symTable.returnValue(this.varName);
            Type varType = varValue.getType();
            if(varType instanceof RefType)
            {
                int address = ((RefValue)varValue).getAddress();
                Value extractedValue = heap.returnValue(address);
                if(heap.isExisting(address)){
                        Value expVal = this.expression.eval(symTable,heap);
                        if(expVal.getType().equals(extractedValue.getType()))
                        {
                            heap.update(address,expVal);
                        }
                        else
                            throw new MyException("The type of the expressions doesn't match!");
                }
                    else
                        throw new MyException("The address of the variable is not a key of the heap!");

            }
            else
                throw new MyException("The variable's type is not a RefType!");
        }
        else
            throw new MyException("This variable is not declared !");
        return null;
    }

    @Override
    public IDictSymTable<String, Type> typecheck(IDictSymTable<String, Type> typeEnv) throws MyException {
        Type typVar = typeEnv.returnValue(this.varName);
        Type typExp = expression.typecheck(typeEnv);
        if(typVar instanceof RefType){
            if(typVar.equals(new RefType(typExp))){
                return typeEnv;
            }
            else{
                throw new MyException("Write Heap stmt: right hand side and left hand side have different types");
            }
        }
        else{
            throw new MyException("Variable name is not a string type!");
        }
    }

    @Override
    public String toString(){return "rW("+this.varName +"," + this.expression.toString() +")";}
}
