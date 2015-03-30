package main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Creation de la structure de l'arbre avec les objets RMI utilisation de
 * RemoteObject.getRef() : RemoteRef pour la gestion des obj
 *
 */
public class Root implements SiteItf{

	public Root(){
	}

	public int sendMessage(byte[] message){
		try {
			System.out.write(message);
		}
		catch (IOException e){
			System.out.println("message received, but cannot print it");
		}
		return -1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Root root = new Root();
			SiteItf stub = (SiteItf) UnicastRemoteObject.exportObject(root, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("root", stub);
			System.out.println("Root started");
			// Naming.bind("Alice", stub);
		}
		catch (Exception e){
			System.out.println("Exception occured, sorry");
			e.printStackTrace();
		}

		System.out.println("Never forget ?");
		/* exec ("java siteimpl  null  null")*/
		/* exec ("java siteimpl  1")*/
		/* exec ("java siteimpl  1")*/
		/* exec ("java siteimpl  2 3 4 5")*/
	}

	@Override
	public String getId() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SiteItf> getFathers() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SiteItf> getSons() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFather(SiteItf father) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSon(SiteItf son) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
