package com.dosomedev;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements RemoteCalculator {
    public CalculatorImpl() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        IO.println("Incoming request for adding a = " + a + " and b = " + b + ".");
        int result = a + b;
        IO.println("Calculation result = " + result + ".");

        return result;
    }
}
