package ru.ancndz;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ECDSAProtocol {

    private static final String SPEC = "secp256k1";
    private static final String ALGO = "SHA256withECDSA";

    public Message sender(String plaintext) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, SignatureException {

        ECGenParameterSpec ecSpec = new ECGenParameterSpec(SPEC);
        KeyPairGenerator g = KeyPairGenerator.getInstance("EC");
        g.initialize(ecSpec, new SecureRandom());
        KeyPair keypair = g.generateKeyPair();
        PublicKey publicKey = keypair.getPublic();
        PrivateKey privateKey = keypair.getPrivate();

        Signature ecdsaSign = Signature.getInstance(ALGO);
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(plaintext.getBytes(StandardCharsets.UTF_8));
        byte[] signature = ecdsaSign.sign();
        String pub = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String sig = Base64.getEncoder().encodeToString(signature);

        return new Message(pub, sig, plaintext, ALGO);
    }

    public boolean receiver(Message message) throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, SignatureException {
        Signature ecdsaVerify = Signature.getInstance(message.getAlgorithm());
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(message.getPublicKey()));
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(message.getMessage().getBytes(StandardCharsets.UTF_8));
        return ecdsaVerify.verify(Base64.getDecoder().decode(message.getSignature()));
    }

}
