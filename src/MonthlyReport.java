import java.util.ArrayList;
import java.util.List;

public class MonthlyReport {
    final int NUMBER_OF_MONTH_REPORTS = 3;
    ArrayList<List<String>> monthlyReports = new ArrayList<>();
    ArrayList<ArrayList<SpendingsByItem>> monthlyReportsContents = new ArrayList<>();
    ContentReader contentReader = new ContentReader();


    public void readMonthlyReports() {
        for (int i = 1; i < NUMBER_OF_MONTH_REPORTS + 1; i++) {
            List<String> monthlyReportContent = contentReader.readFileContents("resources/m.20210" + i + ".csv");
            monthlyReports.add(monthlyReportContent);
        }
        for (List<String> monthlyReport : monthlyReports) {
            ArrayList<SpendingsByItem> monthlyReportContent = new ArrayList<>();
            for (int i = 1; i < monthlyReport.size(); i++) {
                String line = monthlyReport.get(i);
                String[] content = line.split(",");
                String itemName = content[0];
                boolean isExpense = Boolean.parseBoolean(content[1]);
                int quantity = Integer.parseInt(content[2]);
                double sumOfOne = Double.parseDouble(content[3]);
                SpendingsByItem spendingsByItem = new SpendingsByItem(itemName, isExpense, quantity, sumOfOne);
                monthlyReportContent.add(spendingsByItem);
            }
            monthlyReportsContents.add(monthlyReportContent);
        }
    }

    public void printMonthlyReports() {
        for (int i = 0; i < monthlyReportsContents.size(); i++) {
            System.out.println("Отчёт за месяц номер " + (i + 1));
            double maxExpense = 0.0;
            String maxExpenseName = null;
            double biggestIncome = 0.0;
            String biggestIncomeName = null;
            for (SpendingsByItem line : monthlyReportsContents.get(i)) {
                if (!line.isExpense && (line.sumOfOne * line.quantity) > biggestIncome) {
                    biggestIncome = line.sumOfOne * line.quantity;
                    biggestIncomeName = line.itemName;
                }
                if (line.isExpense && (line.quantity * line.sumOfOne) > maxExpense) {
                    maxExpense = line.sumOfOne * line.quantity;
                    maxExpenseName = line.itemName;
                }
            }
            System.out.println("Самый прибыльный товар за этот месяц - " + biggestIncomeName + ". Получено " + biggestIncome);
            System.out.println("Самой большая трата за этот месяц - " + maxExpenseName + ". Потрачено " + maxExpense);
        }
    }

}
