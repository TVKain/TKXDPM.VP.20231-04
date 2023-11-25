module com.itep.hust.aimsgroup {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.itep.hust.aimsgroup to javafx.fxml;
    exports com.itep.hust.aimsgroup;
}