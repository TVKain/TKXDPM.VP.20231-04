module com.itep.hust.aimsgroup {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires org.controlsfx.controls;
    requires org.apache.commons.lang3;
    requires org.apache.commons.validator;
    requires static lombok;

    opens com.itep.hust.aimsgroup to javafx.fxml;
    opens com.itep.hust.aimsgroup.view to javafx.fxml;
    exports com.itep.hust.aimsgroup;
    exports com.itep.hust.aimsgroup.util;
    opens com.itep.hust.aimsgroup.model.media to javafx.base;
    opens com.itep.hust.aimsgroup.view.manager to javafx.fxml;
    opens com.itep.hust.aimsgroup.view.login to javafx.fxml;
    opens com.itep.hust.aimsgroup.view.admin to javafx.fxml;
    opens com.itep.hust.aimsgroup.model.account to javafx.base;
    opens com.itep.hust.aimsgroup.view.manager.add to javafx.fxml;
    opens com.itep.hust.aimsgroup.view.manager.view to javafx.fxml;
    opens com.itep.hust.aimsgroup.view.deliveryinfo to javafx.fxml;
    opens com.itep.hust.aimsgroup.view.deliveryinfo.form to javafx.fxml;
    opens com.itep.hust.aimsgroup.view.invoice to javafx.fxml;
    opens com.itep.hust.aimsgroup.view.manager.edit to javafx.fxml;
    opens com.itep.hust.aimsgroup.view.manager.add.book to javafx.fxml;
    opens com.itep.hust.aimsgroup.view.manager.add.dvd to javafx.fxml;
    opens com.itep.hust.aimsgroup.view.manager.add.cd to javafx.fxml;

}