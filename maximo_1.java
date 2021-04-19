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
		
		String mot = tirageDuMot();
		System.out.println(mot);
		System.out.println(motMelange(mot));

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
	public static String motMelange(String mot) {
		List<String> lettres = Arrays.asList(mot.split(""));
		Collections.shuffle(lettres);
		String test = "";
		
		for (String lettre : lettres) {
			test += lettre;
		}
		return test;
	}
	
}
