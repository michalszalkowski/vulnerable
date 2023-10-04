package com.michalszalkowski.vulnerable2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {

	String execute() throws RemoteException;

}
