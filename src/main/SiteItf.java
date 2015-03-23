package main;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;

public interface SiteItf extends Remote {

	/**
	 * Send a message through a tree of SiteImpl
	 * @param message The message to propagate in the tree
	 * @param initiateur The entry of the tree
	 * @return The status
	 * @throws RemoteException
	 */
	public int sendMessage(byte[] message, RemoteRef initiateur)
			throws RemoteException;

}
