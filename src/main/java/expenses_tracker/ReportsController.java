package expenses_tracker;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import expenses_tracker.dao.ExpenseDAO;
import expenses_tracker.model.Expense;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class ReportsController {

    @FXML
    private Button backButton;

    @FXML
    private BarChart<String, BigDecimal> barChart;

    @FXML
    private Button generateButton;

    @FXML
    private DatePicker finalDateField;
    
    @FXML
    private DatePicker initialDateField;

    @FXML
    void onBackButtonAction(ActionEvent event) throws IOException {
      App.setRoot("expenses");
    }

    @FXML
    void onGenerateAction(ActionEvent event) {
      LocalDate initialDate = initialDateField.getValue();
      LocalDate finalDate = finalDateField.getValue();
      
      if(
        initialDate == null ||
        finalDate == null
      ){
        Alert warningAlert = new Alert(AlertType.INFORMATION);
        warningAlert.setTitle("Alerta");
        warningAlert.setHeaderText(null);
        warningAlert.setContentText("Preencha todos os campos.");
        warningAlert.showAndWait();
        return;
      }

      ExpenseDAO expenseDAO = new ExpenseDAO();
      List<Expense> expenses = expenseDAO.getWithinDateRange(initialDate, finalDate);      
      Map<String, BigDecimal> expensesByCategory = new HashMap<>();

      for(Expense expense : expenses){
        String categoryName = expense.getCategory().getName();
        expensesByCategory.put(
          categoryName,
          expensesByCategory.getOrDefault(categoryName, BigDecimal.ZERO).add(expense.getValue())
        );
      }

      populateBarChart(expensesByCategory);
    }

    public void initialize(){
      ExpenseDAO expenseDAO = new ExpenseDAO();
      List<Expense> expenses = expenseDAO.getAll();
      Map<String, BigDecimal> allExpensesByCategory = new HashMap<>();

      for(Expense expense : expenses){
        String categoryName = expense.getCategory().getName();
        allExpensesByCategory.put(
          categoryName,
          allExpensesByCategory.getOrDefault(categoryName, BigDecimal.ZERO).add(expense.getValue())
        );
      }

      populateBarChart(allExpensesByCategory);
    }

    private void populateBarChart(Map<String, BigDecimal> data){
      XYChart.Series<String, BigDecimal> series = new XYChart.Series<>();
      series.setName("Gastos por Categoria");

      for (Map.Entry<String, BigDecimal> entry : data.entrySet()) {
          String category = entry.getKey();
          BigDecimal total = entry.getValue();

          series.getData().add(new XYChart.Data<>(category, total));
      }

      barChart.getData().clear();
      barChart.getData().add(series);
    }

}
