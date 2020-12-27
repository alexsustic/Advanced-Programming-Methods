package model.values;

import model.types.RefType;
import model.types.Type;

public class RefValue implements Value {
     final private int address;
     final private Type locationType;

     public RefValue(int adr, Type locType) {this.address = adr; this.locationType = locType;}

     public int getAddress() {return this.address;}

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof RefValue;
    }

    @Override
    public String toString(){return "RefValue(" + this.address + "," + this.locationType.toString() +")";}
}
