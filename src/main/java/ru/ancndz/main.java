package ru.ancndz;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class main {
    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            InvalidKeyException, SignatureException, InvalidKeySpecException, IOException {
        String plainText = "MessageText";

        ECDSAProtocol ecdsaProtocol = new ECDSAProtocol();

        Message message = ecdsaProtocol.sender(plainText);

        JsonParser jsonParser = new JsonParser();

        if (jsonParser.toJson(message)) System.out.println("Write done!");


        message = (Message) jsonParser.getFromJson("message.json", Message.class);

        if (ecdsaProtocol.receiver(message)) {
            System.out.println("Verified!");
        } else {
            System.out.println("Not verified!");
        }
    }
}
