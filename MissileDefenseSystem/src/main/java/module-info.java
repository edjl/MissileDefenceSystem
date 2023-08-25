module com.example.missiledefensesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.example.missiledefensesystem to javafx.fxml;
    exports com.example.missiledefensesystem;
}