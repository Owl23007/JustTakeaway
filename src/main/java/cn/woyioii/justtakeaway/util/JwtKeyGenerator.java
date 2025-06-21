package cn.woyioii.justtakeaway.util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtKeyGenerator {
    public static void main(String[] args) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("HmacSHA512");
        SecretKey sk = kg.generateKey();
        String b64 = Base64.getEncoder().encodeToString(sk.getEncoded());
        System.out.println("JWT Secret Key (Base64): " + b64);
    }
}
