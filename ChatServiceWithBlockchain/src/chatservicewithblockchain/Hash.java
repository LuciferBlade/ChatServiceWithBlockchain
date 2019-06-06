/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatservicewithblockchain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author blade
 */
public class Hash {

    public static String hashing(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String text = data;

            md.update(text.getBytes("UTF-16"));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(String.format("%02X", digest[i]));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
        }
        return null;
    }

}
