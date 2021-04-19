package fr.eni.tpmod1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class maximo_1 {

	public static void main(String[] args) {
		/*
		 *	L'objectif est de créer un jeu de lettres : le joueur voit un tirage de lettres et doit trouver un
			mot le plus long possible en utilisant ces lettres. Voici comment se déroule l algorithme du
			programme :
			|X| Un mot est tiré au sort dans le dictionnaire
			|X| Les lettres le constituant sont mélangées aléatoirement
			|O| Le tirage est affiché au joueur
			|O| Le joueur saisit sa proposition
			|O| La proposition est vérifiée :
			--> uniquement les lettres tirées ont été utilisées
			--> mot présent dans le dictionnaire
			|O| Le mot tiré au sort est dévoilé au joueur.*/
		
		String tirage = tirageDuMot();
		String motMelange = motMelange(tirage);
		affichageTirage(motMelange);
		
		Scanner s = new Scanner(System.in);
		String saisie = s.nextLine();
		
		controleSaisie(tirage, saisie);

	};
	

	
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
		
		try (FileInputStream dico = new FileInputStream("D:/workspace/Module_1_TP/src/dictionnaire.txt"); 
				Scanner s = new Scanner(dico)) {
			while (s.hasNextLine())
				for (int i = 0; i < dicoTab.length; i++) {
					dicoTab[i] = s.nextLine();
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
				+ "%s :%n"
				+ "Votre proposition : ", motMelange(mot));
	}
	
	/**
	 * Fonction de comaparaison entre la saisie du joueur
	 * et le mot du tirage.
	 * @param mot
	 * @param saisie
	 */
	private static void controleSaisie(String mot, String saisie) {
		if (mot.equals(saisie)) {
			System.out.printf("%s est une bonne réponse !", saisie);
		} else {
			System.out.printf("%s est une mauvaise réponse. Le mot était : %s", saisie, mot);
		}
	}
}
