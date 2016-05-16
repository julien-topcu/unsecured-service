package org.unsecuredservice.security;

import javax.servlet.Filter;
import org.unsecuredservice.UnsecuredServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UnsecuredServiceApplication.class)
@WebAppConfiguration
public class RawSQLAuthenticationProviderTest {

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
    public void testLogAsAdmin() throws Exception {
        mvc.perform(
                post("/login")
                .param("username", "admin")
                .param("password", "admin")
                .contentType(APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testRejectWrongAuthentication() throws Exception {
        mvc.perform(
                post("/login")
                .param("username", "dsfdsf")
                .param("password", "tkykt,")
                .contentType(APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login?error"));
    }
}
