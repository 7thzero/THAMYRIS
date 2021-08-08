package com.ithink.utils;

import android.os.Build;
import android.util.Base64;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSAUtil {
    public static final String ALGORITHM = "RSA";
    public static final String PADDING = "RSA/None/PKCS1Padding";
    public static final String PROVIDER = "BC";
    private KeyPair keyPair = null;

    public RSAUtil() {
        try {
            this.keyPair = generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator;
        try {
            Security.addProvider(new BouncyCastleProvider());
            if (Build.VERSION.SDK_INT >= 28) {
                keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            } else {
                keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM, "BC");
            }
            keyPairGenerator.initialize(1024);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private RSAPublicKey generateRSAPublicKey(byte[] bArr, byte[] bArr2) throws Exception {
        try {
            try {
                return (RSAPublicKey) KeyFactory.getInstance(ALGORITHM, new BouncyCastleProvider()).generatePublic(new RSAPublicKeySpec(new BigInteger(bArr), new BigInteger(bArr2)));
            } catch (InvalidKeySpecException e) {
                throw new Exception(e.getMessage());
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new Exception(e2.getMessage());
        }
    }

    private RSAPrivateKey generateRSAPrivateKey(byte[] bArr, byte[] bArr2) throws Exception {
        try {
            try {
                return (RSAPrivateKey) KeyFactory.getInstance(ALGORITHM, new BouncyCastleProvider()).generatePrivate(new RSAPrivateKeySpec(new BigInteger(bArr), new BigInteger(bArr2)));
            } catch (InvalidKeySpecException e) {
                throw new Exception(e.getMessage());
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new Exception(e2.getMessage());
        }
    }

    public byte[] encrypt(Key key, byte[] bArr) throws Exception {
        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher instance = Cipher.getInstance(PADDING, "BC");
            instance.init(1, key);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] decrypt(Key key, byte[] bArr) throws Exception {
        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher instance = Cipher.getInstance(PADDING, "BC");
            instance.init(2, key);
            return instance.doFinal(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public RSAPublicKey getRSAPublicKey() throws Exception {
        RSAPublicKey rSAPublicKey = (RSAPublicKey) this.keyPair.getPublic();
        return generateRSAPublicKey(rSAPublicKey.getModulus().toByteArray(), rSAPublicKey.getPublicExponent().toByteArray());
    }

    public RSAPublicKey getRSAPublicKey(BigInteger bigInteger, BigInteger bigInteger2) throws Exception {
        return generateRSAPublicKey(bigInteger.toByteArray(), bigInteger2.toByteArray());
    }

    public RSAPrivateKey getRSAPrivateKey() throws Exception {
        RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) this.keyPair.getPrivate();
        return generateRSAPrivateKey(rSAPrivateKey.getModulus().toByteArray(), rSAPrivateKey.getPrivateExponent().toByteArray());
    }

    public RSAPrivateKey getRSAPrivateKey(BigInteger bigInteger, BigInteger bigInteger2) throws Exception {
        return generateRSAPrivateKey(bigInteger.toByteArray(), bigInteger2.toByteArray());
    }

    public static void main(String[] strArr) throws Exception {
        RSAUtil rSAUtil = new RSAUtil();
        RSAPublicKey rSAPublicKey = rSAUtil.getRSAPublicKey();
        RSAPrivateKey rSAPrivateKey = rSAUtil.getRSAPrivateKey();
        String encodeToString = Base64.encodeToString(rSAUtil.encrypt(rSAPublicKey, "chenguoyu".getBytes()), 0);
        PrintStream printStream = System.out;
        printStream.println("加密后==" + encodeToString);
        PrintStream printStream2 = System.out;
        printStream2.println("解密后==" + new String(rSAUtil.decrypt(rSAPrivateKey, encodeToString.getBytes())));
    }
}
