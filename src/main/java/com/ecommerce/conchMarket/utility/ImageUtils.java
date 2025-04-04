package com.ecommerce.conchMarket.utility;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.imageio.ImageIO;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtils {


	 public static byte[] compressJPGImage(byte[] imageData) throws IOException {
	        // Read the image from byte array
	        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));

	        // Create a ByteArrayOutputStream to store the compressed image
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	        // Create an ImageOutputStream for the output stream
	        ImageOutputStream ios = ImageIO.createImageOutputStream(byteArrayOutputStream);

	        // Set up the JPEG compression parameters with default quality (0.75f)
	        JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
	        jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
	        jpegParams.setCompressionQuality(0.75f);  // Default quality set to 75%

	        // Write the image to the output stream with the specified compression quality
	        ImageIO.write(image, "jpg", ios);
	        System.out.println("------------------------------------------------------------"+byteArrayOutputStream.toByteArray().length);
	        // Return the compressed image as a byte array
	        return byteArrayOutputStream.toByteArray();
	    }



    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }

}