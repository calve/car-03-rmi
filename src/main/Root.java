package main;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
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
	public int sendMessage(byte[] message, RemoteRef initiateur){return -1;}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry();
		}
		catch (RemoteException e){
		}
		/* exec ("java siteimpl  null  null")*/
		/* exec ("java siteimpl  1")*/
		/* exec ("java siteimpl  1")*/
		/* exec ("java siteimpl  2 3 4 5")*/


	}


}
