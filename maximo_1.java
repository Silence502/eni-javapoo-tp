package fr.eni.tpmod1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class maximo_1 {

	public static void main(String[] args) {
		/*
		 *	L'objectif est de créer un jeu de lettres : le joueur voit un tirage de lettres et doit trouver un
			mot le plus long possible en utilisant ces lettres. Voici comment se déroule l algorithme du
			programme :
			- Un mot est tiré au sort dans le dictionnaire
			- Les lettres le constituant sont mélangées aléatoirement
			- Le tirage est affiché au joueur
			- Le joueur saisi t sa proposition
			- La proposition est vérifiée :
			--> uniquement les lettres tirées ont été utilisées
			--> mot présent dans le dictionnaire
			- Le mot tiré au sort est dévoilé au joueur.*/
		System.out.println(tirageDuMot());
	}
	

	@SuppressWarnings("unused")
	/**
	 * Fonction qui va retourner le mot tiré au sort.
	 * Récupération de la liste de mots dans un tableau.*/
	private static String tirageDuMot() {
		String mot = "";
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
		
		mot = dicoTab[randomTab];
		
		return mot;
	}
	
}
