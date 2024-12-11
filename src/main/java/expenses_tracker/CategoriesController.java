package expenses_tracker;

import java.io.IOException;

import expenses_tracker.dao.CategoryDAO;
import expenses_tracker.model.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CategoriesController {

    @FXML
    private Button addButton;

    @FXML
    private TableView<Category> categoriesTable;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button expensesButton;

    @FXML
    private TableColumn<Category, String> idColumn;

    @FXML
    private TableColumn<Category, String> iconColumn;

    @FXML
    private TableColumn<Category, String> nameColumn;

    @FXML
    void onAddAction(ActionEvent event) throws IOException {
      App.setRoot("addCategory");
    }

    @FXML
    void onEditAction(ActionEvent event) throws IOException {
        Category selectedCategory = categoriesTable.getSelectionModel().getSelectedItem();
        if(selectedCategory == null) {
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Alerta");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Nenhuma categoria selecionada.");
            successAlert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateCategory.fxml"));
        Parent root = loader.load();
    
        UpdateCategoryController controller = loader.getController();
        controller.setCategory(selectedCategory);
    
        App.getPrimaryStage().getScene().setRoot(root);
    }

    @FXML
    void onDeleteAction(ActionEvent event) {
        Category selectedCategory = categoriesTable.getSelectionModel().getSelectedItem();
        if(selectedCategory == null) {
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Alerta");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Nenhuma despesa selecionada.");
            successAlert.showAndWait();
            return;
        }

        CategoryDAO categoryDAO = new CategoryDAO();
        if(!categoryDAO.delete(selectedCategory.getId())){
            Alert successAlert = new Alert(AlertType.ERROR);
            successAlert.setTitle("Erro");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Ops... algo deu errado ao excluir a categoria.");
            successAlert.showAndWait();
            return;
        }

        categoriesTable.getItems().remove(selectedCategory);
        Alert successAlert = new Alert(AlertType.INFORMATION);
        successAlert.setTitle("Sucesso");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Categoria excluida com sucesso!");
        successAlert.showAndWait();
    }

    @FXML
    void onExpensesButtonAction(ActionEvent event) throws IOException {
      App.setRoot("expenses");
    }

    public void initialize(){
      idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
      iconColumn.setCellValueFactory(new PropertyValueFactory<>("icon"));
      nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

      CategoryDAO categoryDAO = new CategoryDAO();
      categoriesTable.getItems().addAll(categoryDAO.getAll());
    }

}
