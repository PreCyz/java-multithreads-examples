package com.dosomedev;

public class Value {
    private long setCounter = 0;
    private long takeCounter = 0;
    private volatile long counter = 0;

    private boolean hasNewValue = false;
    private Value value = null;


    void setValue(Value val) {
        this.value = val;
        this.setCounter++;
        this.hasNewValue = true;
    }

    Value takeValue() {
        if (this.hasNewValue) {
            //busy wait until new value arrives
        }

        Value newValue = this.value;
        this.takeCounter++;
        this.hasNewValue = false;
        return newValue;
    }

    public boolean inc() {
        if (this.counter == 10) {
            return false;
        }
        //read main memory
        //inc in CPU register
        //write main memory
        this.counter++;
        return true;
    }
}
