package com.itep.hust.aimsgroup.subsystem.email;

import com.itep.hust.aimsgroup.service.email.JavaMailUtil;
import org.junit.jupiter.api.Test;

class JavaMailUtilTest {

    @Test
    void sendMail() throws Exception {
        String htmlCode = "";
        JavaMailUtil.sendMail("tvkain.it@gmail.com",htmlCode);
    }
}