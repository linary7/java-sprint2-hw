import java.util.ArrayList;

public class Checker {

    public void check (MonthlyReport monthlyReport, YearlyReport yearlyReport) {

        ArrayList<Double> expensesInMonths = new ArrayList<>();
        ArrayList<Double> incomesInMonths = new ArrayList<>();

        for (ArrayList<SpendingsByItem> report: monthlyReport.monthlyReportsContents) {
            double expenses = 0.0;
            double incomes = 0.0;
            for (SpendingsByItem spendingsByItem : report) {
                if (spendingsByItem.isExpense) {
                    expenses += spendingsByItem.quantity*spendingsByItem.sumOfOne;
                } else {
                    incomes += spendingsByItem.quantity*spendingsByItem.sumOfOne;
                }
            }
            expensesInMonths.add(expenses);
            incomesInMonths.add(incomes);
        }

        ArrayList<Integer> expensesErrors = new ArrayList<>();
        ArrayList<Integer> incomeErrors = new ArrayList<>();
        ArrayList<Double> expensesInYear = new ArrayList<>();
        ArrayList<Double> incomesInYear = new ArrayList<>();

        for (int i = 0; i < yearlyReport.yearlyReportContent.size(); i++) {
            if (yearlyReport.yearlyReportContent.get(i).isExpense) {
                expensesInYear.add(yearlyReport.yearlyReportContent.get(i).amount);
            } else {
                incomesInYear.add(yearlyReport.yearlyReportContent.get(i).amount);
            }
        }

        for (int i = 0; i < expensesInYear.size(); i++) {
            if (!expensesInMonths.get(i).equals(expensesInYear.get(i))) {
                expensesErrors.add(i + 1);
            }

            if (!incomesInMonths.get(i).equals(incomesInYear.get(i))) {
                incomeErrors.add(i + 1);
            }
        }

        if (expensesErrors.isEmpty()) {
            System.out.println("Ошибок в отчётах по расходам не обнаружено!");
        } else {
            System.out.println("Были обнаружены ошибки в отчётах по расходам в месяцах под номерами: " + expensesErrors);
        }
        if (incomeErrors.isEmpty()) {
            System.out.println("Ошибок в отчётах по доходам не обнаружено!");
        } else {
            System.out.println("Были обнаружены ошибки в отчётах по доходам в месяцах под номерами: " + expensesErrors);
        }
    }
}
