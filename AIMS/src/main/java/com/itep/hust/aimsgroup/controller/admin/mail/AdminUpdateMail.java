package com.itep.hust.aimsgroup.controller.admin.mail;

import com.itep.hust.aimsgroup.model.account.Account;

public class AdminUpdateMail {
    public static String getContent(Account account) {
        String htmlPayload = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>AIMS - Account create</title>
                </head>
                <body>
                <header>
                    <div class="container">
                        <div id="branding">
                            <h1><span class="highlight">AIMS Account</span></h1>
                        </div>
                    </div>
                </header>

                <main>
                    <div class="container">
                        <h2>Your account information has been updated</h2>
                        <p>Current account details</p>
                        <ul>
                            <li>Email: [email]</li>
                            <li>Password: [password]</li>
                            <li>Role: [roles]</li>
                        </ul>

                    </div>
                </main>

                <footer>
                    <div class="container">
                        <p>&copy; 2023 AIMS. All rights reserved.</p>
                    </div>
                </footer>
                </body>
                </html>""";

        htmlPayload = htmlPayload.replace("[email]", account.getEmail());
        htmlPayload = htmlPayload.replace("[password]", account.getPassword());
        htmlPayload = htmlPayload.replace("[roles]", account.getRoles().toString());

        return htmlPayload;
    }
}
