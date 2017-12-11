package com.infotech;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TessDemo {
    private static final int NUM_OF_PANS = 5;
    private static final String SRC_IMG = "pans";
    private static final String INTERIM = "interim";
    private static final String OUT_IMG = "out-ocr";
    
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        TessDemo ob = new TessDemo();
        ob.run();
    }
    
    public void run() throws FileNotFoundException, UnsupportedEncodingException {
        for(int i = 1;i <= NUM_OF_PANS;i++) {
	        ImagePreProcess ipp = new ImagePreProcess();
	        ipp.setSrcFilePath(SRC_IMG + "/pan" + i + ".png");
	        ipp.processImage(i, INTERIM);
	        
	        ITesseract instance = new Tesseract();
	        try {
	            String result = instance.doOCR(new File(INTERIM + "/pan_post" + i + ".png"));
	            RemoveGarbage rg = new RemoveGarbage();
	            rg.removeGarbage(result);
	            String outOcr = rg.getFilteredOutput();
	            System.out.println(outOcr);
	            PrintWriter writer = new PrintWriter(OUT_IMG + "/pan_out" + i + ".txt", "UTF-8");
	            writer.println(outOcr);
	            
	            // Logic to identify PAN number
	            String pan = "";
	            String[] tokens = outOcr.split("\\s+");
	            for(String token : tokens) {
	            	if(token.matches("^[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}$")) {
	            		pan = token;
	            	}
	            }
	            writer.println("\nPAN: "+pan);
	            writer.close();
	            
	            /**
	            ExtractDetail.readStates();
	            String state = ExtractDetail.findState(outOcr);
	            System.out.println("State found : " + state);
	            
	            String firmName = ExtractDetail.findFirmName(outOcr);
	            System.out.println("Firm Name: " + firmName);
	            
	            String principalPlaceOfBusiness = ExtractDetail.findPrincipalPlaceOfBusiness(outOcr);
	            System.out.println("Principal Place Of Business: " + principalPlaceOfBusiness);
	            **/
	        } catch (TesseractException e) {
	            System.err.println(e.getMessage());
	        }
        }
    }
}
public class TessDemo {

}
