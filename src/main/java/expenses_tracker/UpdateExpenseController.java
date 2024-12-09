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

public class UpdateExpenseController {

    private Expense expense;

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

        Expense updatedExpense = new Expense(expense.getId(), expenseValue, descriptionFieldValue, dateFieldValue, categorySelected);

        if(expense.equals(updatedExpense)){
          Alert successAlert = new Alert(AlertType.INFORMATION);
          successAlert.setTitle("Alerta");
          successAlert.setHeaderText(null);
          successAlert.setContentText("Tentando salvar valores já cadastrados.");
          successAlert.showAndWait();
          return;
        }

        ExpenseDAO expenseDAO = new ExpenseDAO();
        if(!expenseDAO.update(updatedExpense)){
            Alert successAlert = new Alert(AlertType.ERROR);
            successAlert.setTitle("Erro");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Ops... algo deu errado ao atualizar a despesa.");
            successAlert.showAndWait();
            return;
        };

        Alert successAlert = new Alert(AlertType.INFORMATION);
        successAlert.setTitle("Sucesso");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Despesa atualizada com sucesso!");
        successAlert.setOnCloseRequest(e -> {
            try {
                App.setRoot("expenses");
            } catch (IOException error) {
                error.printStackTrace();
            }
        });
        successAlert.showAndWait();
    }
    
    public void setExpense(@SuppressWarnings("exports") Expense expense) {
      this.expense = expense;

      valueField.setText(expense.getValue().toString());
      descriptionField.setText(expense.getDescription());
      dateField.setValue(expense.getDate());
      categorySelect.setValue(expense.getCategory());
    }

    public void initialize(){
      CategoryDAO categoryDAO = new CategoryDAO();
      ObservableList<Category> categories = FXCollections.observableArrayList(categoryDAO.getAll());

      categorySelect.setItems(categories);
    }

}
