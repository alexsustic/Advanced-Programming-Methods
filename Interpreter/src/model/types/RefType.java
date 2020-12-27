package model.types;

import model.values.RefValue;
import model.values.Value;

public class RefType implements Type {
    private final Type inner;
    public RefType(Type inner){this.inner = inner;}

    public Type getInner(){return this.inner;}

    @Override
    public boolean equals(Object o){
        if(o instanceof RefType)
            return this.inner.equals(((RefType) o).getInner());
        else
            return false;

    }

    @Override
    public String toString(){return "Ref(" + inner.toString() + ") ";}
    @Override

    public Value defaultValue() {
        return new RefValue(0, inner);
    }
}
