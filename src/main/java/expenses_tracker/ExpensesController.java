package expenses_tracker;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import expenses_tracker.dao.ExpenseDAO;
import expenses_tracker.model.Expense;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExpensesController {

    @FXML
    private TableColumn<Expense, String> categoryColumn;

    @FXML
    private TableColumn<Expense, LocalDate> dateColumn;

    @FXML
    private TableColumn<Expense, String> descriptionColumn;

    @FXML
    private TableView<Expense> expensesTable;

    @FXML
    private TableColumn<Expense, Integer> idColumn;

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<Expense, BigDecimal> valueColumn;

    @FXML
    void onAddAction(ActionEvent event) throws IOException  {
        App.setRoot("addExpense");
    }

    
    @FXML
    void onEditAction(ActionEvent event) throws IOException {
        Expense selectedExpense = expensesTable.getSelectionModel().getSelectedItem();
        if(selectedExpense == null) {
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Alerta");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Nenhuma despesa selecionada.");
            successAlert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateExpense.fxml"));
        Parent root = loader.load();
    
        UpdateExpenseController controller = loader.getController();
        controller.setExpense(selectedExpense);
    
        App.getPrimaryStage().getScene().setRoot(root);
    }
    
    @FXML
    void onDeleteAction(ActionEvent event) {
        Expense selectedExpense = expensesTable.getSelectionModel().getSelectedItem();
        if(selectedExpense == null) {
            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Alerta");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Nenhuma despesa selecionada.");
            successAlert.showAndWait();
            return;
        }

        ExpenseDAO expenseDAO = new ExpenseDAO();
        if(!expenseDAO.delete(selectedExpense.getId())){
            Alert successAlert = new Alert(AlertType.ERROR);
            successAlert.setTitle("Erro");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Ops... algo deu errado ao excluir a despesa.");
            successAlert.showAndWait();
            return;
        }

        expensesTable.getItems().remove(selectedExpense);
        Alert successAlert = new Alert(AlertType.INFORMATION);
        successAlert.setTitle("Sucesso");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Despesa excluida com sucesso!");
        successAlert.showAndWait();
    }

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        ExpenseDAO expenseDAO = new ExpenseDAO();
        expensesTable.getItems().addAll(expenseDAO.getAll());
    }
    
}
