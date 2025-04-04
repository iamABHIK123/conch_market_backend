package com.ecommerce.conchMarket.service;

import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryImageSer {

    /**
     * Uploads an image to Cloudinary.
     *
     * @param file The image file to upload.
     * @return A map containing the upload result (e.g., secure_url, public_id).
     */
    Map<?, ?> upload(MultipartFile file);

    /**
     * Uploads an image to Cloudinary with custom options (e.g., public_id).
     *
     * @param file    The image file to upload.
     * @param options A map of upload options (e.g., public_id, folder).
     * @return A map containing the upload result (e.g., secure_url, public_id).
     */
//    Map<?, ?> upload(MultipartFile file, Map<?, ?> options);

    /**
     * Deletes an image from Cloudinary using its public_id.
     *
     * @param publicId The public_id of the image to delete.
     * @return A map containing the deletion result.
     */
    Map<?, ?> delete(String publicId);

    /**
     * Updates an image in Cloudinary by deleting the old image and uploading a new one.
     *
     * @param publicId The public_id of the old image to delete.
     * @param newFile  The new image file to upload.
     * @return A map containing the upload result for the new image (e.g., secure_url, public_id).
     */
    Map<?, ?> update(String publicId, MultipartFile newFile);
    Map<?, ?> getImageDetails(String publicId);
}