module com.mycompany.demo_mysql {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;

    opens com.mycompany.demo_mysql to javafx.fxml;
    opens com.mycompany.demo_mysql.controladores to javafx.fxml;
    opens com.mycompany.demo_mysql.modelos to javafx.base;
    exports com.mycompany.demo_mysql;
    exports com.mycompany.demo_mysql.controladores;
    exports com.mycompany.demo_mysql.modelos;
}