module com {
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
	requires com.google.gson;
    opens com to javafx.fxml;
	opens com.Model;
	opens com.View;
	exports com;
	// exports com.View;
}