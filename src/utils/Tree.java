package utils;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import main.SiteItf;

/**
 * Une classe utile pour cr�er un lien de parent� entre un idFather et un idSon.
 * D'apr�s ces ids, on recherche aupr�s du serveur les instances des noeuds
 * SiteItf.
 */
public class Tree {

	public static void main(String[] args) {
		String idFather = null;
		String idSon = null;

		/* D�finition des arguments */
		if (args.length != 2) {
			System.out.println("Args : IdFather IdSon");
		} else {
			idFather = args[0];
			idSon = args[1];

			/* Registry */
			Registry registry;
			try {
				registry = LocateRegistry.getRegistry();
				// On r�cup�re les instances
				SiteItf father = (SiteItf) registry.lookup(idFather);
				SiteItf son = (SiteItf) registry.lookup(idSon);
				// On ajoute le lien de parent�
				father.addSon(son);
				System.out.println("A tree link between " + idFather + " and "
						+ idSon + " have been created.");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

	}
}