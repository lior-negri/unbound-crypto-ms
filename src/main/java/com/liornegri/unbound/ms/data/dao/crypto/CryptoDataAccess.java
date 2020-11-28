package com.liornegri.unbound.ms.data.dao.crypto;

import java.util.List;

public interface CryptoDataAccess <T> {
    String addKey(T key);
    T getKey(String id);
    void removeKey(String id);
    List<String> getIds();
    boolean contains(String id);
}
