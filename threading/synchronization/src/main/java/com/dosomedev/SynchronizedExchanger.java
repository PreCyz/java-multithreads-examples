package com.dosomedev;

public class SynchronizedExchanger {
    protected Object object = null;

    public synchronized Object getObject() {
        return object;
    }

    public synchronized void setObject(Object object) {
        this.object = object;
    }
}
