import java.util.ArrayList;
import java.util.List;

public class YearlyReport {

    public ArrayList<MonthlySpendings> yearlyReportContent = new ArrayList<>();
    List<String> yearlyReport;
    ContentReader contentReader = new ContentReader();

    public void readYearlyReport() {
        yearlyReport = contentReader.readFileContents("resources/y.2021.csv");

        for (int i = 1; i < yearlyReport.size(); i++) {
            String line = yearlyReport.get(i);
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            double amount = Double.parseDouble(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            MonthlySpendings monthlySpendings = new MonthlySpendings(month, amount, isExpense);
            yearlyReportContent.add(monthlySpendings);
        }
    }

    public void printYearlyReport() {
        System.out.println("Годовой отчёт за год");
        System.out.println(" ");
        double expenseSum = 0.0;
        double incomeSum = 0.0;
        for (MonthlySpendings monthlySpendings : yearlyReportContent) {
            if (monthlySpendings.isExpense) {
                expenseSum += monthlySpendings.amount;
            } else {
                incomeSum += monthlySpendings.amount;
            }
        }

        ArrayList<Double> expenses = new ArrayList<>();
        ArrayList<Double> income = new ArrayList<>();
        for (MonthlySpendings monthlySpendings : yearlyReportContent) {
            if (monthlySpendings.isExpense) {
                expenses.add(monthlySpendings.amount);
            } else {
                income.add(monthlySpendings.amount);
            }
        }

        for (int i = 0; i < income.size(); i++) {
            System.out.println("Прибыль за месяц номер " + (i + 1) + " составила " + (income.get(i) - expenses.get(i)));
        }

        System.out.println("Средний расход за все месяцы: " + expenseSum/(yearlyReportContent.size()/2));
        System.out.println("Средний доход за все месяцы: " + incomeSum/(yearlyReportContent.size()/2));
    }
}