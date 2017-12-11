package com.infotech;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	public static String candidate;

	public static boolean validLength() {
		return candidate.length() <= 20 && candidate.length() > 1;
	}
	
	public static boolean validAlphaNumRatio() {
		int alnum = 0;
		int len = candidate.length();
		
		for(char ch : candidate.toCharArray()) {
			if(Character.isLetterOrDigit(ch)) {
				alnum++;
			}
		}
		
		if(len - alnum > 1 || alnum == 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public static boolean has3ConsecutiveIdenticalChars() {
		Pattern pat = Pattern.compile("(.)\\1{2,}");
		Matcher mat = pat.matcher(candidate);
		return mat.find();
	}
	
	public static boolean validVowelToConsonantRatio() {
		
		int vowel = 0;
		int consonant = 0;
		for(char ch : candidate.toCharArray()) {
			if(ch == 'a' || ch == 'A'
					|| ch == 'e' || ch == 'E'
					|| ch == 'i' || ch == 'I'
					|| ch == 'o' || ch == 'O'
					|| ch == 'u' || ch == 'U') {
				vowel++;
			} else {
				consonant++;
			}	
		}
		
		if(vowel == 0 || consonant == 0 && candidate.length() > 1)
			return false;
		if(candidate.length() == 1) return true;
		return (vowel*100/consonant >= 10 || consonant*100/vowel >=10);
	}
	
	public static boolean isAlphaOnly() {
		Pattern pat = Pattern.compile("^[a-zA-Z]+$");
		Matcher mat = pat.matcher(candidate);
		return mat.matches();
	}
	
	public static boolean hasMoreThan1PunctuationInMiddle() {
		String newCandidate = candidate.substring(1,candidate.length()-1);
		Pattern pat = Pattern.compile("\\p{Punct}");
		Matcher mat = pat.matcher(newCandidate);
		int countPuncts = 0;
		while(mat.find()) {
			countPuncts++;
		}
		
		return countPuncts >=2;
	}
	
	public static boolean hasInvalidCapitalInMiddle() {
		Pattern pat1 = Pattern.compile("^[a-z].*[A-Z].*[a-z]$");
		Pattern pat2 = Pattern.compile("\\.[A-Z]");
		Matcher mat1 = pat1.matcher(candidate);
		Matcher mat2 = pat2.matcher(candidate);
		
		return mat1.matches() && !mat2.find();
	}
	
	public static boolean singleLetterPunctuation() {
		Pattern pat = Pattern.compile("\\p{Punct}");
		Matcher mat = pat.matcher(candidate);
		if(candidate.length() == 1 && 
				mat.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
public class RegexUtils {

}
