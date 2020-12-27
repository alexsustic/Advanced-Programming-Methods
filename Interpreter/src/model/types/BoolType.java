package model.types;

import model.values.BoolValue;
import model.values.Value;

public class BoolType implements Type {
    public BoolType(){};

    @Override
    public boolean equals(Object o){
        if(o instanceof BoolType)
            return true;
        else
            return false;

    }

    @Override
    public String toString(){
        return "boolean";
    }

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }
}
