package com.liornegri.unbound.ms;

import com.liornegri.unbound.ms.util.crypto.CryptoUtil;
import com.liornegri.unbound.ms.util.crypto.RSACryptoUtil;
import com.liornegri.unbound.ms.data.dao.crypto.CryptoDataAccess;
import com.liornegri.unbound.ms.data.dao.crypto.RSADataAccess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.KeyPair;

@SpringBootApplication
public class CryptoApplication {

    @Bean
    public CryptoDataAccess<KeyPair> rsaDataAccess(){
        return new RSADataAccess();
    }

    @Bean
    public CryptoUtil<KeyPair> rsaUtil(){
        return new RSACryptoUtil();
    }

    public static void main(String[] args) {
        SpringApplication.run(CryptoApplication.class, args);
    }
}
