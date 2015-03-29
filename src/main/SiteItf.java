package main;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SiteItf extends Remote {

	/**
	 * Return the ID of the node
	 * @return the id of the node
	 * @throws RemoteException
	 */
	public String getId() throws RemoteException;
	
	/**
	 * Return the list of fathers
	 * 
	 * @return the list of fathers (SiteItf)
	 * @throws RemoteException
	 */
	public ArrayList<SiteItf> getFathers() throws RemoteException;

	/**
	 * Return the list of sons
	 * 
	 * @return the list of sons (SiteItf)
	 * @throws RemoteException
	 */
	public ArrayList<SiteItf> getSons() throws RemoteException;

	/**
	 * Add a father into the list of fathers
	 * 
	 * @param father
	 *            The father to be added
	 * @throws RemoteException
	 */
	public void addFather(SiteItf father) throws RemoteException;

	/**
	 * Add a son to the list of sons
	 * 
	 * @param son
	 *            The son to be added
	 * @throws RemoteException
	 */
	public void addSon(SiteItf son) throws RemoteException;

	/**
	 * Send a message through a tree of nodes
	 * 
	 * @param message
	 *            The message to propagate in the tree
	 * @param initiateur
	 *            The entry of the tree
	 * @return The status
	 * @throws RemoteException
	 */
	public int sendMessage(byte[] message) throws RemoteException;

}
