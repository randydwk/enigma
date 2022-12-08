public class Enigma extends Program {

    // CONSTANTES DE LA MACHINE
    String ALPHABET ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String ROTOR1 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
    String ROTOR2 = "AJDKSIRUXBLHWTMCQGZNPYFVOE"; 
    String ROTOR3 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
    String ROTOR4 = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
    String ROTOR5 = "VZBRGITYUPSDNHLXAWMJQOFECK";
    String REFLECTEURA = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
    String REFLECTEURB = "RDOBJNTKVEHMLFCWZAXGYIPSUQ";

    // ----------------------------------------------------------------------------------------
    // Fonction qui retourne un entier correspondant à la position d'une lettre donnée dans l'alphabet (A=0, B=1, C=2, ...)
    // Exemples :
    // entrée : 'A' -> sortie 0
    // entrée : 'B' -> sortie 1
    // ...
    // entrée : 'Z' -> sortie 25
    int lettreEnNombre(char lettre){
		if (lettre >= 'A' && lettre <= 'Z'){
			return (int) (lettre - 'A');
		}else{
			println("ERREUR : Le caractère doit être une lettre en majuscule et sans accent."); 
			return 0;
		}
    }
    // ----------------------------------------------------------------------------------------
    // Fonction qui retourne la lettre associée à une position (un entier) dans l'alphabet (0=A, 1=B, 2=C, ...)
    // Exemples :
    // entrée : 0  -> sortie : 'A'
    // entrée : 1  -> sortie : 'B'
    // ...
    // entrée : 25 -> sortie : 'Z'
    char nombreEnLettre(int nombre){
		if (nombre >= 0 && nombre <= 25){
			return (char) (nombre + 'A');
		}else{println("ERREUR : Le nombre doit être compris entre 0 et 25."); return 'a';}
    }
    // ----------------------------------------------------------------------------------------
    // Fonction qui permet de sélectionner un rotor
    // A partir d'un entier (entre 1 et 5) passé en paramètre, cette fonction retourne le rotor correspondant (la chaîne de caractère correspondante) 
    // Exemples :
    // entrée : 1 -> sortie : "EKMFLGDQVZNTOWYHXUSPAIBRCJ"   (ROTOR1)
    // entrée : 4 -> sortie : "ESOVPZJAYQUIRHXLNFTGKDCMWB"   (ROTOR4) 
    // ...
    String choixRotor(int numeroRotor){
		String rotor = "";

		if (numeroRotor == 1){
			rotor = ROTOR1;
		} else if (numeroRotor == 2){
			rotor = ROTOR2;
		} else if (numeroRotor == 3){
			rotor = ROTOR3;
		} else if (numeroRotor == 4){
			rotor = ROTOR4;
		} else if (numeroRotor == 5){
			rotor = ROTOR5;
		} else {
			println("ERREUR : Le nombre doit être compris entre 1 et 5.");
		}

		/*switch (numeroRotor){
			case 1: rotor = ROTOR1; break;
			case 2: rotor = ROTOR2; break;
			case 3: rotor = ROTOR3; break;
			case 4: rotor = ROTOR4; break;
			case 5: rotor = ROTOR5; break;
			default: println("ERREUR : Le nombre doit être compris entre 1 et 5.");
		}*/

		return rotor;
    }
    // ----------------------------------------------------------------------------------------
    // Fonction qui permet à l'utilisateur de sélectionner le réflecteur
    // A partir d'une lettre ('A' ou 'B') passée en paramètre, cette fonction retourne le réflecteur correspondant (la chaîne de caractère)
    // Exemples :
    // entrée : 'A' -> sortie : "YRUHQSLDPXNGOKMIEBFZCWVJAT"    (REFLECTEURA)
    // entrée : 'B' -> sortie : "RDOBJNTKVEHMLFCWZAXGYIPSUQ"    (REFLECTEURB) 
    String choixReflecteur(char lettreReflecteur){
		String res = "";
		if (lettreReflecteur == 'A') {
			res = REFLECTEURA;
		} else if (lettreReflecteur == 'B'){
			res = REFLECTEURB;
		} else { 
			println("ERREUR : Les choix possibles sont A ou B.");
		}
		return res;
    }
    // ----------------------------------------------------------------------------------------
    // Fonction qui permet à l'utilisateur de la machine de brancher les câbles reliant les paires (6) de lettres
    // Cette fonction doit retourner une chaîne de caractères de 6 lettres majuscules saisies au clavier par l'utilisateur (on supposera que ces 6 lettres sont distinctes)
    // Exemple :
    // Si l'utilisateur saisit les 6 paires suivantes : AV puis DE puis HO puis JK puis LS puis XQ, la fonction doit retourner "AVDEHOJKLSXQ"
    String cablageInitial(){
		println("Entrez les 6 paires du cablage initial");
		String pair = "";
		for (int cpt = 0; cpt<6; cpt++) {
			pair = pair + enMajuscule(readString());
		}
		return pair;
    }
    void affichageCablageInitial(){
		println(cablageInitial());
    }
    // ----------------------------------------------------------------------------------------
    // Fonction qui permet de décaler le rotor d'un rang vers la gauche
    // A partir d'une chaîne de caractères passée en paramètre, cette fonction retourne la chaîne de caractères décalée d'un cran vers la gauche, c'est-à-dire que la première lettre est déplacée à la fin de la chaîne.
    // Exemples :
    // entrée : "ABCDEFGHIJKLMNOPQRSTUVWXYZ" -> sortie : "BCDEFGHIJKLMNOPQRSTUVWXYZA"
    // entrée : "IFHUQSMDVHNQOIVHZ" -> sortie : "FHUQSMDVHNQOIVHZI"
    String decalageUnRang(String rotor){
		return substring (rotor, 1, length(rotor)) + charAt (rotor, 0);
    }
    // ----------------------------------------------------------------------------------------
    // Fonction qui retourne le rotor après avoir défini sa position initiale, c'est-à-dire après nb décalages.
    // A partir d'un rotor donné (une chaîne de caractères) et d'un entier nb donné, cette fonction retourne le rotor décalé de nb crans vers la gauche
    // Exemples :
    // entrées : "ABCDEFGHIJKLMNOPQRSTUVWXYZ" et 3 -> sortie : "DEFGHIJKLMNOPQRSTUVWXYZABC"
    // entrées : "IFHUQSMDVHNQOIVHZ" et 5 -> sortie : "SMDVHNQOIVHZIFHUQ"
    String positionInitialeRotor(String rotor, int position){
		return substring (rotor, position, length(rotor)) + substring(rotor,0,position);
    }
    // ----------------------------------------------------------------------------------------
    // Fonction qui recherche une lettre dans une chaîne de caractères
    // A partir d'une lettre donnée et d'une chaîne de caractère donnée, cette fonction retourne l'indice (la position) de la lettre dans la chaîne (-1 si absent)
    // Exemples : 
    // entrées 'C' et "ABCDE" -> sortie 2
    // entrées 'A' et "ABCDE" -> sortie 0
    // entrées 'E' et "ABCDE" -> sortie 4
    // entrées 'F' et "ABCDE" -> sortie -1
    // entrées 'F' et ROTOR1 -> sortie 3
    int indiceLettre(char lettre, String cablage){
		int res = -1;
		for (int i=0; i<length(cablage); i++) {
			if (charAt(cablage,i)==lettre){
				res = i;
			}
		}
		return res;
    }
    // ----------------------------------------------------------------------------------------
    // Fonction qui permet de vérifier si la lettre à décoder est reliée par un câble à une autre lettre. Si oui, elle est transformée en cette lettre, sinon elle reste identique.
    // A partir d'une lettre donnée et d'un cablâge donné (une chaîne de 12 caractères), cette fonction retourne la lettre transformée si elle fait partie d'une paire de lettres d'un des 6 câbles, la même lettre sinon.
    // Exemples : 
    // entrées : 'H' et "AVDEHOJKLSXQ" -> sortie 'O'    (car 3ème paire HO)
    // entrées : 'A' et "ABCDEFGHIJKL" -> sortie 'B'    (car 1ère paire AB)
    // entrées : 'B' et "ABCDEFGHIJKL" -> sortie 'A'    (car 1ère paire AB)
    // entrées : 'K' et "ABCDEFGHIJKL" -> sortie 'L'    (car 6ème paire KL)
    // entrées : 'D' et "ABCDEFGHIJKL" -> sortie 'C'    (car 2ème paire CD)
    // entrées : 'M' et "ABCDEFGHIJKL" -> sortie 'M'    (car M absent de la chaîne du cablâge)
    char valeurApresCablageDeDepart(char lettre, String cablage){
		int indice = indiceLettre(lettre,cablage);
		char res = 'a';
		if (indice!=-1){
			if (indice%2==0){
				res = charAt(cablage,indice+1);
			}else{
				res = charAt(cablage,indice-1);
			}
		}else{
			res = lettre;
		}
		return res;
    }
    // ----------------------------------------------------------------------------------------
    // Fonction qui retourne la nouvelle valeur après le passage dans un rotor
    // A partir d'une lettre donnée et d'un rotor donné (une chaîne de caractères), cette fonction retourne la lettre correspondante à la lettre passée en paramètre après passage dans le rotor donné.
    // Exemples :
    // entrées : 'A' et ROTOR1 -> sortie : 'E'
    // entrées : 'B' et ROTOR1 -> sortie : 'K'
    // entrées : 'Z' et ROTOR1 -> sortie : 'J'   
    // entrées : 'E' et "AJDKSIRUXBLHWTMCQGZNPYFVOE" -> sortie : S
    char passageDansUnRotor(char lettre, String rotor){
		int i = lettre - 'A';
		return charAt (rotor, i);
    }
    // ----------------------------------------------------------------------------------------
    // Fonction qui retourne la nouvelle valeur après le passage dans le réflecteur
    // A partir d'une lettre donnée et d'un réflecteur donné (une chaîne de caractères), cette fonction retourne la lettre correspondante à la lettre passée en paramètre après passage dans le réflecteur.
    // Exemples :
    // entrées : 'A' et REFLECTEURA -> sortie : 'Y'
    // entrées : 'B' et REFLECTEURA -> sortie : 'R'
    // entrées : 'Z' et "YRUHQSLDPXNGOKMIEBFZCWVJAT" -> sortie : 'T'   
    char passageDansLeReflecteur(char lettre, String reflecteur){
		int i = lettre - 'A';
		return charAt (reflecteur, i);
    }
    // ----------------------------------------------------------------------------------------
    // Fonction qui retourne la nouvelle valeur après le passage dans un rotor dans le sens inverse (pour le retour)
    // A partir d'une lettre donnée et d'un rotor donné (une chaîne de caractères), cette fonction retourne la lettre correspondante à la lettre passée en paramètre après passage en sens inverse dans le rotor.
    // Exemples :
    // entrées : 'E' et ROTOR1 -> sortie : 'A'
    // entrées : 'K' et ROTOR1 -> sortie : 'B'
    // entrées : 'J' et ROTOR1 -> sortie : 'Z'   
    // entrées : 'S' et "AJDKSIRUXBLHWTMCQGZNPYFVOE" -> sortie : E
    char inverseRotor(char lettre, String rotor){
		int i = indiceLettre(lettre, rotor);
		return (char) ('A' + i);
    }
    // ----------------------------------------------------------------------------------------
    // Fonction qui transforme une chaîne de caractères en majuscule
    String enMajuscule(String message){
		String res = "";
		char c;
		for (int i = 0; i < length(message); i++)
		{
			c = charAt(message,i);
			if (c < 'A' || c > 'Z'){
				res = res + (char) (c - 32);
			} else {
				res = res + c;
			}
		}
		return res;
    }
    // ----------------------------------------------------------------------------------------
	// PROGRAMME PRINCIPAL

    int rotor1,rotor2,rotor3;
	String R1,R2,R3;
	char position1,position2,position3;
	int decalage1,decalage2,decalage3;
	char choixRef;
	String refl;
	String cables;
	
	String message="";

	void algorithm(){
	println(" ------------------------------------\n| Simulation d'une machine Enigma M3 |\n ------------------------------------");

	// Initialisation des éléments
	// ---------------------------
	println("Quel type de configuration souhaitez-vous ? \n 1 : Configuration par défaut (Rotor 1 : III en position W, Rotor 2 : I en position D, Rotor 3 : V en position E, Réflecteur : B, Cablâge : AV - DE - HO - JK - LS - XQ, Message à décoder par défaut)\n 2 : Configuration personnalisée\n 3 : Tester des fonctions");
	int choix = readInt();
	while (choix<1 || choix>3){
		println("Erreur de saisie, choix : 1, 2 ou 3");
		choix = readInt();
	}

	if (choix == 1){// Configuration pré-établie
		// // Rotors choisis
		rotor1 = 3;
		rotor2 = 1;
		rotor3 = 5;
		R1=choixRotor(rotor1);
		R2=choixRotor(rotor2);
		R3=choixRotor(rotor3);
		
		// // Position initiale des rotors choisis
		position1 = 'W';
		position2 = 'D';
		position3 = 'E';
		decalage1 = indiceLettre(position1,R1);
		R1 = positionInitialeRotor(R1,decalage1);
		decalage2 = indiceLettre(position2,R2);
		R2 = positionInitialeRotor(R2,decalage2);
		decalage3 = indiceLettre(position3,R3);
		R3 = positionInitialeRotor(R3,decalage3);
		
		// // Réflecteur choisi
		choixRef = 'B';
		refl = choixReflecteur(choixRef);
		
		// // Initialisation de la configuration du cablage de la machine par l'utilisateur
		cables = "AVDEHOJKLSXQ";
		
		// // Message à tester 
		message = "AKBAOKETGPVYHGWBSGSVUDTZEBNOXGFOBVYOJVTWFPIKC";
	}
	else if (choix == 2){// Configuration choisie par l'utilisateur
		// // Choix des 3 rotors (distincts) parmi les 5
		// NB : On supposera que les numéros des 3 rotors sont bien compris entre 1 et 5 et qu'ils sont tous différents
		println("Entrez le numéro du premier rotor choisi (1, 2, 3, 4, 5)");
		rotor1 = readInt();
		println("Entrez le numéro du deuxième rotor choisi");
		rotor2 = readInt();
		println("Entrez le numéro du troisième rotor choisi");
		rotor3 = readInt();
		
		R1=choixRotor(rotor1);
		R2=choixRotor(rotor2);
		R3=choixRotor(rotor3);
		
		// // Choix de la position initiale des rotors choisis
		println("Entrez la position initiale du premier rotor choisi (A à Z)");
		position1 = readChar();
		println("Entrez la position initiale du deuxième rotor choisi (A à Z)");
		position2 = readChar();
		println("Entrez la position initiale du troisième rotor choisi (A à Z)");
		position3 = readChar();
		
		decalage1 = indiceLettre(position1,R1);
		R1 = positionInitialeRotor(R1,decalage1);
		decalage2 = indiceLettre(position2,R2);
		R2 = positionInitialeRotor(R2,decalage2);
		decalage3 = indiceLettre(position3,R3);
		R3 = positionInitialeRotor(R3,decalage3);
		
		// // Choix du réflecteur parmi les 2
		println("Entrez la lettre du réflecteur choisi (A ou B)");
		choixRef = readChar();
		refl = choixReflecteur(choixRef);
		
		// // Initialisation de la configuration du cablage de la machine par l'utilisateur
		cables = cablageInitial();
		
		// // Le message à coder
		println("Entrez le message à coder :");
		message = readString();
		message = enMajuscule(message);
	}
	// -----------------------------------------------------------------------------
	// -------------------- AJOUT DU MENU DE TEST DES FONCTIONS --------------------
	// -----------------------------------------------------------------------------
	else{// Tests de fonctions
		// // Choix de fonctions et executions dans une boucle
		println(" --------------------\n| Tests de fonctions |\n --------------------");
		
		String fonc = "";
		println("Écrivez une fonction pour la tester. Entrez \"stop\" pour arrêter les tests, \"list\" pour obtenir la liste des fonctions à tester.");
		do {
			print("Fonction à tester : ");
			fonc = readString();
			if (equals(fonc,"lettreEnNombre")){									// lettreEnNombre
				print("Entrez un caractère : ");
				println("La fonction retourne : "+lettreEnNombre(readChar()));
			}else if (equals(fonc,"nombreEnLettre")){							// nombreEnLettre
				print("Entrez un entier : ");
				println("La fonction retourne : "+nombreEnLettre(readInt()));
			}else if (equals(fonc,"choixRotor")){								// choixRotor
				print("Entrez un entier : ");
				println("La fonction retourne : "+choixRotor(readInt()));
			}else if (equals(fonc,"choixReflecteur")){							// choixReflecteur
				print("Entrez un caractère : ");
				println("La fonction retourne : "+choixReflecteur(readChar()));
			}else if (equals(fonc,"cablageInitial")){							// cablageInitial
				println("La fonction retourne : "+cablageInitial());
			}else if (equals(fonc,"decalageUnRang")){							// decalageUnRang
				print("Entrez une chaine de caractères : ");
				println("La fonction retourne : "+decalageUnRang(readString()));
			}else if (equals(fonc,"positionInitialeRotor")){					// positionInitialeRotor
				print("Entrez une chaine de caractères : ");
				String temp = readString();
				print("Entrez un entier : ");
				println("La fonction retourne : "+positionInitialeRotor(temp,readInt()));
			}else if (equals(fonc,"indiceLettre")){								// indiceLettre
				print("Entrez un caractère : ");
				char temp = readChar();
				print("Entrez une chaîne de caractères : ");
				println("La fonction retourne : "+indiceLettre(temp,readString()));
			}else if (equals(fonc,"valeurApresCablageDeDepart")){				// valeurApresCablageDeDepart
				print("Entrez un caractère : ");
				char temp = readChar();
				print("Entrez une chaîne de caractères : ");
				println("La fonction retourne : "+valeurApresCablageDeDepart(temp,readString()));
			}else if (equals(fonc,"passageDansUnRotor")){						// passageDansUnRotor
				print("Entrez un caractère : ");
				char temp = readChar();
				print("Entrez une chaîne de caractères : ");
				println("La fonction retourne : "+passageDansUnRotor(temp,readString()));
			}else if (equals(fonc,"passageDansLeReflecteur")){					// passageDansLeReflecteur
				print("Entrez un caractère : ");
				char temp = readChar();
				print("Entrez une chaîne de caractères : ");
				println("La fonction retourne : "+passageDansLeReflecteur(temp,readString()));
			}else if (equals(fonc,"inverseRotor")){								// inverseRotor
				print("Entrez un caractère : ");
				char temp = readChar();
				print("Entrez une chaîne de caractères : ");
				println("La fonction retourne : "+inverseRotor(temp,readString()));
			}else if (equals(fonc,"enMajuscule")){								// enMajuscule
				print("Entrez une chaine de caractères : ");
				println("La fonction retourne : "+enMajuscule(readString()));
			}else if (equals(fonc,"list")){
				println("- lettreEnNombre \n- nombreEnLettre \n- choixRotor \n- choixReflecteur \n- cablageInitial \n- decalageUnRang \n- positionInitialeRotor \n- indiceLettre \n- valeurApresCablageDeDepart \n- passageDansUnRotor \n- passageDansLeReflecteur \n- inverseRotor \n- enMajuscule");
			}else if (!equals(fonc,"stop")){
				println("Fonction inconnue.");
			}
		} while (!equals(fonc,"stop"));
	}
	if (choix != 0 && choix != 3){// Décrypte le message seulement si on ne fait pas des tests de fonctions
		String messageDecode="";
		char lettre;
		int cpt1 = 0;
		int cpt2 = 0;
			// Boucle principale du programme Enigma
			// -------------------------------------
			for (int tour=0 ; tour < length(message) ; tour=tour+1){// la boucle s'arrête quand on a codé chaque lettre du message
				// 1. Récupération de la lettre courante dans le message à décoder
				lettre = charAt(message,tour);
				// 2. Passage par le câblage
				lettre = valeurApresCablageDeDepart(lettre, cables);
				// 3. Passage par les 3 rotors (premier, deuxième, troisième)
				lettre = passageDansUnRotor(lettre,R1);
				lettre = passageDansUnRotor(lettre,R2);
				lettre = passageDansUnRotor(lettre,R3);
				// 4. Passage par le réflecteur
				lettre = passageDansLeReflecteur(lettre,refl);
				// 5. Passage par les 3 rotors (dans le sens inverse : troisième, deuxième, premier)
				lettre = inverseRotor(lettre,R3);
				lettre = inverseRotor(lettre,R2);
				lettre = inverseRotor(lettre,R1);
				// 6. Passage par le cablâge
				lettre = valeurApresCablageDeDepart(lettre, cables);
				
				// 7. Ajout de la lettre décodée au message
				messageDecode = messageDecode + lettre;
				
				// 8. Préparation de l'itération suivante : décalage du premier rotor (à chaque fois); décalage du deuxième rotor si le premier a fait un tour complet (après 26 itérations) ; décalage du troisième rotor si le deuxième a fait un tour complet (après 26*26 itérations) 
				// 8.1 Le rotor 1 tourne d'un rang vers la gauche après chaque lettre (donc à chaque tour)
				R1 = decalageUnRang(R1);
				cpt1 += 1;
				// 8.2 Si le rotor 1 a effectué un tour (toutes les 26 itérations) alors le rotor 2 tourne d'un cran vers la gauche
				if (cpt1 == 26){
					R2 = decalageUnRang(R2);
					cpt1 = 0;
					cpt2 += 1;
				}
				// 8.3 Si le rotor 2 a effectué un tour (toutes les 26*26 itérations) alors le rotor 3 tourne d'un cran vers la gauche 
				if (cpt2 == 26){
					R3 = decalageUnRang(R3);
					cpt2 = 0;
				}
			}
			println("Le message décodé est : \n" + messageDecode);
		}
	}
}