package main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.io.IOException;

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
		System.out.println("Here is "+this.id+" got message : ");
		try {
			System.out.write(message);
		}
		catch (IOException e){
			System.out.println("message received, but cannot print it");
		}
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
		if (args.length < 1) {
			System.out.println("usage : java main.SiteImpl <id> [father-id]");
		}
		try {
			String id = args[0];
			Registry registry = LocateRegistry.getRegistry();
			SiteImpl siteImpl = (SiteImpl) new SiteImpl(id);
			registry.bind(id, siteImpl);
			System.out.println("Running");
			System.out.println("Try to contact root");
			SiteItf root = (SiteItf) registry.lookup("root");
			if (args.length >= 2) {
				/* This one has a father ! */
				has_father = true;
				String father_id = args[1];
				SiteItf father = (SiteItf) registry.lookup(father_id);
				System.out.println("found father : " + father_id);
				father.addSon(siteImpl);
			}
			System.out.println("root found, sending message");
			byte[] message = ("hello, message from " + id + "\n").getBytes();
			root.sendMessage(message);
			System.out.println("message transmission ended");
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
