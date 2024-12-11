package expenses_tracker;

import java.io.IOException;
import expenses_tracker.dao.CategoryDAO;
import expenses_tracker.model.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddCategoryController {

    @FXML
    private Button backButton;

    @FXML
    private TextField iconField;

    @FXML
    private TextField nameField;

    @FXML
    private Button saveButton;

    @FXML
    void onBackButtonAction(ActionEvent event) throws IOException {
        App.setRoot("categories");
    }

    @FXML
    void onSaveButtonAction(ActionEvent event) {
        String iconFieldValue = iconField.getText();
        String nameFieldValue = nameField.getText();

        if(
            iconFieldValue.isEmpty() ||
            nameFieldValue.isEmpty()
        ){
            Alert warningAlert = new Alert(AlertType.INFORMATION);
            warningAlert.setTitle("Alerta");
            warningAlert.setHeaderText(null);
            warningAlert.setContentText("Preencha todos os campos.");
            warningAlert.showAndWait();
            return;
        }

        Category newCategory = new Category(nameFieldValue, iconFieldValue);

        CategoryDAO categoryDAO = new CategoryDAO();
        if(!categoryDAO.insert(newCategory)){
            Alert successAlert = new Alert(AlertType.ERROR);
            successAlert.setTitle("Erro");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Ops... algo deu errado ao criar a categoria.");
            successAlert.showAndWait();
            return;
        };

        Alert successAlert = new Alert(AlertType.INFORMATION);
        successAlert.setTitle("Sucesso");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Categoria criada com sucesso!");
        successAlert.setOnCloseRequest(e -> {
            try {
                App.setRoot("categories");
            } catch (IOException error) {
                error.printStackTrace();
            }
        });
        successAlert.showAndWait();
    }

}
