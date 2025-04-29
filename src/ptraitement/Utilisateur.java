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
public class Utilisateur {

    //attributs
    private String mail;
    private String mdp;
    private String nom;
    private String tel;
    private String adresse;
    private ArrayList<String> historiqueDechets;

    //constructeurs
    public Utilisateur(String mail, String mdp, String nom, String tel, String adresse) {
        this.mail = mail;
        this.mdp = mdp;
        this.nom = nom;
        this.tel = tel;
        this.adresse = adresse;
        historiqueDechets = new ArrayList<>();
    }

    public Utilisateur(String mail, String mdp, String nom, String tel, String adresse, ArrayList<String> historiqueDechets) {
        this.mail = mail;
        this.mdp = mdp;
        this.nom = nom;
        this.tel = tel;
        this.adresse = adresse;
        this.historiqueDechets = historiqueDechets;
    }

    //methode toString
    @Override
    public String toString() {
        return "email : " + mail + "\nmot de passe : " + mdp + "\nnom : " + nom + "\nnumero de telephone : " + tel
                + "\nadresse : " + adresse + "\nhistorique de dechets : \n" + historiqueDechets;
    }

    public String versFichier() {
        return mail + " - " + mdp + " - " + nom + " - " + tel + " - " + adresse + " - " + historiqueDechets;
    }

    public String getMail() {
        return mail;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<String> getHisto() {
        return historiqueDechets;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setHisto(ArrayList<String> histo) {
        historiqueDechets = histo;
    }
}
