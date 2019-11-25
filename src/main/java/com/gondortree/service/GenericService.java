package com.gondortree.service;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 *
 * @author Ítalo Moura
 */
public interface GenericService {
    
    String putBucket(String bucketName, String string, File file, String folder) throws IOException, GeneralSecurityException;
    String deleteBucket(String bucketName, String string, String folder) throws IOException, GeneralSecurityException;
}
