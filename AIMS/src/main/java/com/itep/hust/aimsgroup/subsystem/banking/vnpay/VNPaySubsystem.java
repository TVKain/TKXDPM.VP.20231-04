package com.itep.hust.aimsgroup.subsystem.banking.vnpay;

import com.itep.hust.aimsgroup.subsystem.banking.Banking;
import com.itep.hust.aimsgroup.util.Screen;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class VNPaySubsystem implements Banking {
    private VNPayController vnPayController = new VNPayController();

    @Override
    public void processTransaction(double money, Runnable onSuccess, Runnable onFailure) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        Screen.setScreen(webView);

        webEngine.load(vnPayController.buildUrl(String.valueOf((int) money)));
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED.equals(newValue)) {
                String url = webEngine.getLocation();

                if (url.contains("vnp_TransactionStatus")) {
                    if (url.contains("vnp_TransactionStatus=00")) {
                        onSuccess.run();
                    } else {
                        onFailure.run();
                    }
                }
            }
        });
    }
}
