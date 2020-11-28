package com.liornegri.unbound.ms.service.crypto;

import java.util.List;

public interface CryptoService {
    String generateKey();
    void deleteKey(String id);
    List<String>  getIds();
    byte[] sign(String id, byte[] data);
    boolean verify(String id, byte[] data, String signature);
}
