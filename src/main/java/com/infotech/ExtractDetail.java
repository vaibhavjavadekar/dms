package com.infotech;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ExtractDetail {
	private static ArrayList<String> states = null;
	
	public static int Levenshtein(String A, String B) {
		int[][] distance = new int[A.length() + 1][B.length() + 1];        
        
        for (int i = 0; i <= A.length(); i++)                                 
            distance[i][0] = i;                                                  
        for (int j = 1; j <= B.length(); j++)                                 
            distance[0][j] = j;                                                  
                                                                                 
        for (int i = 1; i <= A.length(); i++)                                 
            for (int j = 1; j <= B.length(); j++)                             
                distance[i][j] = minimum(                                        
                        distance[i - 1][j] + 1,                                  
                        distance[i][j - 1] + 1,                                  
                        distance[i - 1][j - 1] + ((A.charAt(i - 1) == B.charAt(j - 1)) ? 0 : 2));
                                                                                 
        return distance[A.length()][B.length()]; 
	                                                                                 
	                 
	}
	
	private static int minimum(int a, int b, int c) {                            
        return Math.min(Math.min(a, b), c);                                      
    }
	
	public static void readStates() {
		states = new ArrayList<>();
		String state;
		try {
			BufferedReader br = new BufferedReader(new FileReader("IndianStates.txt"));
			try {
				while((state = br.readLine()) != null) {
					states.add(state);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int findWordPosition(String word, String ocrOutput) {
		int minval = 1000;
		int pos = -1;
		
		String[] tokens = ocrOutput.split("\\s+");
		for(int i = 0;i < tokens.length;i++) {
			String token = tokens[i];
			int editCost = Levenshtein(token.toLowerCase(), word.toLowerCase());
			if(editCost < minval) {
				minval = editCost;
				pos = i;
			}
		}
		return pos;		
	}
	
	public static String findState(String ocrOutput) {
		int pos = findWordPosition("State", ocrOutput.substring(0, ocrOutput.length()/2));
		String[] tokens = ocrOutput.split("\\s+");
		String candidate = tokens[pos+2] + " " + tokens[pos+3] + " " + tokens[pos+4];
		
		int mindist = 10000;
		String ansState = null;
		for(String state : states) {
			int dist = Levenshtein(state, candidate);
			if(dist < mindist) {
				mindist = dist;
				ansState = state;
			}
		}
		
		return ansState;
	}
	
	public static String findFirmName(String ocrOutput) {
		int pos1 = findWordPosition("certify", ocrOutput.substring(0,ocrOutput.length()/4));
		int pos2 = findWordPosition("whose", ocrOutput.substring(0,ocrOutput.length()/4));
		String[] tokens = ocrOutput.split("\\s+");
		StringBuilder firmName = new StringBuilder();
		for(int i = pos1+1;i < pos2;i++) {
			firmName.append(tokens[i]);
			if(i != pos2-1) {
				firmName.append(" ");
			}
		}
		
		return firmName.toString();
	}
	
	public static String findPrincipalPlaceOfBusiness(String ocrOutput) {
		int pos1 = findWordPosition("situated", ocrOutput.substring(0,ocrOutput.length()/2)) + 1;
		int pos2 = findWordPosition("registered", ocrOutput.substring(0,ocrOutput.length()/2)) - 2;
		String[] tokens = ocrOutput.split("\\s+");
		StringBuilder firmName = new StringBuilder();
		for(int i = pos1+1;i < pos2;i++) {
			firmName.append(tokens[i]);
			if(i != pos2-1) {
				firmName.append(" ");
			}
		}
		
		return firmName.toString();
	}
}

public class ExtractDetail {

}
