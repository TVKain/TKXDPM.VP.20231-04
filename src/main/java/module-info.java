module com.itep.hust.aimsgroup {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    opens com.itep.hust.aimsgroup to javafx.fxml;
    opens com.itep.hust.aimsgroup.view to javafx.fxml;
    exports com.itep.hust.aimsgroup;
    exports com.itep.hust.aimsgroup.util;
}