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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
    public Entreprise inscriptionEnt(String email, String mdp, String nom, String tel, String adresse) {
        Entreprise entreprise = new Entreprise(email, mdp, nom, tel, adresse);
        listeClients.add(entreprise);
        return entreprise;
    }

    public Particulier inscriptionPart(String email, String mdp, String nom, String prenom, String tel, String adresse) {

//        for (int i = 0; i < listeClients.size(); i++) {
//            if (listeClients.get(i).getMail().equals(email)) {
//                System.out.println("Vous avez deja un compte, veuillez vous connecter");
//                connexion();
//            }
//        }
        Particulier particulier = new Particulier(email, mdp, nom, prenom, tel, adresse);
        listeClients.add(particulier);
//                System.out.println(particulier);
        return particulier;

    }

    public CentreTri inscriptionCentre(String email, String mdp, String nom, String tel, String adresse, ArrayList<String> typeD,
            LocalTime ouverture, LocalTime fermeture, float capacite) {

//        for (int i = 0; i < listeCentres.size(); i++) {
//            if (listeCentres.get(i).getMail().equals(email)) {
//                System.out.println("Vous avez deja un compte, veuillez vous connecter");
//                connexion();
//            }
//        }
        CentreTri centre = new CentreTri(email, mdp, nom, tel, adresse, typeD, ouverture, fermeture, capacite);
        listeCentres.add(centre);
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
//        System.out.println(listeClients);
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
//        System.out.println(listeCentres);
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

    public void rechercherCentre() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez-vous voir la liste des centres de recyclage (A) ou en chercher un a "
                + "partir des dechets qu'il accepte (B) ?");
        String rep = sc.nextLine();
        switch (rep) {
            case "A" -> {
                for (int i = 0; i < listeCentres.size(); i++) {
                    System.out.println(i + 1 + ". " + listeCentres.get(i).getNom());
                }
                System.out.println("De quel centre voulez-vous consulter les infos (donnez le numero) ?");
                int reponse = sc.nextInt();
                for (int j = 0; j < listeCentres.size(); j++) {
                    if ((reponse - 1) == j) {
                        System.out.println("Heure d'ouverture : " + listeCentres.get(j).getOuverture() + "\nHeure de fermeture : "
                                + listeCentres.get(j).getFermeture() + "\nType de dechets acceptes : " + listeCentres.get(j).getTypeDechetAccepte());
                    }
                }
            }
            case "B" -> {
                System.out.println("Quel type de dechets recherchez-vous a recycler ?");
                rep = sc.nextLine();
                for (int i = 0; i < listeCentres.size(); i++) {
                    for (int j = 0; j < listeCentres.get(i).getTypeDechetAccepte().size(); j++) {
                        if (rep.equalsIgnoreCase(listeCentres.get(i).getTypeDechetAccepte().get(j))) {
                            System.out.println(listeCentres.get(i).getNom() + "\nHeure d'ouverture : " + listeCentres.get(j).getOuverture()
                                    + "\nHeure de fermeture : " + listeCentres.get(j).getFermeture());
                        }
                    }
                }
            }
            default -> {
            }
        }
    }

    public void modifier(CentreTri centre) {
        Scanner sc = new Scanner(System.in);
        System.out.println(centre + """
                           \nQue souhaitez-vous modifier (donnez le numero) ? : 
                           1.votre email
                           2.votre mot de passe
                           3.votre nom
                           4.votre numero de telephone
                           5.votre adresse
                           6.votre heure d'ouverture
                           7.votre heure de fermeture
                           8.vos types de dechets acceptes
                           9.votre capacite de stockage""");
        int rep = sc.nextInt();
        sc.nextLine();
        switch (rep) {
            case 1 -> {
                System.out.println("Entrez votre nouvel email");
                String reponse = sc.nextLine();
                centre.setMail(reponse);
            }
            case 2 -> {
                System.out.println("Entrez votre nouveau mot de passe");
                String reponse = sc.nextLine();
                centre.setMdp(reponse);
            }
            case 3 -> {
                System.out.println("Entrez votre nouveau nom");
                String reponse = sc.nextLine();
                centre.setNom(reponse);
            }
            case 4 -> {
                System.out.println("Entrez votre nouveau numero de telephone");
                String reponse = sc.nextLine();
                centre.setTel(reponse);
            }
            case 5 -> {
                System.out.println("Entrez votre nouvelle adrese");
                String reponse = sc.nextLine();
                centre.setAdresse(reponse);
            }
            case 6 -> {
                System.out.println("Entrez votre nouvelle heure d'ouverture");
                LocalTime reponse = LocalTime.parse(sc.nextLine());
                centre.setOuverture(reponse);
            }
            case 7 -> {
                System.out.println("Entrez votre nouvelle heure de fermeture");
                LocalTime reponse = LocalTime.parse(sc.nextLine());
                centre.setFermeture(reponse);
            }
            case 8 -> {
                System.out.println("Voulez-vous ajouter un type de dechet (1) ou en retirer un (2) ?");
                int reponse = sc.nextInt();
                sc.nextLine();
                switch (reponse) {
                    case 1 -> {
                        System.out.println("Entrez le type de dechets a ajouter");
                        String stock = sc.nextLine();
                        ArrayList<String> stock1 = centre.getTypeDechetAccepte();
                        stock1.add(stock);
                        centre.setTypeDechetAccepte(stock1);
                    }
                    case 2 -> {
                        System.out.println("Entrez le type de dechet a retirer");
                        String stock = sc.nextLine();
                        if (centre.getTypeDechetAccepte().contains(stock)) {
                            ArrayList<String> stock1 = centre.getTypeDechetAccepte();
                            stock1.remove(stock);
                            centre.setTypeDechetAccepte(stock1);
                        } else {
                            System.out.println("Ce type de dechet n'est pas dans votre liste actuelle");
                        }
                    }
                }
            }
            case 9 -> {
                System.out.println("Entrez votre nouvelle capacite de stockage");
                float reponse = sc.nextFloat();
                centre.setCapacite(reponse);
            }
        }
    }

    public void modifier(Entreprise ent) {
        Scanner sc = new Scanner(System.in);
        System.out.println(ent + """
                           \nQue souhaitez-vous modifier (donnez le numero) ? : 
                           1.votre email
                           2.votre mot de passe
                           3.votre nom
                           4.votre numero de telephone
                           5.votre adresse""");
        int rep = sc.nextInt();
        sc.nextLine();
        switch (rep) {
            case 1 -> {
                System.out.println("Entrez votre nouvel email");
                String reponse = sc.nextLine();
                ent.setMail(reponse);
            }
            case 2 -> {
                System.out.println("Entrez votre nouveau mot de passe");
                String reponse = sc.nextLine();
                ent.setMdp(reponse);
            }
            case 3 -> {
                System.out.println("Entrez votre nouveau nom");
                String reponse = sc.nextLine();
                ent.setNom(reponse);
            }
            case 4 -> {
                System.out.println("Entrez votre nouveau numero de telephone");
                String reponse = sc.nextLine();
                ent.setTel(reponse);
            }
            case 5 -> {
                System.out.println("Entrez votre nouvelle adrese");
                String reponse = sc.nextLine();
                ent.setAdresse(reponse);
            }
        }
    }

    public void modifier(Particulier parti) {
        Scanner sc = new Scanner(System.in);
        System.out.println(parti + """
                           \nQue souhaitez-vous modifier (donnez le numero) ? : 
                           1.votre email
                           2.votre mot de passe
                           3.votre nom
                           4.votre prenom
                           5.votre numero de telephone
                           6.votre adresse""");
        int rep = sc.nextInt();
        sc.nextLine();
        switch (rep) {
            case 1 -> {
                System.out.println("Entrez votre nouvel email");
                String reponse = sc.nextLine();
                parti.setMail(reponse);
            }
            case 2 -> {
                System.out.println("Entrez votre nouveau mot de passe");
                String reponse = sc.nextLine();
                parti.setMdp(reponse);
            }
            case 3 -> {
                System.out.println("Entrez votre nouveau nom");
                String reponse = sc.nextLine();
                parti.setNom(reponse);
            }
            case 4 -> {
                System.out.println("Entrez votre nouveau prenom");
                String reponse = sc.nextLine();
                parti.setPrenom(reponse);
            }
            case 5 -> {
                System.out.println("Entrez votre nouveau numero de telephone");
                String reponse = sc.nextLine();
                parti.setTel(reponse);
            }
            case 6 -> {
                System.out.println("Entrez votre nouvelle adrese");
                String reponse = sc.nextLine();
                parti.setAdresse(reponse);
            }
        }
    }

    public void demandeCollecte(Entreprise ent) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel nom souhaitez vous donner a votre dechet ?");
        String ID = scanner.nextLine();
        System.out.println("A quel type de dechet appartient votre dechet ?");
        String type = scanner.nextLine();
        boolean stock = false;
        for (int i = 0; i < listeCentres.size(); i++) {
            if (listeCentres.get(i).getTypeDechetAccepte().contains(type)) {
                stock = true;
            }
        }
        if (stock) {
            System.out.println("""
                                   Au moins un centre prends en charge le type de dechets que vous avez demande
                                   Quelle quantite voulez vous faire collecter en tonnes ?""");
            Float qtte = scanner.nextFloat();
            Dechet newdechet = new Dechet(ID + listeDechets.size(), type, qtte, ent.getMail());
            listeDemandes.add(newdechet);
        } else {
            System.out.println("Aucun centre n'accepte ce type de dechet.");
        }
    }

    public Dechet fixerDateCollecte(CentreTri centre) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Voici la liste des dechets en attente de collecte et qui correspondent aux types de dechets que vous acceptez "
                + "et a votre capacite de stockage:");
        int numero = 1;
        ArrayList<Dechet> demandes = new ArrayList<>();
        for (int i = 0; i < listeDemandes.size(); i++) {
            if (listeDemandes.get(i).getCentre() == null) {
                if (centre.getTypeDechetAccepte().contains(listeDemandes.get(i).getType())
                        && centre.getCapacite() >= listeDemandes.get(i).getQuantite()) {
                    System.out.println(numero + "." + listeDemandes.get(i));
                    demandes.add(listeDemandes.get(i));
                    numero += 1;
                }
            }
        }
        System.out.println("Souhaitez-vous accepter une demande ? (oui/non)");
        String reponse = sc.nextLine();
        if (reponse.equalsIgnoreCase("oui")) {
            System.out.println("Laquelle (donnez le numero) ?");
            int rep = sc.nextInt();
            Dechet demande = demandes.get(rep - 1);
            demande.setCentre(centre.getMail());
            System.out.println("Entrez la date de collecte (Format: YYYY-MM-DD) :");
            sc.nextLine();
            String dateStr = sc.nextLine();
            demande.setDateCollecte(LocalDate.parse(dateStr));
            return demande;
        }
        return null;
    }

    public void consulterListeCollectes(Entreprise ent) {
        for (int i = 0; i < listeDemandes.size(); i++) {
            if (listeDemandes.get(i).getClient().equals(ent.getMail())) {
                System.out.println(listeDemandes.get(i));
            }
        }
    }

    public void consulterListeCollecte(CentreTri centre) {
        for (int i = 0; i < listeDemandes.size(); i++) {
            if (listeDemandes.get(i).getCentre() != null && listeDemandes.get(i).getCentre().equals(centre.getMail())) {
                System.out.println(listeDemandes.get(i));
            }
        }
    }

    public void declarerdepot(Particulier parti) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel nom souhaitez vous donner a votre dechet ?");
        String ID = scanner.nextLine();
        System.out.println("Quel est type de dechet du depot");
        String type = scanner.nextLine();
        int numero = 1;
        ArrayList<String> centres = new ArrayList<>();
        System.out.println("Les centres ci-dessous prennent en charge le type de dechets que vous avez depose :");
        for (int i = 0; i < listeCentres.size(); i++) {
            if (listeCentres.get(i).getTypeDechetAccepte().contains(type)) {
                System.out.println(numero + "." + listeCentres.get(i).getNom());
                numero += 1;
                centres.add(listeCentres.get(i).getMail());
            }
        }
        if (numero == 1) {
            System.out.println("Aucun centre n'accepte ce type de déchet.");
        }
        System.out.println("Dans lequel avez vous deposer vos dechets (numero)?");
        numero = scanner.nextInt();
        System.out.println("Entrez la quantite du depot");
        float qtte = scanner.nextFloat();
        System.out.println("Entrez la date du depot (Format: YYYY-MM-DD) :");
        scanner.nextLine();
        LocalDate date = LocalDate.parse(scanner.nextLine());
        Dechet newdechet = new Dechet(ID + listeDechets.size(), type, qtte, parti.getMail(), date, centres.get(numero - 1));
        listeDechets.add(newdechet);
        ArrayList<String> histo = parti.getHisto();
        histo.add(newdechet.getID());
        parti.setHisto(histo);
    }

    public void MAJ() {
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
    }

//    public void consulterHisto(Client client) {
//        System.out.println("""
//                           Par quoi souhaitez-vous trier vote historique de dechets ?
//                           1.par date de collecte
//                           2.par date de depot
//                           3.par type de dechet
//                           4.par centre de recyclage""");
//        Scanner sc = new Scanner(System.in);
//        int rep = sc.nextInt();
//        switch (rep) {
//            case 1 -> {
//                for (int i = client.getHisto().size()-1;i>=0;i--){
//                    if
//                }
//            }
//        }
//        for (int i = nbClients - 1; i >= 0; i--) {
//            if (nouvClient.placerApres(listeClients[i]) == false) {
//                listeClients[i + 1] = listeClients[i];
//                if (i == 0) {
//                    listeClients[i] = nouvClient;
//                    nbClients += 1;
//                    break;
//                }
//            }
//            if (nouvClient.placerApres(listeClients[i]) == true) {
//                listeClients[i + 1] = nouvClient;
//                nbClients += 1;
//                break;
//            }
//        }
//    }
}
