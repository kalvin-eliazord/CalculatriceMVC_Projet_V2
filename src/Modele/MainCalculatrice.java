package Modele;
import Controleur.CalculatriceControleur;
import Vue.CalculatricePub;
import Vue.CalculatriceVue;

// instanciation des classes MVC

public class MainCalculatrice {
    
    public static void main(String[] args) {
    	
    	CalculatricePub laPub = new CalculatricePub();
    	
    	CalculatriceVue laVue = new CalculatriceVue();
        
    	CalculatriceModele leModele = new CalculatriceModele();
        
        CalculatriceControleur leControleur = new CalculatriceControleur(laVue, leModele, laPub); 
        
    }
}
