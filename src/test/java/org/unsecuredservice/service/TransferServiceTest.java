package org.unsecuredservice.service;

import javax.servlet.Filter;
import org.unsecuredservice.UnsecuredServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import static org.springframework.http.MediaType.APPLICATION_XML;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UnsecuredServiceApplication.class)
@WebAppConfiguration
public class TransferServiceTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void testTransfer() throws Exception {
        mvc.perform(post("transfer")
                .contentType(APPLICATION_XML)
                .content("<TransferRequest xmlns=\"\"><transferName>my_transfer_name</transferName><content>content_of_transfer</content></TransferRequest>"));
    }

}
