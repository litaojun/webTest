package com.simo.boss.util;

import java.io.PrintStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesUtil
{
  public static byte[] createEncryptor(byte[] mDesKey, byte[] data)
  {
    byte[] cipherByte = null;
    try {
      IvParameterSpec mIvParameterSpec = getIV();

      DESKeySpec mDESKeySpec = new DESKeySpec(mDesKey);

      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey deskey = keyFactory.generateSecret(mDESKeySpec);

      Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");

      c.init(1, deskey, mIvParameterSpec);

      cipherByte = c.doFinal(data);
    } catch (Exception ex) {
      ex.printStackTrace();
      cipherByte = data;
    }
    return cipherByte;
  }

  public static byte[] createDecryptor(byte[] mDesKey, byte[] encryptorData)
  {
    byte[] cipherByte = null;
    try {
      IvParameterSpec mIvParameterSpec = getIV();
      DESKeySpec mDESKeySpec = new DESKeySpec(mDesKey);
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey deskey = keyFactory.generateSecret(mDESKeySpec);
      Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");
      c.init(2, deskey, mIvParameterSpec);
      cipherByte = c.doFinal(encryptorData);
    } catch (Exception ex) {
      ex.printStackTrace();
      cipherByte = encryptorData;
    }
    return cipherByte;
  }

  private static IvParameterSpec getIV() {
    return new IvParameterSpec("simomate".getBytes());
  }

  public static void main(String[] args)
  {
    byte[] b = "1234567890123456".getBytes();
    byte[] key = "12345678".getBytes();

    byte[] kk = createEncryptor(key, b);
    byte[] dd = createDecryptor(key, kk);
    System.out.println(BytesUtil.outPutToString(kk));
    System.out.println(BytesUtil.outPutToString(dd));
  }
}
