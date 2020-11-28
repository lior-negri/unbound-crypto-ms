package com.liornegri.unbound.ms.util.crypto;

import com.liornegri.unbound.ms.data.model.crypto.exception.CryptoException;

import java.security.*;
import java.util.Base64;

public class RSACryptoUtil implements CryptoUtil<KeyPair> {

    final static int KEY_SIZE = 2048;
    final static String ENCRYPTION_METHOD = "RSA";
    final static String SIGNATURE_METHOD = "SHA256withRSA";

    @Override
    public KeyPair generateKey() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(ENCRYPTION_METHOD);
            kpg.initialize(KEY_SIZE);

            return kpg.generateKeyPair();
        } catch (Exception e) {
            throw new CryptoException("Error occurred: key generation failed");
        }
    }

    @Override
    public byte[] sign(final KeyPair keyPair, final byte[] data) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_METHOD);
            PrivateKey privateKey = keyPair.getPrivate();
            signature.initSign(privateKey);
            signature.update(data);
            byte[] singed = signature.sign();

            return Base64.getEncoder().encode(singed);
        } catch (Exception e) {
            throw new CryptoException("Error occurred: failed to sign data");
        }
    }

    @Override
    public boolean verify(final KeyPair keyPair, final byte[] data, final String signature) {
        try {
            Signature sigInstance = Signature.getInstance(SIGNATURE_METHOD);
            PublicKey publicKey = keyPair.getPublic();
            sigInstance.initVerify(publicKey);
            sigInstance.update(data);
            byte[] decodedSig = Base64.getDecoder().decode(signature);

            return sigInstance.verify(decodedSig);
        } catch (Exception e) {
            throw new CryptoException("Error occurred: failed to verify signature");
        }

    }
}
