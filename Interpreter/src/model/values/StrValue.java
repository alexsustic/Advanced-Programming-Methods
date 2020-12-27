package model.values;

import model.types.StrType;
import model.types.Type;

public class StrValue implements Value
{
    private final String str;

    public StrValue(String string) {
        this.str = string;
    }

    public String getValue() {
        return str;
    }

    @Override
    public String toString() {
        return "" + this.str;
    }

    @Override
    public Type getType() {
        return new StrType();
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof StrValue;
    }
}
