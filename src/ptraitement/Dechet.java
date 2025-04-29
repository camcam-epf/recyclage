/* projet centre de recyclage
 * Camille Orjas
 * Garance Dubois
 * 26 janvier 2025
 */
package ptraitement;

import java.time.LocalDate;

/**
 *
 * @author camil
 */
public class Dechet {

    //attributs
    private String ID;
    private String type;
    private float qtte;
    private String mailClient;
    private LocalDate dateCollecte;
    private String mailCentre;

    //constructeurs
    public Dechet(String ID, String type, float qtte, String client) {
        this.ID = ID;
        this.type = type;
        this.qtte = qtte;
        this.mailClient = client;
        dateCollecte = null;
        mailCentre = null;
    }

    public Dechet(String ID, String type, float qtte, String client, LocalDate dateCollecte, String centre) {
        this.ID = ID;
        this.type = type;
        this.qtte = qtte;
        this.mailClient = client;
        this.dateCollecte = dateCollecte;
        this.mailCentre = centre;
    }

    //methode toString
    @Override
    public String toString() {
        return "identifiant du dechet : " + ID + "\ntype du dechet : " + type + "\nquantite : " + qtte + "\nclient : " + mailClient
                + "\ndate de collecte : " + dateCollecte + "\ncentre de tri : " + mailCentre;
    }

    public String versFichier() {
        if (dateCollecte == null){
            dateCollecte = LocalDate.parse("2000-01-01");
        }
        if (mailCentre==null){
            mailCentre =" ";
        }
        return ID + " - " + type + " - " + qtte + " - " + mailClient + " - " + dateCollecte + " - " + mailCentre;
    }

    public String getType() {
        return type;
    }

    public String getCentre() {
        return mailCentre;
    }

    public float getQuantite() {
        return qtte;
    }

    public LocalDate getDateCollecte() {
        return dateCollecte;
    }

    public String getClient() {
        return mailClient;
    }

    public String getID() {
        return ID;
    }

    public void setDateCollecte(LocalDate dateCollecte) {
        this.dateCollecte = dateCollecte;
    }

    public void setCentre(String mailCentre) {
        this.mailCentre = mailCentre;
    }
}
