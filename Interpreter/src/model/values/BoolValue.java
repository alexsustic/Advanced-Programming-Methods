package model.values;

import model.types.BoolType;
import model.types.Type;

public class BoolValue implements Value{
    private boolean value;

    public BoolValue(boolean v){this.value = v;};

    public boolean getValue(){
        return this.value;
    }

    public String toString(){
        return " " + this.value;
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof BoolValue;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }
}
