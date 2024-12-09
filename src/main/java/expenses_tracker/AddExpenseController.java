package expenses_tracker;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import expenses_tracker.dao.CategoryDAO;
import expenses_tracker.dao.ExpenseDAO;
import expenses_tracker.model.Category;
import expenses_tracker.model.Expense;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddExpenseController {

    @FXML
    private Button backButton;
    
    @FXML
    private ComboBox<Category> categorySelect;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Button saveButton;

    @FXML
    private TextField valueField;

    @FXML
    void onBackButtonAction(ActionEvent event) throws IOException {
      App.setRoot("expenses");
    }

    @FXML
    void onSaveButtonAction(ActionEvent event) {

        String valueFieldValue = valueField.getText();
        String descriptionFieldValue = descriptionField.getText();
        LocalDate dateFieldValue = dateField.getValue();
        Category categorySelected = categorySelect.getSelectionModel().getSelectedItem(); 

        BigDecimal expenseValue;

        if(
            valueFieldValue.isEmpty() ||
            descriptionFieldValue.isEmpty() ||
            dateFieldValue == null ||
            categorySelected == null
        ){
            Alert warningAlert = new Alert(AlertType.INFORMATION);
            warningAlert.setTitle("Alerta");
            warningAlert.setHeaderText(null);
            warningAlert.setContentText("Preencha todos os campos.");
            warningAlert.showAndWait();
            return;
        }

        try {
            expenseValue = new BigDecimal(valueFieldValue.replace(',', '.'));
        } catch (NumberFormatException  e) {
            Alert warningAlert = new Alert(AlertType.INFORMATION);
            warningAlert.setTitle("Alerta");
            warningAlert.setHeaderText(null);
            warningAlert.setContentText("Valor inválido. Por favor, insira um número válido");
            warningAlert.showAndWait();
            return;        
        }

        Expense newExpense = new Expense(expenseValue, descriptionFieldValue, dateFieldValue, categorySelected);

        ExpenseDAO expenseDAO = new ExpenseDAO();
        if(!expenseDAO.insert(newExpense)){
            Alert successAlert = new Alert(AlertType.ERROR);
            successAlert.setTitle("Erro");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Ops... algo deu errado ao criar a despesa.");
            successAlert.showAndWait();
            return;
        };

        Alert successAlert = new Alert(AlertType.INFORMATION);
        successAlert.setTitle("Sucesso");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Despesa criada com sucesso!");
        successAlert.setOnCloseRequest(e -> {
            try {
                App.setRoot("expenses");
            } catch (IOException error) {
                error.printStackTrace();
            }
        });
        successAlert.showAndWait();
    }

    public void initialize(){
        //init dateField
        dateField.setValue(LocalDate.now());

        //init categorySelect
        CategoryDAO categoryDAO = new CategoryDAO();
        ObservableList<Category> categories = FXCollections.observableArrayList(categoryDAO.getAll());

        categorySelect.setItems(categories);
    }
}