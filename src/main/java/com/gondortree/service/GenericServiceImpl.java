package com.gondortree.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Collections;

/**
 *
 * @author Ítalo Moura
 */
public class GenericServiceImpl implements GenericService {
    
    
    /* GOOGLE BUCKET */
    private static final String STORAGE_SCOPE = "https://www.googleapis.com/auth/devstorage.read_write";

    @Override
    public String putBucket(final String bucketName, final String filename, File upf, final String folder) throws IOException, GeneralSecurityException {
        
        File file = new File("C:/Users/italo/Documents/INATEL/GondorTree/google/gondor-tree-4b4d7c29e236.json");
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(file)).createScoped(Collections.singleton(STORAGE_SCOPE));
        String uri = "https://storage.googleapis.com/" + URLEncoder.encode(bucketName, "UTF-8") + "/"+folder+"/" + URLEncoder.encode(filename, "UTF-8");

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory(credential);
        GenericUrl url = new GenericUrl(uri);

        FileInputStream fis = new FileInputStream(upf);
        HttpRequest request = requestFactory.buildPutRequest(url, new InputStreamContent("image/png", fis));
        request.getHeaders().set("x-goog-acl", "public-read");
        HttpResponse response = request.execute();
        String content = response.parseAsString();

        return content;

    }

    @Override
    public String deleteBucket(final String bucketName, final String filename, final String folder) throws IOException, GeneralSecurityException {

        File file = new File("C:/Users/Vivavox/Documents/cloudoss-7de0085b8ae4.json");
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(file)).createScoped(Collections.singleton(STORAGE_SCOPE));
        String uri = "https://storage.googleapis.com/" + URLEncoder.encode(bucketName, "UTF-8") + "/"+folder+"/" + URLEncoder.encode(filename, "UTF-8");

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory(credential);
        GenericUrl url = new GenericUrl(uri);

        HttpRequest request = requestFactory.buildDeleteRequest(url);
        request.getHeaders().set("x-goog-acl", "public-read");
        HttpResponse response = request.execute();
        String content = response.parseAsString();

        return content;
    }
}
