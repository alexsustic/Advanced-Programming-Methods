package exception;

public class MyException extends RuntimeException {
    private String mess;
    public MyException(String s) { this.mess = s;
    }

    @Override
    public String toString(){
        return this.mess;
    }


}
