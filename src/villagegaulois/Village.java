package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum,int nbEtal) {
		this.marche = new Marche(nbEtal);
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}
	
	private class Marche {
		Etal[] etals;
		private Marche(int nbetal) {
			this.etals = new Etal[nbetal];
			for (int i = 0; i<nbetal; i++) {
				etals[i] = new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtatLibre() {
			int indiceEtalLibre = -1;
			for ( int i = 0; i<etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					indiceEtalLibre = i;
				}
			}
			return indiceEtalLibre;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nombreEtalContenantProduit = 0;
			for (int i = 0; i<etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nombreEtalContenantProduit++;
				}
			}
			Etal[] etalContenantProduit = new Etal[nombreEtalContenantProduit];
			for (int i = 0; i<etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etalContenantProduit[i] = etals[i];
				}
			}
			return etalContenantProduit;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i<etals.length; i++) {
				if(etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			StringBuilder bld = new StringBuilder();
			
			int nombreEtalOccupe = 0;
			for (int i = 0; i< etals.length ; i++) {
				if (etals[i].isEtalOccupe()) {
					bld.append(etals[i].afficherEtal());
					
					nombreEtalOccupe++;
				}
				
			}
			int nbEtalVide = etals.length - nombreEtalOccupe;
			String text = "Il reste " + nbEtalVide + " étal non utilisés dans le marché.\n";
			
			bld.append(text);
			return bld.toString();
		}
	}
	
	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}