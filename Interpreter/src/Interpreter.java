import controller.Controller;
import exception.MyException;
import model.PrgState;
import model.adt.*;
import model.expression.*;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.RefType;
import model.types.StrType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StrValue;
import model.values.Value;
import repository.Repo;
import repository.Repository;
import view.ExitCommand;
import view.RunExample;
import view.TextMenu;

import java.io.BufferedReader;

public class Interpreter {
   public static PrgState initializeProgramState(IStmt ex)
   {
       IExeStack<IStmt> exeStack = new ExeStack<>();
       IDictSymTable<String, Value> symTable = new DictSymTable<>();
       IListOut<Value> out = new ListOut<>();
       IFileTable<String, BufferedReader> fileTable = new FileTable<>();
       IHeap<Integer,Value> heap = new Heap<>();
       return new PrgState(exeStack,symTable,out,heap,fileTable, ex);
   }

   public static void main(String[] args) throws MyException {
       IStmt ex1 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new AssignStmt("a", new ValExp(new IntValue(2))), new PrintStmt(new VarExp("a"))));
       PrgState prg1 = initializeProgramState(ex1);
       Repo repo1 = new Repository("file1.txt");
       Controller srv1 = new Controller(repo1, 1);
       srv1.addPrgState(prg1);
       ex1.typecheck(new DictSymTable<>());


       IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new BoolType()), new CompStmt(new AssignStmt("a",new ValExp(new IntValue(2))), new CompStmt(new AssignStmt("b", new ValExp(new BoolValue(true))),new IfStmt(new LogicExp(1,new ValExp(new BoolValue(true)), new VarExp("b")),new PrintStmt(new ArithExp(3,new VarExp("a"), new ValExp(new IntValue(2)))), new PrintStmt(new ArithExp(4,new VarExp("a"), new ValExp(new IntValue(2)))))))));
       PrgState prg2 = initializeProgramState(ex2);
       Repo repo2 = new Repository("file2.txt");
       Controller srv2 = new Controller(repo2, 1);
       srv2.addPrgState(prg2);
       ex2.typecheck(new DictSymTable<>());


       IStmt ex3 = new CompStmt(new VarDeclStmt("varf", new StrType()), new CompStmt(new AssignStmt("varf", new ValExp(new StrValue("test.in"))), new CompStmt(new OpenRFile(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFile(new VarExp("varf"), new VarExp("varc").toString()), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFile(new VarExp("varf"), new VarExp("varc").toString()), new PrintStmt(new VarExp("varc")))))))));
       PrgState prg3 = initializeProgramState(ex3);
       Repo repo3 = new Repository("file3.txt");
       Controller srv3 = new Controller(repo3, 1);
       srv3.addPrgState(prg3);
       ex3.typecheck(new DictSymTable<>());


       IStmt ex4 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new NewStmt("v", new ValExp(new IntValue(20))), new CompStmt( new VarDeclStmt("a",new RefType(new RefType( new IntType()))), new CompStmt(new NewStmt("a", new VarExp("v")), new CompStmt(new NewStmt("v", new ValExp(new IntValue(30))), new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("a")))))))));

       PrgState prg4 = initializeProgramState(ex4);
       Repo repo4 = new Repository("file4.txt");
       Controller srv4 = new Controller(repo4, 1);
       srv4.addPrgState(prg4);
       ex4.typecheck(new DictSymTable<>());


       IStmt ex5 = new CompStmt(new VarDeclStmt("a",new IntType()),new CompStmt(new AssignStmt("a",new ValExp(new IntValue(1))), new CompStmt(new WhileStmt(new RelationalExp(new VarExp("a"), new ValExp(new IntValue(15)), "<"), new AssignStmt("a",new ArithExp(3,new VarExp("a"), new ValExp(new IntValue(2))))),new PrintStmt(new VarExp("a")))));
       PrgState prg5 = initializeProgramState(ex5);
       Repo repo5 = new Repository("file5.txt");
       Controller srv5 = new Controller(repo5,1);
       srv5.addPrgState(prg5);
       ex5.typecheck(new DictSymTable<>());


       IStmt ex6 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new NewStmt("a", new ValExp(new IntValue(15))), new CompStmt( new VarDeclStmt("b",new RefType(new RefType( new IntType()))), new CompStmt(new NewStmt("b", new VarExp("a")), new CompStmt(new HeapWritingStmt("a",new ValExp(new IntValue(45))), new PrintStmt(new HeapReadingExp(new HeapReadingExp(new VarExp("b")))))))));
       PrgState prg6 = initializeProgramState(ex6);
       Repo repo6 = new Repository("file6.txt");
       Controller srv6 = new Controller(repo6,1);
       srv6.addPrgState(prg6);
       ex6.typecheck(new DictSymTable<>());

       IStmt ex7 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new AssignStmt("v", new ValExp(new IntValue(10))), new CompStmt(new NewStmt("a", new ValExp(new IntValue(22))), new CompStmt(new ForkStmt(new CompStmt(new HeapWritingStmt("a",new ValExp(new IntValue(30))), new CompStmt(new AssignStmt("v",new ValExp(new IntValue(32))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a"))))))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a")))))))));
       PrgState prg7 = initializeProgramState(ex7);
       Repo repo7 = new Repository("file7.txt");
       Controller srv7 = new Controller(repo7,1);
       srv7.addPrgState(prg7);
       ex7.typecheck(new DictSymTable<>());


       TextMenu textMenu = new TextMenu();
       textMenu.addCommand(new ExitCommand("0", "exit"));
       textMenu.addCommand(new RunExample("1", ex1.toString(),srv1));
       textMenu.addCommand(new RunExample("2", ex2.toString(),srv2));
       textMenu.addCommand(new RunExample("3", ex3.toString(),srv3));
       textMenu.addCommand(new RunExample("4", ex4.toString(),srv4 ));
       textMenu.addCommand(new RunExample("5", ex5.toString(),srv5));
       textMenu.addCommand(new RunExample("6", ex6.toString(),srv6));
       textMenu.addCommand(new RunExample("7", ex7.toString(),srv7));


       textMenu.show();

   }
}
