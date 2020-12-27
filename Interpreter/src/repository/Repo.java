package repository;

import exception.MyException;
import model.PrgState;
import model.adt.ListOut;

import java.io.IOException;
import java.util.List;

public interface Repo {

    void logPrgStateExec(PrgState prg) throws MyException, IOException;
    void addPrgState(PrgState prgState);
    void setPrgList(List<PrgState> list);
    List<PrgState> getPrgList();
}
