package Vue;

import java.awt.event.ActionListener;
import javax.swing.*;

//la seule fonctionnalit� de la vue est d'afficher 
//ce que l'utilisateur va voir, aucun calculs ne sera fait
public class CalculatriceVue extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Integer> premierNombre  = new JComboBox<Integer>();
	private JComboBox<String> operateurs = new JComboBox<String>();
	private JComboBox<Integer> deuxiemeNombre = new JComboBox<Integer>();
	private JLabel labelEgal = new JLabel(" = ");
	private JComboBox<Integer> resultatPropose = new JComboBox<Integer>();
	private JButton bouttonVerification = new JButton("V�rifier le R�sultat");
	private JLabel affichageBonMauvais = new JLabel(" ");
	private JLabel affichageResultat = new JLabel();

	//constructeur de la classe CalculatriceVue

	public CalculatriceVue(){

		// ihm param�tres
		JPanel fenetreCalcul = new JPanel();

		setTitle("EditCALC V2 - par Kalvin ELIAZORD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); // plein �cran par d�faut 

		// Affectation des valeurs des listes de JCombobox
		Integer[] listeNombre = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		String[] listeOperateur = new String[] {"+", "-"};

		// affectation des JCombobox qui poss�de une liste � une variable 
		// pour pouvoir l'ajouter � la fenetreCalcul 
		premierNombre = new JComboBox<Integer>(listeNombre);
		operateurs = new JComboBox<String>(listeOperateur);
		deuxiemeNombre = new JComboBox<Integer>(listeNombre);
		resultatPropose = new JComboBox<Integer>(listeNombre);

		// ajout des objets graphiques de l'ihm calculatrice dans le conteneur
		fenetreCalcul.add(premierNombre);
		fenetreCalcul.add(operateurs);
		fenetreCalcul.add(deuxiemeNombre);
		fenetreCalcul.add(labelEgal);
		fenetreCalcul.add(resultatPropose);
		fenetreCalcul.add(bouttonVerification);
		fenetreCalcul.add(affichageBonMauvais);
		fenetreCalcul.add(affichageResultat);

		this.add(fenetreCalcul);
	}

	// les getter
	public int getPremierNombre(){ 
		return (int) premierNombre.getSelectedItem();
	}

	public int getDeuxiemeNombre(){
		return (int) deuxiemeNombre.getSelectedItem();
	}

	public String getStringOperateurs() {
		return (String) operateurs.getSelectedItem();
	}

	public int getResultatPropose() {
		return (int) resultatPropose.getSelectedItem();
	}

	// affiche le r�sultat du calcul dans le label Resultat
	public void setAffichageBonMauvais(String bonOuMauvais){
		affichageBonMauvais.setText(bonOuMauvais);
	}

	public void setAffichageResultat(int resultatOperation){
		affichageResultat.setText(Integer.toString(resultatOperation));	
	}

	public void setAffichageResultatNettoyage(){
		affichageResultat.setText(" ");	
	}

	// m�thode qui ajoute une action de type actionlistener 
	public void BtnListener(ActionListener event){
		bouttonVerification.addActionListener(event);
	}
	
	public void ComboListener(ActionListener event){
		premierNombre.addActionListener(event);
		operateurs.addActionListener(event);
	}

	public JComboBox<String> getOperateurs() {
		return operateurs;
	}

	public JComboBox<Integer> getComboDeux() {
		return deuxiemeNombre;
	}

	public JComboBox<Integer> getComboUn() {
		return premierNombre;
	}	
}
