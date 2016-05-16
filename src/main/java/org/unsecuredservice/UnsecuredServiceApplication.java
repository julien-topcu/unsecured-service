package org.unsecuredservice;

import java.util.EnumSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;

@SpringBootApplication
public class UnsecuredServiceApplication implements ServletContextInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UnsecuredServiceApplication.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        servletContext
                .setSessionTrackingModes(
                        EnumSet.of(SessionTrackingMode.URL));
    }
}
