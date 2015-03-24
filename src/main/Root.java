package main;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.RemoteRef;

/**
 * Creation de la structure de l'arbre avec les objets RMI utilisation de
 * RemoteObject.getRef() : RemoteRef pour la gestion des obj
 *
 */
public class Root implements SiteItf{

	public Root(){
	}

	public int sendMessage(byte[] message, RemoteRef initiateur){
		System.out.println(message.toString());
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


}
