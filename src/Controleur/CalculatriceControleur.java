package Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import Modele.CalculatriceModele;
import Vue.CalculatricePub;
import Vue.CalculatriceVue;

// le controleur fait le pont 
// entre la vue et le modèle

public class CalculatriceControleur {

	private CalculatriceVue laVue;
	private CalculatriceModele leModele;
	private CalculatricePub laPub;
	private int conteneurErreur;

	public CalculatriceControleur(CalculatriceVue laVue, CalculatriceModele leModele, CalculatricePub laPub) {
		this.laVue = laVue;
		this.leModele = leModele;
		this.laPub = laPub;
		
		// méthode qui va bloquer le programme pendant 10s pour la publicité 
		
		this.leModele.pauseProgramme(10);
		
		// je rend visible la calculatrice et je fais disparaitre la pub 
		
		this.laVue.setVisible(true);
		this.laPub.setVisible(false);
		
		this.laVue.recepteurBouttonVerification(new RecepteurCalculatrice());

	}
	
	class RecepteurCalculatrice implements ActionListener{

		public void actionPerformed(ActionEvent e) {
				
			// déclaration et initialisation des variables qui vont être 
			// utilisés en paramètre de fonction

			int premierNombre = laVue.getPremierNombre();
			int deuxiemeNombre = laVue.getDeuxiemeNombre();  
			
			int resultatAddition = leModele.getSommeAddition();
			int resultatSoustraction = leModele.getSommeSoustraction();
			
				//condition qui va faire une addition ou une soustraction 
				//en fonction de l'opérateur selectionné
				
			if (laVue.getOperateurs() == "+") { 			
				
				leModele.addition(premierNombre, deuxiemeNombre);
				
				//condition qui va vérifier si le résultat de l'addition  
				// est égal au résultat proposé par l'élève et afficher Bon ou Mauvais
				
				if (resultatAddition == laVue.getResultatPropose() && resultatAddition <= 10){
						
						laVue.setAffichageBonMauvais(" Le résultat choisit est BON! Félicitation!!");
						laVue.setAffichageResultatNettoyage();
						
						// boite de dialogue qui affichera la pub pendant 5s si l'utilisteur ferme le programme
						
						 int choix = JOptionPane.showConfirmDialog(null,
				                "Voulez vous arrêter ? ",
				                "Cliquez sur YES pour arrêter l'application", JOptionPane.YES_OPTION,
				                JOptionPane.INFORMATION_MESSAGE);
				        if (choix == JOptionPane.YES_OPTION) {
				        	
				        	laPub.setVisible(true);
				        	laVue.setVisible(false);
				        	leModele.pauseProgramme(5);
				        	System.exit(0);
				        }
						
				} else if (resultatAddition != laVue.getResultatPropose() && resultatAddition <= 10){
		
						laVue.setAffichageBonMauvais(" Le résultat choisit est MAUVAIS! Ré-essaye!");
						conteneurErreur = conteneurErreur + 1;
						laVue.setAffichageResultatNettoyage();
						
						if (conteneurErreur == 3){
							
							// réinitialisation du conteneur 
							
							conteneurErreur = 0;
							laVue.setAffichageBonMauvais(" Tu as échoué 3 fois! Le résultat était: ");
							laVue.setAffichageResultat(resultatAddition);
							
							// boite de dialogue qui affichera la pub pendant 5s si l'utilisteur ferme le programme
							
							 int choix = JOptionPane.showConfirmDialog(null,
					                "Voulez vous arrêter ? ",
					                "Cliquez sur YES pour arrêter l'application", JOptionPane.YES_OPTION,
					                JOptionPane.INFORMATION_MESSAGE);
					        if (choix == JOptionPane.YES_OPTION) {
					        	
					        	laPub.setVisible(true);
					        	laVue.setVisible(false);
					        	leModele.pauseProgramme(5);
					        	System.exit(0);
					        }
						}
					}
				} else {			

						leModele.soustraction(premierNombre, deuxiemeNombre);
					
					//condition qui va vérifier si le résultat de la soustraction  
					// est égal au résultat proposé par l'élève et afficher Bon ou Mauvais
					
					if (resultatSoustraction == laVue.getResultatPropose() && resultatSoustraction >= 0){

							laVue.setAffichageBonMauvais(" Le résultat choisit est BON! Félicitation!");
							laVue.setAffichageResultatNettoyage();
							
							// boite de dialogue qui affichera la pub pendant 5s si l'utilisteur ferme le programme
							
							 int choix = JOptionPane.showConfirmDialog(null,
					                "Voulez vous arrêter ? ",
					                "Cliquez sur YES pour arrêter l'application", JOptionPane.YES_OPTION,
					                JOptionPane.INFORMATION_MESSAGE);
					        if (choix == JOptionPane.YES_OPTION) {
					        	
					        	laPub.setVisible(true);
					        	laVue.setVisible(false);
					        	leModele.pauseProgramme(5);
					        	System.exit(0);
					        }
							
					} else if (resultatSoustraction != laVue.getResultatPropose() && resultatSoustraction >= 0){
		
							laVue.setAffichageBonMauvais(" Le résultat choisit est MAUVAIS! Ré-essaye!");
				
							conteneurErreur = conteneurErreur + 1;
							laVue.setAffichageResultatNettoyage();
						
						if (conteneurErreur == 3){
							conteneurErreur = 0;
							laVue.setAffichageBonMauvais(" Tu as échoué 3 fois! Le résultat était: ");
							laVue.setAffichageResultat(resultatSoustraction);	
							
							// boite de dialogue qui affichera la pub pendant 5s si l'utilisteur ferme le programme
							
							 int choix = JOptionPane.showConfirmDialog(null,
					                "Voulez vous arrêter ? ",
					                "Cliquez sur YES pour arrêter l'application", JOptionPane.YES_OPTION,
					                JOptionPane.INFORMATION_MESSAGE);
					        if (choix == JOptionPane.YES_OPTION) {
					        	
					        	laPub.setVisible(true);
					        	laVue.setVisible(false);
					        	leModele.pauseProgramme(5);
					        	System.exit(0);
					        }		
						}
					}			
				}
			// condition qui va afficher un msg d'erreur si les résultats de l'opération sont > 10 ou < 0
			
			if (resultatAddition > 10 || resultatSoustraction < 0) {
			
				laVue.affichageMsgErreur("Le résultat ne doit pas dépasser 10 ou descendre en dessous de 0! Choisis une autre opération!");
				laVue.setAffichageResultatNettoyage();
			}


		}
	}
		
}
