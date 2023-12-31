package com.itep.hust.aimsgroup.subsystem.email;

import com.itep.hust.aimsgroup.service.email.javax.JavaxEmailService;
import org.junit.jupiter.api.Test;

class JavaxEmailServiceTest {

    @Test
    void sendMail() throws Exception {
        String htmlCode = "";
        new JavaxEmailService().sendMail("tvkain.it@gmail.com", "test", htmlCode);
    }
}