package repository;

import exception.MyException;
import model.PrgState;
import model.adt.*;
import model.statements.IStmt;
import model.values.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;


public class Repository implements Repo {
    private List<PrgState> prgStates;
    private String logFilePath;
    public Repository(String path){ this.prgStates= new LinkedList<PrgState>() ; this.logFilePath = path;};


    public List<PrgState> returnAllStates(){ return this.prgStates;}

    @Override
    public void logPrgStateExec(PrgState prg) throws MyException, IOException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            IExeStack<IStmt> exeStack =prg.getExeStack();
            IDictSymTable<String, Value> symTable = prg.getSymTable();
            IListOut<Value> out = prg.getOut();
            IHeap<Integer, Value> heap = prg.getHeap();

            logFile.println("---------------------------------------------------------------------------");
            logFile.println("ID:");
            logFile.println(prg.getId());
            logFile.println("ExeStack:");
            logFile.println(exeStack.toString());
            logFile.println("SymTable:");
            logFile.println(symTable.toString());
            logFile.println("Out:");
            logFile.println(out.toString());
            logFile.println("Heap:");
            logFile.println(heap.toString());
            logFile.println("---------------------------------------------------------------------------");
            logFile.println("\n");
            logFile.println("\n");
            logFile.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addPrgState(PrgState prgState) {
        this.prgStates.add(prgState);
    }


    @Override
    public void setPrgList(List<PrgState> list) {
        this.prgStates = list;
    }

    @Override
    public List<PrgState> getPrgList() {
        return this.prgStates;
    }
}
