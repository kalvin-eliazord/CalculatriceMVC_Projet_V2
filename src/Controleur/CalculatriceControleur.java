package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
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

		this.laVue.BtnListener(new RecepteurCalculatrice());
		this.laVue.ComboListener(new ComboAction());
	}

	class RecepteurCalculatrice implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			// déclaration et initialisation des variables qui vont être 
			// utilisés en paramètre de fonction

			int premierNombre = laVue.getPremierNombre();
			int deuxiemeNombre = laVue.getDeuxiemeNombre();  

			//condition qui va faire une addition ou une soustraction 
			//en fonction de l'opérateur selectionné

			if (laVue.getStringOperateurs() == "+") { 			

				leModele.addition(premierNombre, deuxiemeNombre);

				//condition qui va vérifier si le résultat de l'addition  
				// est égal au résultat proposé par l'élève et afficher Bon ou Mauvais

				if (leModele.getSommeAddition() == laVue.getResultatPropose()){

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

				} else if (leModele.getSommeAddition() != laVue.getResultatPropose()){

					laVue.setAffichageBonMauvais(" Le résultat choisit est MAUVAIS! Ré-essaye!");
					conteneurErreur = conteneurErreur + 1;
					laVue.setAffichageResultatNettoyage();

					if (conteneurErreur == 3){

						// réinitialisation du conteneur 
						conteneurErreur = 0;
						laVue.setAffichageBonMauvais(" Tu as échoué 3 fois! Le résultat était: ");
						laVue.setAffichageResultat(leModele.getSommeAddition());

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

				if (leModele.getSommeSoustraction() == laVue.getResultatPropose()){

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

				} else if (leModele.getSommeSoustraction() != laVue.getResultatPropose()){

					laVue.setAffichageBonMauvais(" Le résultat choisit est MAUVAIS! Ré-essaye!");

					conteneurErreur = conteneurErreur + 1;
					laVue.setAffichageResultatNettoyage();

					if (conteneurErreur == 3){
						conteneurErreur = 0;
						laVue.setAffichageBonMauvais(" Tu as échoué 3 fois! Le résultat était: ");
						laVue.setAffichageResultat(leModele.getSommeSoustraction());	

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
		}
	}

	//actions qui vont restreindrent les calculs entre 0 et 10
	class ComboAction implements ActionListener {
		public void actionPerformed(ActionEvent EventCombo) {

			if (EventCombo.getSource() == laVue.getComboUn()) {

				String operateur = laVue.getStringOperateurs();
				int premierNombre = laVue.getPremierNombre();
				JComboBox<Integer> JComboDeuxiemeNombre = laVue.getComboDeux();

				//en fonction du chiffre sélectionné dans la premiere liste, la deuxieme liste se met à jour
				// avec la var maxSize
				int maxSize = 0;

				if (operateur == "+") {

					switch (premierNombre) {

					case 0:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<11; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 1:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<10; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 2:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<9; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 3:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<8; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 4:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<7; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 5:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<6; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 6:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<5; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 7:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<4; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 8:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<3; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 9:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<2; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 10:
						JComboDeuxiemeNombre.removeAllItems();
						JComboDeuxiemeNombre.addItem(maxSize);

						break;
					}

					// si l'opérateur est "-"
				} else {

					switch (premierNombre) {

					case 0:
						JComboDeuxiemeNombre.removeAllItems();
						JComboDeuxiemeNombre.addItem(maxSize);
						break;

					case 1:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<2; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 2:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<3; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 3:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<4; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 4:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<5; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 5:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<6; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 6:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<7; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 7:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<8; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 8:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<9; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 9:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<10; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 10:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<11; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;
					}
				}

			} else if(EventCombo.getSource() == laVue.getOperateurs()) {

				String operateur = laVue.getStringOperateurs();
				//pour les soustractions les restrictions sont différentes
				if(operateur == "-") {

					int premierNombre = laVue.getPremierNombre();
					JComboBox<Integer> JComboDeuxiemeNombre = laVue.getComboDeux();

					//en fonction du chiffre sélectionné dans la premiere liste, la deuxieme liste se met à jour
					// avec la var maxSize
					int maxSize = 0;

					switch (premierNombre) {

					case 0:
						JComboDeuxiemeNombre.removeAllItems();
						JComboDeuxiemeNombre.addItem(maxSize);
						break;

					case 1:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<2; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 2:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<3; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 3:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<4; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 4:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<5; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 5:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<6; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 6:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<7; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 7:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<8; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 8:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<9; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 9:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<10; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 10:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<11; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;
					}

				} else {

					int premierNombre = laVue.getPremierNombre();
					JComboBox<Integer> JComboDeuxiemeNombre = laVue.getComboDeux();
					int maxSize = 0;

					switch (premierNombre) {

					case 0:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<11; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 1:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<10; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 2:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<9; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 3:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<8; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 4:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<7; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 5:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<6; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 6:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<5; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 7:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<4; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 8:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<3; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 9:
						JComboDeuxiemeNombre.removeAllItems();
						for (maxSize=0; maxSize<2; maxSize++ )  {
							JComboDeuxiemeNombre.addItem(maxSize);
						}
						break;

					case 10:
						JComboDeuxiemeNombre.removeAllItems();
						JComboDeuxiemeNombre.addItem(maxSize);

						break;

					}
				}
			}
		}
	}
}