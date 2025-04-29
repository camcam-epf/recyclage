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
public class Entreprise extends Client {

    //attributs
    //constructeurs
    public Entreprise(String mail, String mdp, String nom, String tel, String adresse) {
        super(mail, mdp, nom, tel, adresse);
    }

    public Entreprise(String mail, String mdp, String nom, String tel, String adresse, ArrayList<String> histo) {
        super(mail, mdp, nom, tel, adresse, histo);
    }

}
