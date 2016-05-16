package org.unsecuredservice.service;

import org.unsecuredservice.model.TransferRequest;
import org.unsecuredservice.model.TransferResponse;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    public TransferResponse transfer(final TransferRequest request) {
        return new TransferResponse(request.getTransferName(), "Success");
    }
}
