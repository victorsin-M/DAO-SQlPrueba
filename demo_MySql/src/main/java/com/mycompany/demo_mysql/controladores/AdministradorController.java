package com.mycompany.demo_mysql.controladores;

import com.mycompany.demo_mysql.daos.AdministradorDAO;
import com.mycompany.demo_mysql.modelos.administrador;
import com.mycompany.demo_mysql.modelos.EEstados;
import com.mycompany.demo_mysql.modelos.Eroles;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class AdministradorController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtContrasena;
    @FXML
    private ComboBox<Eroles> cmbRol;
    @FXML
    private ComboBox<EEstados> cmbEstado;
    @FXML
    private TextField txtRutaImagen;
    @FXML
    private ImageView imgVistaPrevia;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TableView<administrador> tblAdministradores;
    @FXML
    private TableColumn<administrador, Integer> colId;
    @FXML
    private TableColumn<administrador, String> colUsuario;
    @FXML
    private TableColumn<administrador, String> colNombre;
    @FXML
    private TableColumn<administrador, String> colApellidos;
    @FXML
    private TableColumn<administrador, String> colCorreo;
    @FXML
    private TableColumn<administrador, Eroles> colRol;
    @FXML
    private TableColumn<administrador, EEstados> colEstado;
    @FXML
    private TableColumn<administrador, String> colRutaImagen;

    private final AdministradorDAO administradorDAO = new AdministradorDAO();
    private final ObservableList<administrador> listaObservable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colRutaImagen.setCellValueFactory(new PropertyValueFactory<>("rutaImagen"));

        cmbRol.setItems(FXCollections.observableArrayList(Eroles.values()));
        cmbRol.setValue(Eroles.MOSTRADOR);
        cmbEstado.setItems(FXCollections.observableArrayList(EEstados.values()));
        cmbEstado.setValue(EEstados.ACTIVO);

        tblAdministradores.setItems(listaObservable);
        tblAdministradores.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetalleAdministrador(newValue)
        );

        actualizarTabla();
    }

    @FXML
    private void btnSeleccionarImagenOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Foto de Perfil");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File archivoSeleccionado = fileChooser.showOpenDialog(txtId.getScene().getWindow());
        if (archivoSeleccionado != null) {
            txtRutaImagen.setText(archivoSeleccionado.getAbsolutePath());
            cargarImagenVistaPrevia(archivoSeleccionado.getAbsolutePath());
        }
    }

    @FXML
    private void btnGuardarOnAction(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = txtContrasena.getText().trim();
        Eroles rol = cmbRol.getValue();
        EEstados estado = cmbEstado.getValue();
        String rutaImagen = txtRutaImagen.getText().trim();

        if (usuario.isEmpty() || nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || rol == null || estado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Requeridos", "Usuario, Nombre, Correo, Contraseña, Rol y Estado son obligatorios.");
            return;
        }

        administrador nuevoAdmin = new administrador(0, usuario, nombre, apellidos, correo, contrasena, rol, rutaImagen, estado);

        if (administradorDAO.guardar(nuevoAdmin)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Administrador registrado correctamente en la base de datos.");
            actualizarTabla();
            limpiarCampos();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar el registro. Verifique que usuario o correo no estén duplicados.");
        }
    }

    @FXML
    private void btnActualizarOnAction(ActionEvent event) {
        if (txtId.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida", "Seleccione un administrador de la tabla para actualizar.");
            return;
        }
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String usuario = txtUsuario.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellidos = txtApellidos.getText().trim();
            String correo = txtCorreo.getText().trim();
            String contrasena = txtContrasena.getText().trim();
            Eroles rol = cmbRol.getValue();
            EEstados estado = cmbEstado.getValue();
            String rutaImagen = txtRutaImagen.getText().trim();

            administrador adminActualizar = new administrador(id, usuario, nombre, apellidos, correo, contrasena, rol, rutaImagen, estado);

            if (administradorDAO.actualizar(adminActualizar)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Registro actualizado correctamente.");
                actualizarTabla();
                limpiarCampos();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar la información en la base de datos.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Identificador no válido.");
        }
    }

    @FXML
    private void btnEliminarOnAction(ActionEvent event) {
        if (txtId.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección Requerida", "Seleccione un registro de la tabla para eliminar.");
            return;
        }
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            if (administradorDAO.borrar(id)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Administrador eliminado correctamente.");
                actualizarTabla();
                limpiarCampos();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el registro de la base de datos.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Identificador no válido.");
        }
    }

    @FXML
    private void btnLimpiarOnAction(ActionEvent event) {
        limpiarCampos();
    }

    private void mostrarDetalleAdministrador(administrador admin) {
        if (admin != null) {
            txtId.setText(String.valueOf(admin.getId()));
            txtUsuario.setText(admin.getUsuario());
            txtNombre.setText(admin.getNombre());
            txtApellidos.setText(admin.getApellidos());
            txtCorreo.setText(admin.getCorreo());
            txtContrasena.setText(admin.getContraseña());
            cmbRol.setValue(admin.getRol());
            cmbEstado.setValue(admin.getEstado());
            txtRutaImagen.setText(admin.getRutaImagen());
            cargarImagenVistaPrevia(admin.getRutaImagen());
        }
    }

    private void cargarImagenVistaPrevia(String ruta) {
        if (ruta != null && !ruta.trim().isEmpty()) {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                Image imagen = new Image(archivo.toURI().toString());
                imgVistaPrevia.setImage(imagen);
                return;
            }
        }
        imgVistaPrevia.setImage(null);
    }

    private void actualizarTabla() {
        listaObservable.setAll(administradorDAO.obtenerTodos());
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtUsuario.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
        cmbRol.setValue(Eroles.MOSTRADOR);
        cmbEstado.setValue(EEstados.ACTIVO);
        txtRutaImagen.setText("");
        imgVistaPrevia.setImage(null);
        tblAdministradores.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}