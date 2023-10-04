package com.michalszalkowski.vulnerable2;

import com.michalszalkowski.vulnerable.core.jndi.RMIInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientExample {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		RMIInterface lookup = (RMIInterface) Naming.lookup("//127.0.0.1/MyServer");
		System.out.println(lookup.execute());
	}

}
