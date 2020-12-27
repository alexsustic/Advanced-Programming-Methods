package model.types;

import model.values.IntValue;
import model.values.Value;

public class IntType implements Type{
    public IntType(){};

    @Override
    public boolean equals(Object o){
        if(o instanceof IntType)
            return true;
        else
            return false;

    }

    @Override
    public String toString(){
        return "int";
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }
}
