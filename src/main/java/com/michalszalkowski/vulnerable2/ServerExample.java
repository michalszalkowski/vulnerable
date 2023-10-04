package com.michalszalkowski.vulnerable2;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerExample {
	public static void main(String[] args) {
		try {

			String host = "localhost";
			if (args.length > 0) {
				host = args[0];
			}

			LocateRegistry.createRegistry(1099);
			Naming.rebind("//" + host + "/MyServer", new RemoteObject());
			System.err.println("Server ready " + host);
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

}