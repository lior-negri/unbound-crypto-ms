package com.liornegri.unbound.ms.data.dao.crypto;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.KeyPair;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RSADataAccess implements CryptoDataAccess<KeyPair> {

    private final ConcurrentMap<String, KeyPair> keysMapping;

    @Autowired
    public RSADataAccess() {
        keysMapping = new ConcurrentHashMap<>();
    }

    @Override
    public String addKey(final KeyPair keyPair) {
        final String id = UUID.randomUUID().toString();
        keysMapping.put(id, keyPair);

        return id;
    }

    @Override
    public KeyPair getKey(final String id) {
        return keysMapping.get(id);
    }

    @Override
    public void removeKey(final String id) {
        keysMapping.remove(id);
    }

    @Override
    public List<String> getIds() {
        return new ArrayList<>(keysMapping.keySet());
    }

    @Override
    public boolean contains(final String id) {
        return keysMapping.containsKey(id);
    }
}
