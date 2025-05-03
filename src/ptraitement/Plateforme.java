/* projet centre de recyclage
 * Camille Orjas
 * Garance Dubois
 * 26 janvier 2025
 */
package ptraitement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import ptraitement.CentreTri;

/**
 *
 * @author camil
 */
public class Plateforme {

    //attributs
    private ArrayList<Client> listeClients;
    private ArrayList<Dechet> listeDechets;
    private ArrayList<Dechet> listeDemandes;
    private ArrayList<CentreTri> listeCentres;

    //constructeurs
    public Plateforme() {
        listeClients = new ArrayList<>();
        listeDechets = new ArrayList<>();
        listeDemandes = new ArrayList<>();
        listeCentres = new ArrayList<>();
    }

    //methodes
//    fait : sauvegerder (clients, centres, dechets, demandes), charger(clients, centres, dechets, demandes), inscription(client, centre), 
//    connexion, rechercher centre, modifier(centre, particulier, entreprise), MAJ, demande Collecte, fixer date collecte, declarer depot
//    consulter liste collecte(centre, entreprise)
//    a faire :  trierHistoDechets
    public ArrayList<CentreTri> getListeCentres() {
        return listeCentres;
    }

    public ArrayList<Client> getListeClients() {
        return listeClients;
    }

    public ArrayList<Dechet> getListeDemandes() {
        return listeDemandes;
    }
    
    public ArrayList<Dechet> getListeDechets(){
        return listeDechets;
    }

    public Entreprise inscriptionEnt(String email, String mdp, String nom, String tel, String adresse) throws IOException {
        Entreprise entreprise = new Entreprise(email, mdp, nom, tel, adresse);
        listeClients.add(entreprise);
        sauvegarderClients();
        return entreprise;
    }

    public Particulier inscriptionPart(String email, String mdp, String nom, String prenom, String tel, String adresse) throws IOException {
        Particulier particulier = new Particulier(email, mdp, nom, prenom, tel, adresse);
        listeClients.add(particulier);
        sauvegarderClients();
        return particulier;
    }

    public CentreTri inscriptionCentre(String email, String mdp, String nom, String tel, String adresse, ArrayList<String> typeD,
            LocalTime ouverture, LocalTime fermeture, float capacite) throws IOException {
        CentreTri centre = new CentreTri(email, mdp, nom, tel, adresse, typeD, ouverture, fermeture, capacite);
        listeCentres.add(centre);
        sauvegarderCentres();
        return (centre);
    }

    public void sauvegarderClients() throws IOException {
        try (FileWriter fich = new FileWriter("Liste_Clients.txt")) {
            for (int i = 0; i < listeClients.size(); i++) {
                String ligne = listeClients.get(i).versFichier();
                fich.write(ligne + System.lineSeparator());
            }
        }
    }

    public void sauvegarderDechets() throws IOException {
        try (FileWriter fich = new FileWriter("Liste_Dechets.txt")) {
            for (int i = 0; i < listeDechets.size(); i++) {
                String ligne = listeDechets.get(i).versFichier();
                fich.write(ligne + System.lineSeparator());
            }
        }
    }

    public void sauvegarderDemandes() throws IOException {
        try (FileWriter fich = new FileWriter("Liste_Demandes.txt")) {
            for (int i = 0; i < listeDemandes.size(); i++) {
                String ligne = listeDemandes.get(i).versFichier();
                fich.write(ligne + System.lineSeparator());
            }
        }
    }

    public void sauvegarderCentres() throws IOException {
        try (FileWriter fich = new FileWriter("Liste_Centres.txt")) {
            for (int i = 0; i < listeCentres.size(); i++) {
                String ligne = listeCentres.get(i).versFichier();
                fich.write(ligne + System.lineSeparator());
            }
        }
    }

    public void chargerClients() throws FileNotFoundException, IOException {
        try (FileReader fich = new FileReader("Liste_Clients.txt")) {
            BufferedReader br = new BufferedReader(fich);
            String ligne = br.readLine();
            while (ligne != null) {
                String[] tab = ligne.split(" - ");
                String email = tab[0];
                String mdp = tab[1];
                String nom = tab[2];
                String num = tab[3];
                String adresse = tab[4];
                ArrayList<String> histoDechets;
                if (tab[5].length() > 2) {
                    tab[5] = tab[5].substring(1, tab[5].length() - 1);
                    histoDechets = new ArrayList<>(Arrays.asList(tab[5].split(", ")));
                    if (tab.length == 7) {
                        String prenom = tab[6];
                        Particulier part = new Particulier(email, mdp, nom, prenom, num, adresse, histoDechets);
                        listeClients.add(part);
                    } else {
                        Entreprise ent = new Entreprise(email, mdp, nom, num, adresse, histoDechets);
                        listeClients.add(ent);
                    }
                } else {
                    if (tab.length == 7) {
                        String prenom = tab[6];
                        Particulier part = new Particulier(email, mdp, nom, prenom, num, adresse);
                        listeClients.add(part);
                    } else {
                        Entreprise ent = new Entreprise(email, mdp, nom, num, adresse);
                        listeClients.add(ent);
                    }
                }
                ligne = br.readLine();
            }
        }
    }

    public void chargerCentres() throws FileNotFoundException, IOException {
        try (FileReader fich = new FileReader("Liste_Centres.txt")) {
            BufferedReader br = new BufferedReader(fich);
            String ligne = br.readLine();
            while (ligne != null) {
                String[] tab = ligne.split(" - ");
                String email = tab[0];
                String mdp = tab[1];
                String nom = tab[2];
                String num = tab[3];
                String adresse = tab[4];
                tab[6] = tab[6].substring(1, tab[6].length() - 1);
                ArrayList<String> typeDechets = new ArrayList<>(Arrays.asList(tab[6].split(", ")));
                LocalTime ouverture = LocalTime.parse(tab[7]);
                LocalTime fermeture = LocalTime.parse(tab[8]);
                float capacite = Float.parseFloat(tab[9]);
                CentreTri centre;
                if (tab[5].length() > 2) {
                    tab[5] = tab[5].substring(1, tab[5].length() - 1);
                    ArrayList<String> histoDechets = new ArrayList<>(Arrays.asList(tab[5].split(", ")));
                    centre = new CentreTri(email, mdp, nom, num, adresse, typeDechets, ouverture, fermeture, capacite, histoDechets);
                } else {
                    centre = new CentreTri(email, mdp, nom, num, adresse, typeDechets, ouverture, fermeture, capacite);
                }
                listeCentres.add(centre);
                ligne = br.readLine();
            }
        }
    }

    public void chargerDechets() throws FileNotFoundException, IOException {
        try (FileReader fich = new FileReader("Liste_Dechets.txt")) {
            BufferedReader br = new BufferedReader(fich);
            String ligne = br.readLine();
            while (ligne != null) {
                String[] tab = ligne.split(" - ");
                String ID = tab[0];
                String type = tab[1];
                float qtte = Float.parseFloat(tab[2]);
                String client = tab[3];
                LocalDate dateCollecte = LocalDate.parse(tab[4]);
                String centre = tab[5];
                Dechet dechet = new Dechet(ID, type, qtte, client, dateCollecte, centre);
                listeDechets.add(dechet);
                ligne = br.readLine();
            }
        }
    }

    public void chargerDemandes() throws FileNotFoundException, IOException {
        try (FileReader fich = new FileReader("Liste_Demandes.txt")) {
            BufferedReader br = new BufferedReader(fich);
            String ligne = br.readLine();
            while (ligne != null) {
                String[] tab = ligne.split(" - ");
                String ID = tab[0];
                String type = tab[1];
                float qtte = Float.parseFloat(tab[2]);
                String client = tab[3];
                LocalDate dateCollecte = LocalDate.parse(tab[4]);
                if (dateCollecte.equals(LocalDate.parse("2000-01-01"))) {
                    dateCollecte = null;
                }
                String centre = tab[5];
                if (centre.equals(" ")) {
                    centre = null;
                }
                Dechet dechet = new Dechet(ID, type, qtte, client, dateCollecte, centre);
                listeDemandes.add(dechet);
                ligne = br.readLine();
            }
        }
    }

    public Utilisateur connexion(String email, String mdp) {
        Utilisateur uti;
        for (int i = 0; i < listeClients.size(); i++) {
            if (listeClients.get(i).getMail().equals(email)) {
                uti = listeClients.get(i);
                while (listeClients.get(i).getMdp().equals(mdp) == false) {
                    return null;
                }
                return uti;
            }
        }
        for (int i = 0; i < listeCentres.size(); i++) {
            if (listeCentres.get(i).getMail().equals(email)) {
                uti = listeCentres.get(i);
                while (listeCentres.get(i).getMdp().equals(mdp) == false) {
                    return null;
                }
                return uti;
            }
        }
        return null;
    }

    public void modifier(CentreTri centre, String email, String mdp, String nom, String tel, String adresse, LocalTime ouv, LocalTime ferm,
            ArrayList<String> typeD, float capacite) throws IOException {
        centre.setMail(email);
        centre.setMdp(mdp);
        centre.setNom(nom);
        centre.setTel(tel);
        centre.setAdresse(adresse);
        centre.setOuverture(ouv);
        centre.setFermeture(ferm);
        centre.setTypeDechetAccepte(typeD);
        centre.setCapacite(capacite);
        sauvegarderCentres();
    }

    public void modifier(Entreprise ent, String email, String mdp, String nom, String tel, String adresse) throws IOException {
        ent.setMail(email);
        ent.setMdp(mdp);
        ent.setNom(nom);
        ent.setTel(tel);
        ent.setAdresse(adresse);
        sauvegarderClients();
    }

    public void modifier(Particulier parti, String email, String mdp, String nom, String prenom, String tel, String adresse) throws IOException {
        parti.setMail(email);
        parti.setMdp(mdp);
        parti.setNom(nom);
        parti.setPrenom(prenom);
        parti.setTel(tel);
        parti.setAdresse(adresse);
        sauvegarderClients();
    }

    public void demandeCollecte(Entreprise ent, String ID, String type, float qtte) throws IOException {
        Dechet newdechet = new Dechet(ID, type, qtte, ent.getMail());
        listeDemandes.add(newdechet);
        sauvegarderDemandes();
    }

    public Dechet fixerDateCollecte(CentreTri centre, Dechet demande, Object Date) throws IOException {
        demande.setCentre(centre.getMail());
        Date dateUtil = (Date) Date; // Récupère la date du spinner
        LocalDate date = dateUtil.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        demande.setDateCollecte(date);
        sauvegarderDemandes();
        return demande;
    }

    public void declarerdepot(Particulier parti, String ID, String type, String centre, float qtte, Object Date) throws IOException {
        Date dateUtil = (Date) Date; // Récupère la date du spinner
        LocalDate date = dateUtil.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        CentreTri Centre = null;
        for (int i = 0; i < listeCentres.size(); i++) {
            if (listeCentres.get(i).getNom().equals(centre)) {
                Centre = listeCentres.get(i);
            }
        }
        Dechet newdechet = new Dechet(ID, type, qtte, parti.getMail(), date, Centre.getMail());
        listeDechets.add(newdechet);
        ArrayList<String> histo = parti.getHisto();
        histo.add(newdechet.getID());
        parti.setHisto(histo);
        sauvegarderDechets();
        sauvegarderClients();
    }

    public void MAJ() throws IOException {
        for (int i = 0; i < listeDemandes.size(); i++) {
            if (listeDemandes.get(i).getDateCollecte() != null && listeDemandes.get(i).getDateCollecte().isBefore(LocalDate.now())) {
                String mailClient = listeDemandes.get(i).getClient();
                for (int j = 0; j < listeClients.size(); j++) {
                    if (listeClients.get(j).getMail().equals(mailClient)) {
                        ArrayList<String> histo = listeClients.get(j).getHisto();
                        histo.add(listeDemandes.get(i).getID());
                        listeClients.get(j).setHisto(histo);
                    }
                }
                listeDechets.add(listeDemandes.get(i));
                listeDemandes.remove(listeDemandes.get(i));
            }
        }
        sauvegarderDechets();
        sauvegarderDemandes();
        sauvegarderClients();
    }

    public ArrayList<Dechet> TriDate(ArrayList<Dechet> liste) {
        liste.sort(Comparator.comparing((Dechet d) -> d.getDateCollecte()).reversed());
        return liste;
    }

    public ArrayList<Dechet> TriCentre(ArrayList<Dechet> liste){
        liste.sort(Comparator.comparing((Dechet d) -> d.getCentre()));
        return liste;
    }
    
    public ArrayList<Dechet> TriClient(ArrayList<Dechet> liste){
        liste.sort(Comparator.comparing((Dechet d) -> d.getClient()));
        return liste;
    }
    
    public ArrayList<Dechet> TriType(ArrayList<Dechet> liste) {
    liste.sort(Comparator.comparing(d -> d.getType()));
    return liste;
}

}
