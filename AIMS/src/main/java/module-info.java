module com.itep.hust.aimsgroup {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires static lombok;

    opens com.itep.hust.aimsgroup to javafx.fxml;
    opens com.itep.hust.aimsgroup.view to javafx.fxml;
    exports com.itep.hust.aimsgroup;
    exports com.itep.hust.aimsgroup.util;
    opens com.itep.hust.aimsgroup.model.media to javafx.base;
    opens com.itep.hust.aimsgroup.view.manager to javafx.fxml;
}