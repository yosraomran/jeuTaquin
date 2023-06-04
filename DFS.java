

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class DFS {


	public static LinkedList <String> OPEN = new LinkedList <String> ();                  //OPEN
	public static LinkedHashSet<String> CLOSED = new LinkedHashSet<String>();             //CLOSED
	private static Map<String,Integer> PROFONDEUR  = new HashMap<String, Integer>();       //ETAT ET SA PROFONDEUR
	private static Map<String,String> ETATS= new HashMap<String,String>();                 // ETAT ET SON PERE
	private static boolean SOLUTION = false;                                               //BOOLEEN SI SOLUTION EST TROUVE OU PAS
	private static int prf;                                                                //PROFONDEUR
	private static int position;                                                           //POSITION DE LA CASE VIDE [0]
	private static String etatC;					             						   //ETAT COURRENT 
	private static Map<String, List<String>> MOVES = new LinkedHashMap <>();               // ETAT COURRENT ET MOUVEMENT EFFECTUE POUR Y ARRIVER

	
	private static List myArrayListD = new ArrayList<>(); 

		


	

	public static  <T> List<T> depthFirstSearch() {
        String etat_initial ="";
        for(int i=0;i<3;i++)
           for(int j=0;j<3;j++)
             etat_initial=etat_initial+TaquinGame.board[i][j];      
        String etat_but ="123456780";
		 int limit_prf = TaquinUI.val;
		 System.out.println("limit_prf est "+limit_prf);
		
	 
     OPEN.add(etat_initial);          
	 ETATS.put(etat_initial, null);
     calcule_prf(etat_initial,null);
	 MOVES.put(etat_initial, Arrays.asList(null, "-"));

     
	                                                 

    while (!OPEN.isEmpty()){
			etatC = OPEN.removeFirst();
			CLOSED.add(etatC);
			if (etatC.equals(etat_but)){ 
				SOLUTION = true;
				print(etatC);
				break;
			}
			else {
											
				if (PROFONDEUR.get(etatC) < limit_prf){
					position = etatC.indexOf("0");
					CLOSED.add(etatC);
						//Gauche
						while (position != 0 && position != 3 && position != 6){
							String nextState = etatC.substring(0,position-1)+"0"+etatC.charAt(position-1)+etatC.substring(position+1);
							if (!PROFONDEUR.containsKey(nextState)) {
								OPEN.addFirst(nextState);
								ETATS.put(nextState, etatC);
						        calcule_prf(nextState,etatC);
						      	MOVES.put(nextState, Arrays.asList(etatC, "left"));
							}
							break;
						}
						
						//Haut
						while (position!=0 && position!=1 && position!=2 && PROFONDEUR.get(etatC) < limit_prf ){
							String nextState = etatC.substring(0,position-3)+"0"+etatC.substring(position-2,position)+etatC.charAt(position-3)+etatC.substring(position+1);
							if (!PROFONDEUR.containsKey(nextState))  {
								OPEN.push(nextState);
								ETATS.put(nextState, etatC);
								calcule_prf(nextState,etatC);
								MOVES.put(nextState, Arrays.asList(etatC, "up"));	
							}
						break;
						}
						//Droit
						while(position != 2 && position != 5 && position != 8 && PROFONDEUR.get(etatC) < limit_prf ){
							String nextState = etatC.substring(0,position)+etatC.charAt(position+1)+"0"+etatC.substring(position+2);
							if (!PROFONDEUR.containsKey(nextState))  {
								OPEN.push(nextState);
								ETATS.put(nextState, etatC);
								calcule_prf(nextState,etatC);
								MOVES.put(nextState, Arrays.asList(etatC, "right"));				
							}
						break;
						}
						//Bas
						while (position != 6 && position != 7 && position != 8 && PROFONDEUR.get(etatC) < limit_prf ) {
							String nextState = etatC.substring(0,position)+etatC.substring(position+3,position+4)+etatC.substring(position+1,position+3)+"0"+etatC.substring(position+4);
							if (!PROFONDEUR.containsKey(nextState))  {
								OPEN.push(nextState);
								ETATS.put(nextState, etatC);
								calcule_prf(nextState,etatC);
								MOVES.put(nextState, Arrays.asList(etatC, "down"));	
							}
						break;
						}
						}
				
				if ((OPEN.isEmpty() && !SOLUTION) ) {
                    TaquinUI.DisplayMoves.setText("Solution not found!");
				  	  
				    }
			}
			}
    if (OPEN.isEmpty() && !SOLUTION || PROFONDEUR.get(etatC) >= limit_prf) {
  	  TaquinUI.DisplayMoves.setText("Solution not found!");
  	  
    }
	return myArrayListD;}


//FONCTION POUR CALCULER NIVEAU DE PROFONDEUR D'UN ETAT
static void calcule_prf (String etatF, String etatP){
	if(etatP == null) {
		prf = 0; 
	} 
	else {
		prf = PROFONDEUR.get(etatP) +1 ;
	}
		PROFONDEUR.put(etatF, prf);
		}
static void doClear() {
	OPEN.clear();
	CLOSED.clear();
	ETATS.clear();
	MOVES.clear();
	PROFONDEUR.clear();
	myArrayListD.clear();
}

static void print (String etatC){
	String traceState = etatC;
	while (traceState != null) {
		
		myArrayListD.add(MOVES.get(traceState).get(1));

	traceState = ETATS.get(traceState);
	}
	Collections.reverse(myArrayListD);
	myArrayListD.remove(0);
	}
	
	} 