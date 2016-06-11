function login(event) {
    if (!event.ctrlKey) {
        document.getElementById("loginForm").submit();
    } else {
        // Set any custom configuration options here or in an external js file that gets sourced in above.
        Base.esapi.properties.logging['ApplicationLogger'] = {Level: org.owasp.esapi.Logger.ALL, Appenders: [new Log4js.ConsoleAppender()], LogUrl: true, LogApplicationName: true, EncodingRequired: true};
        Base.esapi.properties.application.Name = "Unsecured Service";
        // Initialize the api
        org.owasp.esapi.ESAPI.initialize();
        // Using the logger 
        alert($ESAPI.encoder().encodeForSQL("' OR '1'='1"));
    }

}


