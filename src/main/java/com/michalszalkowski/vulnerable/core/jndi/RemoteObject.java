package com.michalszalkowski.vulnerable.core.jndi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteObject extends UnicastRemoteObject implements RMIInterface {

	public RemoteObject() throws RemoteException {
		super();
	}

	@Override
	public String execute() throws RemoteException {
		return "Hello From Server";
	}
}
