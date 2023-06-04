import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class BFS {

public static LinkedHashSet <String> OPEN_bfs = new LinkedHashSet <String> ();
public static LinkedHashSet<String> CLOSED_bfs = new LinkedHashSet<String>();
private static LinkedHashMap<String,String> ETATS_bfs = new LinkedHashMap<String,String>();
private static boolean SOLUTION = false;

private static Map<String, List<String>> MOVES_bfs = new LinkedHashMap <>(); 

private static int position;

private static String etatC;

public static List myArrayList = new ArrayList<>(); 


public static  <T> List<T> breadthFirstSearch() {
	String etat_initial ="";
    for(int i=0;i<3;i++)
       for(int j=0;j<3;j++)
         etat_initial=etat_initial+TaquinGame.board[i][j];      
	String etat_but ="123456780";
	System.out.println(etat_initial);

	OPEN_bfs.add(etat_initial);
	ETATS_bfs.put(etat_initial, null);
	MOVES_bfs.put(etat_initial, Arrays.asList(null, "-"));
  
	while (!OPEN_bfs.isEmpty()){
		etatC = OPEN_bfs.iterator().next();
		OPEN_bfs.remove(etatC);
		if (etatC.equals(etat_but)){ 
			SOLUTION = true;
			print(etatC);
			break;
			}

			position = etatC.indexOf("0");
			CLOSED_bfs.add(etatC);
			
			while (position != 0 && position != 3 && position != 6){
				String nextState = etatC.substring(0,position-1)+"0"+etatC.charAt(position-1)+etatC.substring(position+1);
				if (!OPEN_bfs.contains(nextState) && CLOSED_bfs.contains(nextState) == false) {
					OPEN_bfs.add(nextState);
					ETATS_bfs.put(nextState, etatC);	                
					MOVES_bfs.put(nextState, Arrays.asList(etatC, "left"));
				} 
				break;
			}
			
			while (position!=0 && position!=1 && position!=2){
				String nextState = etatC.substring(0,position-3)+"0"+etatC.substring(position-2,position)+etatC.charAt(position-3)+etatC.substring(position+1);
				if (!OPEN_bfs.contains(nextState) && CLOSED_bfs.contains(nextState) == false) {
					OPEN_bfs.add(nextState);
					ETATS_bfs.put(nextState, etatC);
					MOVES_bfs.put(nextState, Arrays.asList(etatC, "up"));
				}
				break;
			}
			
			while(position != 2 && position != 5 && position != 8){
				String nextState = etatC.substring(0,position)+etatC.charAt(position+1)+"0"+etatC.substring(position+2);
				if (!OPEN_bfs.contains(nextState) && CLOSED_bfs.contains(nextState) == false) {
					OPEN_bfs.add(nextState);
					ETATS_bfs.put(nextState, etatC);
					MOVES_bfs.put(nextState, Arrays.asList(etatC, "right"));
				}
				break;
			}
			
			while (position != 6 && position != 7 && position != 8) {
				String nextState = etatC.substring(0,position)+etatC.substring(position+3,position+4)+etatC.substring(position+1,position+3)+"0"+etatC.substring(position+4);
				if (!OPEN_bfs.contains(nextState) && CLOSED_bfs.contains(nextState) == false) {
					OPEN_bfs.add(nextState);
					ETATS_bfs.put(nextState, etatC);
					MOVES_bfs.put(nextState, Arrays.asList(etatC, "down"));
				}
				break;
			}

			}
	return myArrayList;}

static void doClear() {
	System.out.println("i cleared");
	OPEN_bfs.clear();
	CLOSED_bfs.clear();
	ETATS_bfs.clear();
	MOVES_bfs.clear();
	myArrayList.clear();
}

static void print (String currState){
	if (!SOLUTION){		
		System.out.println("SOLUTION not found!");	
		
	}
	String traceState = currState;
	while (traceState != null) {
	
		myArrayList.add(MOVES_bfs.get(traceState).get(1));
	traceState = ETATS_bfs.get(traceState);
	}
	Collections.reverse(myArrayList);
	myArrayList.remove(0);
	}
	
	} 

