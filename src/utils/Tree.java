package utils;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import main.SiteItf;

/**
 * Une classe utile pour créer un lien de parenté entre un idFather et un idSon.
 * D'après ces ids, on recherche auprès du serveur les instances des noeuds
 * SiteItf.
 */
public class Tree {

	public static void main(String[] args) {
		String idFather = null;
		String idSon = null;

		/* Définition des arguments */
		if (args.length != 2) {
			System.out.println("Args : IdFather IdSon");
		} else {
			idFather = args[0];
			idSon = args[1];

			/* Registry */
			Registry registry;
			try {
				registry = LocateRegistry.getRegistry();
				// On récupère les instances
				SiteItf father = (SiteItf) registry.lookup(idFather);
				SiteItf son = (SiteItf) registry.lookup(idSon);
				// On ajoute le lien de parenté
				father.addSon(son);
				System.out.println("A tree link between " + idFather + " and "
						+ idSon + " have been created.");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

	}
}