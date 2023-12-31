package com.itep.hust.aimsgroup.subsystem.banking.vnpay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class VNPayController {
    public String buildUrl(String amount) {
        String url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?";
        String vnp_Amount = "vnp_Amount=" + amount + "00&";
        String vnp_BankCode = "vnp_BankCode=NCB&";
        String vnp_Command = "vnp_Command=pay&";
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = "vnp_CreateDate=" + formatter.format(cld.getTime()) + "&";

        String vnp_CurrCode = "vnp_CurrCode=VND&";
        String vnp_IpAddr = "vnp_IpAddr=222.252.10.226&";
        String vnp_Locale = "vnp_Locale=vn&";
        String vnp_OrderInfo = "vnp_OrderInfo=Thanh+toan+don+hang+%3A5&";
        String vnp_OrderType = "vnp_OrderType=other&";
        String vnp_ReturnUrl = "vnp_ReturnUrl=https%3A%2F%2Fsandbox.vnpayment.vn%2Ftryitnow%2FHome%2FVnPayReturn&";
        String vnp_TmnCode = "vnp_TmnCode=" + VNPayConfig.vnp_TmnCode + "&";
        String vnp_TxnRef = "vnp_TxnRef=" + VNPayConfig.getRandomNumber(8) + "&";
        String vnp_Version = "vnp_Version=2.1.0";

        String hashData = vnp_Amount + vnp_BankCode + vnp_Command  + vnp_CreateDate + vnp_CurrCode + vnp_IpAddr + vnp_Locale + vnp_OrderInfo + vnp_OrderType + vnp_ReturnUrl + vnp_TmnCode + vnp_TxnRef + vnp_Version;
        url += hashData;
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData);
        url += "&vnp_SecureHash=" + vnp_SecureHash;
        System.out.println(url);
        return url;
    }
}
