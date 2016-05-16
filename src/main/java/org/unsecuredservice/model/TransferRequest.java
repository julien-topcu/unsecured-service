package org.unsecuredservice.model;

public class TransferRequest {

    private String transferName;
    private String content;

    public TransferRequest() {

    }

    public TransferRequest(String transferName, String content) {
        this.transferName = transferName;
        this.content = content;
    }

    public String getTransferName() {
        return transferName;
    }

    public String getContent() {
        return content;
    }

}
