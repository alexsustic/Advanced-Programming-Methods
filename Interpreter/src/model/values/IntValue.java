package model.values;

import model.types.IntType;
import model.types.Type;

public class IntValue implements Value{
    private int value;

    public IntValue(int v){this.value=v;};

    public int getValue(){
        return this.value;
    }

    public String toString(){
        return " " + this.value;
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof IntValue;
    }

    @Override
    public Type getType() {
        return new IntType();
    }
}
