/* projet centre de recyclage
 * Camille Orjas
 * Garance Dubois
 * 26 janvier 2025
 */
package ptraitement;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author camil
 */
public class Projet_recyclage {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Plateforme plateforme = new Plateforme();
        plateforme.chargerClients();
        plateforme.chargerCentres();
        plateforme.chargerDemandes();
        plateforme.chargerDechets();
        plateforme.MAJ();
        int reponse;
        Scanner sc = new Scanner(System.in);
        Utilisateur uti = null;
        do {
            System.out.println("Voulez-vous vous connecter (1) ou creer un compte (2) ?");
            reponse = sc.nextInt();
            switch (reponse) {
                case 1 -> {
                    uti = plateforme.connexion();
                }
                case 2 -> {
                    System.out.println("Etes vous un centre de tri (1) ou un client (2)?");
                    int choix = sc.nextInt();
                    switch (choix) {
                        case 1 -> {
                            uti = plateforme.inscriptionCentre();
                        }
                        case 2 -> {
                            uti = plateforme.inscriptionClient();
                        }
                    }
                }
            }
        } while (uti == null);
        do {
            if (uti instanceof CentreTri centreTri) {
                System.out.println("""
                                   Voulez-vous:
                                   1.Consulter la liste des demandes de collecte
                                   2.Consulter la liste des collectes que vous devez effectuer
                                   3.Consulter votre historique de dechets recycles
                                   4.Modifier les informations de votre compte
                                   0.Quitter""");
                reponse = sc.nextInt();
                switch (reponse) {
                    case 1 -> {
                        plateforme.fixerDateCollecte(centreTri);
                    }
                    case 2 -> {
                        plateforme.consulterListeCollecte(centreTri);
                    }
                    case 3 -> {

                    }
                    case 4 -> {
                        plateforme.modifier(centreTri);
                    }
                    case 0 -> {
                        break;
                    }
                }
            }
            if (uti instanceof Entreprise entreprise) {
                System.out.println("""
                                   Voulez-vous:
                                   1.Faire une demande de collecte de dechets
                                   2.Consulter la liste des collectes a venir
                                   3.Consulter votre historique de dechets recycles
                                   4.Modifier les informations de votre compte
                                   0.Quitter""");
                reponse = sc.nextInt();
                switch (reponse) {
                    case 1 -> {
                        plateforme.demandeCollecte(entreprise);
                    }
                    case 2 -> {
                        plateforme.consulterListeCollectes(entreprise);
                    }
                    case 3 -> {

                    }
                    case 4 -> {
                        plateforme.modifier(entreprise);
                    }
                    case 0 -> {
                        break;
                    }
                }
            }
            if (uti instanceof Particulier particulier) {
                System.out.println("""
                                   Voulez-vous:
                                   1.Trouver un centre de recyclage
                                   2.ajouter un depot que vous avez effectue
                                   3.Consulter votre historique de dechets recycles
                                   4.Modifier les informations de votre compte
                                   0.Quitter""");
                reponse = sc.nextInt();
                switch (reponse) {
                    case 1 -> {
                        plateforme.rechercherCentre();
                    }
                    case 2 -> {
                        plateforme.declarerdepot(particulier);
                    }
                    case 3 -> {

                    }
                    case 4 -> {
                        plateforme.modifier(particulier);
                    }
                    case 0 -> {
                        break;
                    }
                }
            }
        } while (reponse != 0);
        plateforme.sauvegarderCentres();
        plateforme.sauvegarderClients();
        plateforme.sauvegarderDemandes();
        plateforme.sauvegarderDechets();
    }
}
