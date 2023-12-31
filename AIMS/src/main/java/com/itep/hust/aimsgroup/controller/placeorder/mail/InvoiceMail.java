package com.itep.hust.aimsgroup.controller.placeorder.mail;

import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.invoice.Invoice;
import com.itep.hust.aimsgroup.model.media.Media;

import java.text.DecimalFormat;
import java.util.Map;

public class InvoiceMail {
    public static String getContent(Invoice invoice){
        DecimalFormat format = new DecimalFormat("0.#");
        StringBuilder htmlCode = new StringBuilder("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n" +
                "  <title>Purchased items</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      font-family: Arial, sans-serif;\n" +
                "      background-color: #f4f4f4;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "    h2 {\n" +
                "      text-align: center;\n" +
                "      color: #333;\n" +
                "    }\n" +
                "    table {\n" +
                "      border-collapse: collapse;\n" +
                "      width: 80%;\n" +
                "      margin: 20px auto;\n" +
                "      background-color: #fff;\n" +
                "      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "    }\n" +
                "    th, td {\n" +
                "      border: 1px solid #ddd;\n" +
                "      padding: 12px;\n" +
                "      text-align: left;\n" +
                "    }\n" +
                "    th {\n" +
                "      background-color: #f2f2f2;\n" +
                "    }\n" +
                "    tr:nth-child(even) {\n" +
                "      background-color: #f9f9f9;\n" +
                "    }\n" +
                "    tr:hover {\n" +
                "      background-color: #e2e2e2;\n" +
                "    }\n" +
                "    button {\n" +
                "      padding: 8px 12px;\n" +
                "      cursor: pointer;\n" +
                "      background-color: #4caf50;\n" +
                "      color: #fff;\n" +
                "      border: none;\n" +
                "      border-radius: 4px;\n" +
                "    }\n" +
                "    button:hover {\n" +
                "      background-color: #45a049;\n" +
                "    }\n" +
                "    a {\n" +
                "      color: #007BFF;\n" +
                "      text-decoration: none;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>Purchased items</h2>\n" +
                "\n" +
                "<table>\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th>ID</th>\n" +
                "      <th>Title</th>\n" +
                "      <th>Category</th>\n" +
                "      <th>Price</th>\n" +
                "      <th>Quantity</th>\n" +
                "      <th>Support rush order</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>");
        for (Map.Entry<Media, Integer> entry : Cart.getInstance().getMedias().entrySet()) {
            String itemCode = new String("<tr>\n" +
                    "      <td>" + entry.getKey().getId() + "</td>\n" +
                    "      <td>" + entry.getKey().getTitle() + "</td>\n" +
                    "      <td>" + entry.getKey().getCategory() + "</td>\n" +
                    "      <td>" + entry.getKey().getPrice() + "</td>\n" +
                    "      <td>" + entry.getKey().getQuantity() + "</td>\n" +
                    "      <td>" + entry.getKey().isRushDelivery() + "</td>\n" +
                    "    </tr>");
            htmlCode.append(itemCode);
        }
        htmlCode.append("</tbody>\n" +
                "</table>\n" +
                "<h3>Buyer: "+invoice.getDeliveryInfo().getName()+"</h3>\n" +
                "<h3>Address: "+invoice.getDeliveryInfo().getAddress()+"</h3>\n" +
                "<h3>City: "+invoice.getDeliveryInfo().getCity()+"</h3>\n" +
                "<h3>Phone: "+invoice.getDeliveryInfo().getPhone()+"</h3>\n" +
                "<h2>VAT: "+format.format(invoice.getVat())+"%</h2>\n" +
                "<h2>Media subtotal: "+format.format(invoice.getMediaSubtotal())+"</h2>\n" +
                "<h2>Media total: "+format.format(invoice.getMediaTotal())+"</h2>\n" +
                "<h2>Shipping fee: "+format.format(invoice.getShippingFee())+"</h2>\n" +
                "<h2>Total: "+format.format(invoice.getTotal())+"</h2>\n" +
                "</body>\n" +
                "</html>");
        return String.valueOf(htmlCode);
    }
}
