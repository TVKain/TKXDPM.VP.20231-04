package com.itep.hust.aimsgroup.subsystem.email;

import com.itep.hust.aimsgroup.subsystem.email.javax.JavaxEmailSubsystem;
import org.junit.jupiter.api.Test;

class JavaxEmailSubsystemTest {

    @Test
    void sendMail() throws Exception {
        String htmlCode = "";
        new JavaxEmailSubsystem().sendMail("tvkain.it@gmail.com", "test", htmlCode);
    }
}