package fr.eni.tpmod1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class maximo_1 {
	static final Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
		/*
		 *	L'objectif est de créer un jeu de lettres : le joueur voit un tirage de lettres et doit trouver un
			mot le plus long possible en utilisant ces lettres. Voici comment se déroule l algorithme du
			programme :
			|X| Un mot est tiré au sort dans le dictionnaire
			|X| Les lettres le constituant sont mélangées aléatoirement
			|X| Le tirage est affiché au joueur
			|X| Le joueur saisit sa proposition
			|X| La proposition est vérifiée :
			--> uniquement les lettres tirées ont été utilisées
			|X| Le mot tiré au sort est dévoilé au joueur.*/
		
		menu();
		jeu();
	}
	
	/**
	 * Fonction qui retourne le mot tiré au sort.
	 * Récupération de la liste de mots dans un tableau.
	 * Puis récupération d'un mot random du tableau.
	 * @return
	 */
	private static String tirageDuMot() {
		String motRandom = "";
		String[] dicoTab = new String[22506];
		int randomTab = (int) (Math.random() * 22506 + 1);
		
		try (FileInputStream dico = new FileInputStream("D:/workspace/Module_1_TP/src/fr/eni/tpmod1/dictionnaire.txt"); 
				Scanner s = new Scanner(dico)) {
			while (s.hasNextLine())
				for (int i = 0; i < dicoTab.length; i++) {
					dicoTab[i] = s.nextLine().toLowerCase();
				}
		} catch (IOException e) {
			System.out.println("Raté");
		}
		motRandom = dicoTab[randomTab];
		return motRandom;
	}

	/**
	 * Fonction qui récupère le mot tiré en random du dico
	 * pour en mélanger les lettres.
	 * @param mot
	 * @return
	 */
	private static String motMelange(String mot) {
		List<String> lettres = Arrays.asList(mot.split(""));
		Collections.shuffle(lettres);
		String test = "";
		
		for (String lettre : lettres) {
			test += lettre;
		}
		return test;
	}
	
	/**
	 * Affichage du tirage.
	 */
	private static void affichageTirage(String mot) {
		System.out.printf("voici le résultat du tirage%n"
				+ "%s %n", motMelange(mot));
	}
	
	/**
	 * Fonction de comaparaison entre la saisie du joueur
	 * et le mot du tirage.
	 * @param mot
	 * @param saisie
	 */
	private static void controleSaisie(String tirage, String saisie) {
		char lettre1 = 0;
		char lettre2 = 0;
		boolean test = false;
		
		do {
			
			while (test == false) {
				String ml = "";
				test = true;
				System.out.println("----------------------------------------\n"
						+ "Votre proposition : ");
				saisie = s.nextLine().toLowerCase();
				
				while (saisie.length() != tirage.length()) {
					System.out.printf("Le nombre de lettres ne correspond pas au tirage (%d).%n"
							+ "Réessayez : ", tirage.length());
					saisie = s.nextLine();
				}
				int i = 0;
				//List<String> mc = Arrays.asList(null)
				while (i < saisie.length()) {
					int j = 0;
					lettre1 = saisie.charAt(i);
					i++;
					while (lettre1 != lettre2 && j != tirage.length()) {
						lettre2 = tirage.charAt(j);
						j++;
						
						if (lettre1 != lettre2 && j == tirage.length()) {
							//System.out.println(lettre1 + " n'est pas dans le mot.");
							ml += lettre1;
							test = false;
						}
					}
				}
				if (ml != "") {
					System.out.printf("La/les lettre(s) '%s' ne sont pas dans le mot.%n", ml);
				}
			}
			
			if (tirage.equals(saisie)) {
				System.out.printf("Bravo : '%s' est une bonne réponse !%n"
						+ "----------------------------------------%n", saisie);
			} else {
				System.out.printf("'%s' est une mauvaise réponse.%n", saisie);
				System.out.print("Voulez-vous réessayer ? ('o' = oui | 'n' = non) : ");
				saisie = s.nextLine().toLowerCase();
				switch (saisie) {
				case "o":
					test = false;
					break;
				case "n":
					System.out.printf("La réponse était : %s%n"
							+ "----------------------------------------%n", tirage);
					test = true;
					break;
				default:
					System.out.println("Saisie incorrecte.");
					break;
				}
			}
		} while (test == false);
	}
	
	/**
	 * Corps du jeu avec saisie et appel des fonctions et procédures.
	 */
	private static void tirage() {
		String tirage = tirageDuMot();
		String motMelange = motMelange(tirage);
		String saisie = "";
		affichageTirage(motMelange);
		controleSaisie(tirage, saisie);
	}
	
	/**
	 * Affichage du menu du jeu.
	 */
	private static void menu() {
		//Scanner s =new Scanner(System.in);
		System.out.println("----------------------------------------\n"
						 + "----------------Bienvenu----------------\n"
						 + "----------------------------------------");
		System.out.println("Appuyez sur une touche pour commencer");
		s.nextLine();
	}
	
	/**
	 * Lancement du programme avec possibilité de rejouer.
	 */
	private static void jeu() {
		boolean recommencer = true;
		
		do {
			tirage();
			System.out.println("Voulez-vous recommencer avec un nouveau mot ? ('o' = oui | 'n' = non) : ");
			String choix = s.nextLine().toLowerCase();
			switch (choix) {
			case "o":
				recommencer = false;
				break;
			case "n":
				System.out.println("A bientôt !");
				recommencer = true;
				break;
			default:
				recommencer = true;
				break;
			}
		} while (recommencer == false);
		s.close();
	}
}
