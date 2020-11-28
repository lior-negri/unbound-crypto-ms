package com.liornegri.unbound.ms.service.crypto;

import com.liornegri.unbound.ms.data.model.crypto.exception.CryptoException;
import com.liornegri.unbound.ms.util.crypto.CryptoUtil;
import com.liornegri.unbound.ms.data.dao.crypto.CryptoDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.security.KeyPair;
import java.util.*;

@Service
public class RSACryptoService implements CryptoService {

    private final CryptoDataAccess<KeyPair> rsaDataAccess;
    private final CryptoUtil<KeyPair> rsaCryptoUtil;

    @Autowired
    public RSACryptoService(final CryptoDataAccess<KeyPair> cryptoDataAccess,
                            final CryptoUtil<KeyPair> cryptoUtil) {
        rsaDataAccess = cryptoDataAccess;
        rsaCryptoUtil = cryptoUtil;
    }

    @Override
    public String generateKey() {
        KeyPair kp = rsaCryptoUtil.generateKey();
        return rsaDataAccess.addKey(kp);
    }

    @Override
    public void deleteKey(final String id) {
        if (!rsaDataAccess.contains(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Could not find KeyPair for id: %s", id));
        }
        rsaDataAccess.removeKey(id);
    }

    @Override
    public List<String> getIds() {
        return rsaDataAccess.getIds();
    }

    @Override
    public byte[] sign(final String id, final byte[] data) {
        KeyPair keyPair = getKeyPair(id);
        return rsaCryptoUtil.sign(keyPair, data);
    }

    @Override
    public boolean verify(final String id, final byte[] data, final byte[] signature) {
        KeyPair keyPair = getKeyPair(id);
        return rsaCryptoUtil.verify(keyPair, data, signature);
    }

    private KeyPair getKeyPair(final String id) {
        KeyPair keyPair = rsaDataAccess.getKey(id);
        if (keyPair == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Could not find KeyPair for id: %s", id));
        }
        return keyPair;
    }
}
