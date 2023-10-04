package com.michalszalkowski.vulnerable2;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerExample {
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//localhost/HackerServer", new RemoteObject());
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

}