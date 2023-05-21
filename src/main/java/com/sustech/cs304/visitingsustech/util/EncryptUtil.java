package com.sustech.cs304.visitingsustech.util;

import javax.crypto.Cipher;
import java.security.Key;

/**
 * Details of encrypt handling.
 *
 * @author sui_h
 */
public class EncryptUtil {
    private static String strDefaultKey = "2023@#$%^&";
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;

    public EncryptUtil() throws Exception {
        this(strDefaultKey);
    }

    public EncryptUtil(String strKey) {
        try {
            Key key = getKey(strKey.getBytes());
            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static String byteArr2HexStr(byte[] arrB) {
        int iLen = arrB.length;
        StringBuilder sb = new StringBuilder(iLen * 2);
        for (int b : arrB) {
            int intTmp = b;
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }


    public static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    public byte[] encrypt(byte[] arrB) {
        try {
            return encryptCipher.doFinal(arrB);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String encrypt(String strIn) {
        try {
            return byteArr2HexStr(encrypt(strIn.getBytes()));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public byte[] decrypt(byte[] arrB){
        try {
            return decryptCipher.doFinal(arrB);
        }catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String decrypt(String strIn) {
        return new String(decrypt(hexStr2ByteArr(strIn)));
    }

    private Key getKey(byte[] arrBTmp) {
        byte[] arrB = new byte[8];
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        return new javax.crypto.spec.SecretKeySpec(arrB, "DES");
    }
}
