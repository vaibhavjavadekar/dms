package com.infotech;

public class RemoveGarbage {
	private String ocrOutput = null;
	private StringBuilder filteredOutput = new StringBuilder();
	
	public String getOcrOutput() {
		return ocrOutput;
	}
	
	public void setOcrOutput(String ocrOutput) {
		this.ocrOutput = ocrOutput;
	}
	
	public String getFilteredOutput() {
		return filteredOutput.toString();
	}
	
	public void setFilteredOutput(StringBuilder filteredOutput) {
		this.filteredOutput = filteredOutput;
	}
	
	public void removeGarbage(String ocrOutput) {
		setOcrOutput(ocrOutput);
		String[] tokens = ocrOutput.split("\\s+");
		for(String token : tokens) {
			if((token.length() != 0) && (isGarbageWord(token) == false)) {
				filteredOutput.append(token);
				filteredOutput.append(" ");
			}
		}
	}
	
	/*
	 * Applies RegexUtils on every word to check if it is not
	 * garbage as defined by garbage rules
	 */
	public boolean isGarbageWord(String candidate) {
		RegexUtils.candidate = candidate;
		
		if(!RegexUtils.validLength())
			return true;
		if(!RegexUtils.validAlphaNumRatio())
			return true;
		if(RegexUtils.has3ConsecutiveIdenticalChars()) 
			return true;
		if(RegexUtils.isAlphaOnly() && !RegexUtils.validVowelToConsonantRatio())
			return true;
		if(candidate.length() >= 2 && RegexUtils.hasMoreThan1PunctuationInMiddle())
			return true;
		if(RegexUtils.hasInvalidCapitalInMiddle()) 
			return true;
		if(RegexUtils.singleLetterPunctuation()) 
			return true;
		return false;
	}
	
}
public class RemoveGarbage {

}
