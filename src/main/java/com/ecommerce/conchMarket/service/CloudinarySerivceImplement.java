package com.ecommerce.conchMarket.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinarySerivceImplement implements CloudinaryImageSer{

	@Autowired 
	private Cloudinary cloudinary;
	
	@Override
	public Map upload(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			Map data=this.cloudinary.uploader().upload(file.getBytes(),Map.of());
		    return data;
		}
		catch(IOException e){
			throw new RuntimeException("Image uploading fail!!");
		}
	
	}

	@Override
	public Map<?, ?> delete(String publicId) {
		 try {
	            // Delete the image from Cloudinary using its public ID
			 	System.out.println("public id from cloud service-"+publicId);
	            Map<?, ?> deleteResult = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
	            return deleteResult;
	        } catch (IOException e) {
	            throw new RuntimeException("Image deletion failed: " + e.getMessage());
	        }
	}

	 @Override
	    public Map<?, ?> update(String publicId, MultipartFile newFile) {
	        try {
	            // First, delete the old image
	            delete(publicId);

	            // Then, upload the new image
	            Map<?, ?> uploadResult = cloudinary.uploader().upload(newFile.getBytes(), ObjectUtils.emptyMap());
	            return uploadResult;
	        } catch (IOException e) {
	            throw new RuntimeException("Image update failed: " + e.getMessage());
	        }
	    }
	 
	 @Override
	 public Map<?, ?> getImageDetails(String publicId) {
	     try {
	         // Retrieve image details using the explicit method
	         return cloudinary.api().resource(publicId, ObjectUtils.emptyMap());
	     } catch (Exception e) {
	         throw new RuntimeException("Failed to retrieve image details: " + e.getMessage());
	     }
	 }

	
}
