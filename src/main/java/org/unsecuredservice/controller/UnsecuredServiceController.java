package org.unsecuredservice.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.unsecuredservice.model.TransferRequest;
import org.unsecuredservice.model.TransferResponse;
import org.unsecuredservice.service.TransferService;

@Controller
public class UnsecuredServiceController {

    @Autowired
    private TransferService transferService;

    @Value("classpath:transfer/basic.xml")
    private Resource basicTransferTemplate;

    @Value("classpath:transfer/externalEntity.xml")
    private Resource externalEntityTransferTemplate;

    @Value("classpath:transfer/expansionEntity.xml")
    private Resource expansionEntityTransferTemplate;

    @Value("classpath:org/unsecuredservice/security/RawSQLAuthenticationProvider.java")
    private Resource rawSQLAuthenticationProvider;

    @RequestMapping(method = POST, path = "/transfer", consumes = APPLICATION_XML_VALUE, produces = APPLICATION_XML_VALUE)
    @ResponseBody
    public TransferResponse transfer(@RequestBody final TransferRequest transferRequest) {
        return transferService.transfer(transferRequest);
    }

    @RequestMapping(method = GET, path = "/whitehat")
    public ModelAndView whitehat() throws IOException {
        final ModelAndView modelAndView = new ModelAndView("whitehat");
        modelAndView.addObject("basicTransferTemplate", getContent(basicTransferTemplate));
        modelAndView.addObject("externalEntityTransferTemplate", getContent(externalEntityTransferTemplate));
        modelAndView.addObject("expansionEntityTransferTemplate", getContent(expansionEntityTransferTemplate));
        modelAndView.addObject("rawSQLAuthenticationProvider", getContent(rawSQLAuthenticationProvider));
        return modelAndView;
    }

    private String getContent(final Resource fileResource) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileResource.getURI())));
    }
}
