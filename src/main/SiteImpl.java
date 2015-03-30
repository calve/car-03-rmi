package main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SiteImpl extends UnicastRemoteObject implements SiteItf {

	protected RemoteRef ref;
	protected String id;
	protected ArrayList<SiteItf> listFathers;
	protected ArrayList<SiteItf> listSons;

	protected SiteImpl(String id) throws RemoteException {
		super();
		this.id = id;
		this.listFathers = new ArrayList<SiteItf>();
		this.listSons = new ArrayList<SiteItf>();
	}

	@Override
	public String getId() throws RemoteException {
		return this.id;
	}
	
	@Override
	public ArrayList<SiteItf> getFathers() throws RemoteException {
		return this.listFathers;
	}

	@Override
	public ArrayList<SiteItf> getSons() throws RemoteException {
		return this.listSons;
	}

	@Override
	public void addFather(SiteItf father) throws RemoteException {
		this.listFathers.add(father);
	}

	@Override
	public void addSon(SiteItf son) throws RemoteException {
		this.listSons.add(son);
	}

	@Override
	public int sendMessage(byte[] message) throws RemoteException {

		int result = 0;
		for (SiteItf son : this.listSons) {
			if (son.equals(null)) {
				System.out.println("DEBUG [" + this.id + "] : Plus de fils");
			} else { // propagation a tous les fils
				System.out.println("DEBUG [" + this.ref
						+ "] : Propagation du message au fils [" + son.getId()
						+ "]");
				result += son.sendMessage(message);
			}
		}
		return result;
	}

	/*
	 * The first argument is the father id The other arguments are childrens ids
	 */
	public static void main(String[] args) {
		boolean has_father = false;
		int father = -1;
		if (args.length > 0) {
			has_father = true;
			father = Integer.parseInt(args[0]);
			System.out.println("father : " + father);
		}
		try {
			Registry registry = LocateRegistry.getRegistry();
			SiteImpl siteImpl = (SiteImpl) new SiteImpl("First");
			registry.bind("node-" + father, siteImpl);
			System.out.println("Running");
			System.out.println("Try to contact root");
			SiteItf alice = (SiteItf) registry.lookup("root");
			alice.sendMessage("hello, message".getBytes());
		} catch (Exception e) {
			System.out.println("Something bad happend");
			e.printStackTrace();
		}

		/* Lire l'id du père dans args */
		/* Récupère une instance du père auprès de RMIregistry */
		/* new SiteImpl(? id_pere) */
		/* while (true) ? */
		
		/* Recherche de l'instance auprès du registry dans Tree.java
		 * Dans cette classe, création du noeud de l'arbre.
		 * Sera utiliser dans un .sh pour créer des noeuds et les lier 
		 * ensemble grâce au main de Tree.
		 * 
		 * */
	}
}
