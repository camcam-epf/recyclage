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
public class Client extends Utilisateur {
    //attributs

    //constructeurs
    public Client(String mail, String mdp, String nom, String tel, String adresse) {
        super(mail, mdp, nom, tel, adresse);
    }

    public Client(String mail, String mdp, String nom, String tel, String adresse, ArrayList<String> histo) {
        super(mail, mdp, nom, tel, adresse, histo);
    }
}
