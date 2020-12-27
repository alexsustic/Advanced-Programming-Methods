package model.types;

import model.values.StrValue;
import model.values.Value;

public class StrType implements Type{
    @Override
    public boolean equals(Object another) {
        return another instanceof StrType;
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public Value defaultValue() {
        return new StrValue("");
    }
}
