package view;

import controller.Controller;
import exception.MyException;

import java.io.IOException;

public class RunExample extends Command{
    private Controller ctr;

    public RunExample(String k, String d, Controller c) {
        super(k, d);
        this.ctr = c;
    }

    @Override
    public void execute() {
        try{
            ctr.allSteps();
        }catch( IOException | InterruptedException ex){
            ex.getStackTrace();
        }
    }
}