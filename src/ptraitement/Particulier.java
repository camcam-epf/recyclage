/* projet centre de recyclage
 * Camille Orjas
 * Garance Dubois
 * 26 janvier 2025
 */
package ptraitement;

import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Particulier extends Client {

    //attributs
    private String prenom;

    //constructeurs
    public Particulier(String mail, String mdp, String nom, String prenom, String tel, String adresse) {
        super(mail, mdp, nom, tel, adresse);
        this.prenom = prenom;
    }

    public Particulier(String mail, String mdp, String nom, String prenom, String tel, String adresse, ArrayList<String> histodechet) {
        super(mail, mdp, nom, tel, adresse, histodechet);
        this.prenom = prenom;
    }

    //methode toString
    @Override
    public String toString() {
        return super.toString() + "\nprenom : " + prenom;
    }

    @Override
    public String versFichier() {
        return super.versFichier() + " - " + prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
