package controller;

import exception.MyException;
import model.PrgState;
import model.adt.IExeStack;
import model.adt.ListOut;
import model.statements.IStmt;
import model.values.RefValue;
import model.values.Value;
import repository.Repo;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private Repo repository;
    private int flag;
    private ExecutorService executor;

    public Controller(Repo r, int f){this.repository = r; this.flag = f;};

    public void addPrgState(PrgState prgState) {

        this.repository.addPrgState(prgState);}

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }
    public Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddresses, HashMap<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Value> safeGarbageCollector(List<Integer> addressesFromSymTable, List<Integer> addressesFromHeap, HashMap<Integer, Value> heap)
    {
        return heap.entrySet().stream()
                .filter(e -> addressesFromSymTable.contains(e.getKey()) || addressesFromHeap.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddressesFromSymTable(Collection<Value> symTablesValues) {
        return symTablesValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    public List<Integer> getAddressFromHeap(Collection<Value> heapVal) {
        return heapVal.stream()
                .filter(val -> val instanceof RefValue)
                .map(value -> {
                    RefValue v1 = (RefValue) value;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unChecked")
    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException{

        prgList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (IOException | MyException e) {
                e.printStackTrace();
            }
        });

        List<Callable<PrgState>> callableList = prgList.stream()
                       .map((PrgState p) -> (Callable<PrgState>) p::oneStep)
                       .collect(Collectors.toList());


        List<PrgState> newPrgList = executor.invokeAll(callableList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        prgList.addAll(newPrgList);

        prgList.forEach(prg -> {
            try {

                repository.logPrgStateExec(prg);
            } catch (IOException | MyException e) {
                e.printStackTrace();
            }
        });
        repository.setPrgList(prgList);


    }


    @SuppressWarnings("unChecked")
    // complete execution of a program
    public void allSteps() throws IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repository.getPrgList());
        while (prgList.size() > 0) {
            collectGarbage(prgList);
            oneStepForAllPrg(prgList);
            //remove the completed programs
            prgList = removeCompletedPrg(repository.getPrgList());
        }
        executor.shutdown();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository

        // update the repository state
        repository.setPrgList(prgList);
    }

    public void collectGarbage(List<PrgState> prgList) {
        PrgState prg = prgList.get(0);
        var dummy = prgList.stream()
                .map(programState -> programState.getSymTable().getDictionary().values())
                .collect(Collectors.toList());
        prg.getHeap().setContent(safeGarbageCollector(
                getAddressesFromSymTable(prg.getSymTable().getDictionary().values()),
                getAddressFromHeap(prg.getHeap().getContent().values()),
                prg.getHeap().getContent()));
    }

}
