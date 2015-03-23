package main;

import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;

public class SiteImpl extends UnicastRemoteObject implements SiteItf {

	protected RemoteRef ref;
	protected SiteImpl pere;
	protected SiteImpl[] fils;

	protected SiteImpl(RemoteRef ref, SiteImpl pere, SiteImpl[] fils) throws RemoteException {
		super();
		this.ref  = ref;
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
}
