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

public class UpdateCategoryController {

  private Category category;

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

    Category updatedCategory = new Category(category.getId(), nameFieldValue, iconFieldValue);

    if(category.equals(updatedCategory)){
      Alert successAlert = new Alert(AlertType.INFORMATION);
      successAlert.setTitle("Alerta");
      successAlert.setHeaderText(null);
      successAlert.setContentText("Tentando salvar valores já cadastrados.");
      successAlert.showAndWait();
      return;
    }

    CategoryDAO categoryDAO = new CategoryDAO();
    if(!categoryDAO.update(updatedCategory)){
      Alert successAlert = new Alert(AlertType.ERROR);
      successAlert.setTitle("Erro");
      successAlert.setHeaderText(null);
      successAlert.setContentText("Ops... algo deu errado ao atualizar a categoria.");
      successAlert.showAndWait();
      return;
    };

    Alert successAlert = new Alert(AlertType.INFORMATION);
    successAlert.setTitle("Sucesso");
    successAlert.setHeaderText(null);
    successAlert.setContentText("Categoria atualizada com sucesso!");
    successAlert.setOnCloseRequest(e -> {
      try {
        App.setRoot("categories");
      } catch (IOException error) {
        error.printStackTrace();
      }
    });
    successAlert.showAndWait();
  }

  public void setCategory(@SuppressWarnings("exports") Category category) {
    this.category = category;

    iconField.setText(category.getIcon());
    nameField.setText(category.getName());
  }

}
