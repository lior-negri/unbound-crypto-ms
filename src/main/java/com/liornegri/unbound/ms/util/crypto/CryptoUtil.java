package com.liornegri.unbound.ms.util.crypto;

public interface CryptoUtil <T> {
    T generateKey();
    byte[] sign(T key, byte[] data);
    boolean verify(T key, byte[] data, String signature);
}
