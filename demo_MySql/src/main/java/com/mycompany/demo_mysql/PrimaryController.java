package com.mycompany.demo_mysql;

import com.mycompany.demo_mysql.config.conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button primaryButton;



    @FXML
    private void conectarBD(ActionEvent event) {
        Connection conn = null;

        try {
            conn = conexion.conectar();

            if (conn != null && !conn.isClosed()) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setContentText("CONEXION ESTABLECIDA");
                alerta.showAndWait();
            }

        } catch (SQLException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("NO FUE POSIBLE ESTABLECER LA CONEXION " + e.getMessage());
            alerta.showAndWait();

        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
}