package ru.ancndz;

public class Message {

    private String publicKey;
    private String signature;
    private String message;
    private String algorithm;

    public Message() {
    }

    public Message(String publicKey, String signature, String message, String algorithm) {
        this.publicKey = publicKey;
        this.signature = signature;
        this.message = message;
        this.algorithm = algorithm;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
