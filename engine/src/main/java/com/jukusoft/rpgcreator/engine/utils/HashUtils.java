/*
* Coypright (c) 2015 Justin Kuenzel
* Apache 2.0 License
*
* This file doesnt belongs to the Pentaquin Project.
* This class is owned by Justin Kuenzel and licensed under the Apache 2.0 license.
* Many projects use this class.
*/

package com.jukusoft.rpgcreator.engine.utils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * Created by Justin on 26.01.2015.
 */
public class HashUtils {

    protected static final String COMPUTESHAHASH_METHOD = "computeSHAHash";
    protected static final String COMPUTESHAPASSWORDHASH_METHOD = "computeSHAPasswordHash";
    protected static final String HASHUTILS_CLASS_NAME = "HashUtils";

    /**
    * convert byte data to hex
    */
    private static String convertToHex(byte[] data) throws IOException {
        //create new instance of string buffer
        StringBuilder StringBuilder = new StringBuilder();

        //encode byte data with base64
        String hex = Base64.getEncoder().encodeToString(data);
        StringBuilder.append(hex);

        //return string
        return StringBuilder.toString();
    }

    /**
    * generates SHA Hash
     *
     * @param password text
     *
     * @return hash
    */
    public static String computeSHAHash(String password) {
        MessageDigest mdSha1 = null;
        String SHAHash = "";

        try
        {
            mdSha1 = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e1) {
            Logger.getLogger(HASHUTILS_CLASS_NAME).throwing(HASHUTILS_CLASS_NAME, COMPUTESHAHASH_METHOD, e1);
        }

        if (mdSha1 == null) {
            throw new NullPointerException("mdSha1 cannot be null.");
        }

        try {
            mdSha1.update(password.getBytes("ASCII"));
        } catch (UnsupportedEncodingException e) {
            Logger.getLogger(HASHUTILS_CLASS_NAME).throwing(HASHUTILS_CLASS_NAME, COMPUTESHAHASH_METHOD, e);
        }

        byte[] data = mdSha1.digest();

        try {
            SHAHash = convertToHex(data);
        } catch (IOException e) {
            Logger.getLogger(HASHUTILS_CLASS_NAME).throwing(HASHUTILS_CLASS_NAME, COMPUTESHAHASH_METHOD, e);
        }

        return SHAHash;
    }

    /**
     * generates SHA-512 Hash for passwords
     *
     * @param password text
     *
     * @return hash
     */
    public static String computePasswordSHAHash(String password) {
        return computeSHAHash(password);
    }

    /**
     * generates MD5 hash
     *
     * This method is compatible to PHP 5 and Java 8.
     *
     * @param password text
     * @return hash
    */
    public static String computeMD5Hash(String password) {
        StringBuilder MD5Hash = new StringBuilder();

        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            for (int i = 0; i < messageDigest.length; i++)
            {
                String h = Integer.toHexString(0xFF & messageDigest[i]);

                while (h.length() < 2) {
                    h = "0" + h;
                }

                MD5Hash.append(h);
            }

        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(HASHUTILS_CLASS_NAME).throwing(HASHUTILS_CLASS_NAME, "computeMD5Hash", e);
        }

        return MD5Hash.toString();
    }

    /**
    * generates an MD5 file hash, like an file checksum
     *
     * @param file file
     * @return hash
    */
    public static String computeMD5FileHash (File file) throws Exception {
        byte[] b = createFileChecksum(file);
        StringBuilder result = new StringBuilder();

        for (int i=0; i < b.length; i++) {
            result.append(Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 ));
        }

        return result.toString();
    }

    private static byte[] createFileChecksum(File file) throws Exception {
        InputStream fis =  new FileInputStream(file);

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;

        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();
        return complete.digest();
    }

    /**
    * generates an hashCode of long value
     *
     * Thanks to Christoph Engelbert
    */
    public static int hashCode(long value) {
        return (int) (value ^ (value >>> 32));
    }

}
