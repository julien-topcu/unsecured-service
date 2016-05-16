package org.unsecuredservice.model;

public class TransferResponse {

    private String transferName;
    private String status;

    public TransferResponse() {
    }

    public TransferResponse(String transferName, String status) {
        this.transferName = transferName;
        this.status = status;
    }

    public String getTransferName() {
        return transferName;
    }

    public String getStatus() {
        return status;
    }

}
