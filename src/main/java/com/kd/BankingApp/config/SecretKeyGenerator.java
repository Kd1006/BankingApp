package com.kd.BankingApp.config;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SecretKeyGenerator {

    public static void main(String[] args) {
        generateSecretKey();
    }
@Bean
    public static String generateSecretKey() {
        // Generate a secret key for HMAC-SHA256 algorithm
        byte[] secretKeyBytes = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256).getEncoded();

        // Convert the byte array to a Base64-encoded string
        String token= java.util.Base64.getEncoder().encodeToString(secretKeyBytes);

        // Print the secret key
        System.out.println("Generated Secret Key: " + token);
        return token;
    }
}
