module expenses_tracker {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    opens expenses_tracker.model to javafx.base;
    opens expenses_tracker to javafx.fxml;
    exports expenses_tracker;
}
