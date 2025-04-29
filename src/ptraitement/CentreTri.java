/* projet centre de recyclage
 * Camille Orjas
 * Garance Dubois
 * 26 janvier 2025
 */
package ptraitement;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class CentreTri extends Utilisateur {

    //attributs
    private ArrayList<String> typeDechetsAcceptes;
    private LocalTime heureOuverture;
    private LocalTime heureFermeture;
    private float capaciteStockage;

    //constructeurs
    public CentreTri(String mail, String mdp, String nom, String tel, String adresse, ArrayList<String> typeDechets, LocalTime heureOuverture,
            LocalTime heureFermeture, float capaciteStockage) {
        super(mail, mdp, nom, tel, adresse);
        this.typeDechetsAcceptes = typeDechets;
        this.heureOuverture = heureOuverture;
        this.heureFermeture = heureFermeture;
        this.capaciteStockage = capaciteStockage;
    }

    public CentreTri(String mail, String mdp, String nom, String tel, String adresse, ArrayList<String> typeDechets, LocalTime heureOuverture,
            LocalTime heureFermeture, float capaciteStockage, ArrayList<String> histo) {
        super(mail, mdp, nom, tel, adresse, histo);
        this.typeDechetsAcceptes = typeDechets;
        this.heureOuverture = heureOuverture;
        this.heureFermeture = heureFermeture;
        this.capaciteStockage = capaciteStockage;
    }

    //methode toString
    @Override
    public String toString() {
        return super.toString() + "\ntype de dechets acceptes :\n" + typeDechetsAcceptes + "\nheure d'ouverture : " + heureOuverture
                + "\nheure de fermeture : " + heureFermeture + "\ncapacite de stockage en tonnes : " + capaciteStockage;
    }

    @Override
    public String versFichier() {
        return super.versFichier() + " - " + typeDechetsAcceptes + " - " + heureOuverture + " - " + heureFermeture + " - " + capaciteStockage;
    }

    public LocalTime getOuverture() {
        return heureOuverture;
    }

    public LocalTime getFermeture() {
        return heureFermeture;
    }

    public ArrayList<String> getTypeDechetAccepte() {
        return typeDechetsAcceptes;
    }

    public float getCapacite() {
        return capaciteStockage;
    }

    public void setOuverture(LocalTime ouverture) {
        this.heureOuverture = ouverture;
    }

    public void setFermeture(LocalTime fermeture) {
        this.heureFermeture = fermeture;
    }

    public void setCapacite(float capacite) {
        this.capaciteStockage = capacite;
    }

    public void setTypeDechetAccepte(ArrayList<String> type) {
        this.typeDechetsAcceptes = type;
    }
}
