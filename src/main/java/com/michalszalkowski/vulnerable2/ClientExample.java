package com.michalszalkowski.vulnerable2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientExample {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		String host = "localhost";
		if (args.length > 0) {
			host = args[0];
		}

		RMIInterface lookup = (RMIInterface) Naming.lookup("//" + host + "/MyServer");
		System.out.println(lookup.execute());
	}

}
