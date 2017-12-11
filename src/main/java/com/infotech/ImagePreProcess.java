package com.infotech;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImagePreProcess {
	private String srcFilePath;
	private String dstFilePath;
	
	public String getDstFilePath() {
		return dstFilePath;
	}

	public void setDstFilePath(String dstFilePath) {
		this.dstFilePath = dstFilePath;
	}

	public String getSrcFilePath() {
		return srcFilePath;
	}

	public void setSrcFilePath(String srcFilePath) {
		this.srcFilePath = srcFilePath;
	}
	
	public void processImage(int index, String srcFolder) {
		try {
			System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	        File input = new File(getSrcFilePath());
	        BufferedImage image = ImageIO.read(input);	
	        
	        Mat mat = ImagePreProcessUtils.convertImgToMat(image, CvType.CV_8UC3);
	        mat = ImagePreProcessUtils.convertImgToGrayscale(mat, image);
	        mat = ImagePreProcessUtils.resizeMat(mat, 2000, 2000, CvType.CV_8UC1);
	        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(1, 1));
	        Imgproc.erode(mat, mat, element);
	        mat = ImagePreProcessUtils.applyAdaptiveThreshold(mat, CvType.CV_8UC1);
	 
	        ImagePreProcessUtils.applyBlur(mat);
	        
	        byte[] data = ImagePreProcessUtils.getByteArrayFromMat(mat);
	        BufferedImage imageOut = ImagePreProcessUtils.getImgFromMatData(mat, data, BufferedImage.TYPE_BYTE_BINARY);
	        ImagePreProcessUtils.writeImg(srcFolder + "/pan_post" + index + ".png", imageOut, "png");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

public class ImagePreProcess {

}
