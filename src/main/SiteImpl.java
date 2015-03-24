package main;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;

public class SiteImpl extends UnicastRemoteObject implements SiteItf {

	protected RemoteRef ref;
	protected int pere;
	protected SiteImpl[] fils;

	protected SiteImpl() throws RemoteException {
		super();
		this.pere = -1;
		this.fils = null;
	}

	protected SiteImpl(int pere, SiteImpl[] fils) throws RemoteException {
		super();
		this.pere = pere;
		this.fils = fils;
	}

	@Override
	public int sendMessage(byte[] message, RemoteRef initiateur)
			throws RemoteException {

		if (this.fils.equals(null)) {
			System.out.println("DEBUG ["+this.ref+"] : Plus de fils");
			return 0;
		} else { // propagation a tous les fils
			int result = 0;
			for(SiteImpl refFils : this.fils){
				System.out.println("DEBUG ["+this.ref+"] : Propagation du message au fils ["+refFils.ref+"]");
				result += refFils.sendMessage(message, initiateur);
			}
			return result;
		}
	}

	/*  The first argument is the father id
	    The other arguments are childrens ids
	 */
	public static void main(String[] args) {
		boolean has_father = false;
		int father = -1;
		if (args.length > 0){
			has_father = true;
			father = Integer.parseInt(args[0]);
			System.out.println("father : "+father);
		}
		try {
			Registry registry = LocateRegistry.getRegistry();
			SiteImpl siteImpl = (SiteImpl) new SiteImpl();
			registry.bind("node-"+father, siteImpl);
			System.out.println("Running");
			System.out.println("Try to contact root");
			SiteItf alice = (SiteItf) registry.lookup("root");
			alice.sendMessage("hello, message".getBytes(), null);
		}
		catch (Exception e) {
			System.out.println("Something bad happend");
			e.printStackTrace();
		}

		/* Lire l'id du père dans args */
		/* Récupère une instance du père auprès de RMIregistry */
		/* new SiteImpl(? id_pere) */
		/* while (true) ?*/
	}
}
