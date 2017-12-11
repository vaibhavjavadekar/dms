package com.infotech;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

import javax.imageio.ImageIO;

public class ImagePreProcessUtils {
	
	public static Mat convertImgToMat(BufferedImage img, int type) {
		Mat mat = null;
		byte[] data = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        mat = new Mat(img.getHeight(), img.getWidth(), type);
        mat.put(0, 0, data);
		return mat;
	}
	
	public static Mat convertImgToGrayscale(Mat srcMat, BufferedImage srcImg) {
		Mat dstMat = new Mat(srcImg.getHeight(), srcImg.getWidth(), CvType.CV_8UC1);
        Imgproc.cvtColor(srcMat, dstMat, Imgproc.COLOR_RGB2GRAY);
        return dstMat;
	}
	
	public static byte[] getByteArrayFromMat(Mat srcMat) {
		byte[] data = new byte[srcMat.rows() * srcMat.cols() * (int)(srcMat.elemSize())];
        srcMat.get(0, 0, data);
        return data;
	}
	
	public static BufferedImage getImgFromMatData(Mat srcMat, byte[] srcData, int type) {
		BufferedImage img = null;
		img = new BufferedImage(srcMat.cols(), srcMat.rows(), type);
        img.getRaster().setDataElements(0, 0, srcMat.cols(), srcMat.rows(), srcData);
		return img;
	}
	
	public static Mat resizeMat(Mat srcMat, int width, int height, int type) {
		Mat dstMat = new Mat(width, height, type); 
        Size sz = new Size(width, height);
        Imgproc.resize(srcMat, dstMat, sz, 0, 0, Imgproc.INTER_CUBIC);
        return dstMat;
	}
	
	public static Mat applyAdaptiveThreshold(Mat srcMat, int type) {
		Mat dstMat = new Mat(srcMat.width(), srcMat.height(), type);
		Imgproc.adaptiveThreshold(srcMat, dstMat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 41, 24);
		return dstMat;
	}
	
	public static void writeImg(String path, BufferedImage img, String format) {
		try {
			File output = new File(path);
			ImageIO.write(img, format, output);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void applyBlur(Mat srcMat) {
		Imgproc.blur(srcMat, srcMat, new Size(4, 4));
	}
	
}

public class ImagePreProcessUtils {

}
