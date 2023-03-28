import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
        // (=`ω´=)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        Checker checker = new Checker();

        while (true) {
            printMenu();
            int userInput = scanUserInput(scanner);
            if (userInput == 1) {
                monthlyReport.readMonthlyReports();
            } else if (userInput == 2) {
                yearlyReport.readYearlyReport();
            } else if (userInput == 3) {
                if (monthlyReport.monthlyReports.isEmpty() || yearlyReport.yearlyReport == null) {
                    System.out.println("Вы пока не предоставляли месячные и/или годовые отчёты. Воспользуйтесь командой 1 и/или 2!");
                } else {
                    checker.check(monthlyReport, yearlyReport);
                }
            } else if (userInput == 4) {
                if (!monthlyReport.monthlyReports.isEmpty()) {
                    monthlyReport.printMonthlyReports();
                } else {
                    System.out.println("Вы пока не предоставляли месячные отчёты. Воспользуйтесь командой 1!");
                }
            } else if (userInput == 5) {
                if (yearlyReport.yearlyReport != null) {
                   yearlyReport.printYearlyReport();
                } else {
                    System.out.println("Вы пока не предоставляли годовой отчёт. Воспользуйтесь командой 2!");
                }
            } else if (userInput == 3058) {
                System.out.println("Завершаем работу");
                return;
            } else {
                System.out.println("Такой команды пока нет");
            }
        }

    }

    static void printMenu() {
        System.out.println("1 - Cчитать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("3058 - код для завершения работы");
        System.out.println("Введите номер команды:");
    }

    public static int scanUserInput(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException ime) {
            System.out.println("Недопустимые данные. Введите номер одной из предложенных команд");
            scanner.nextLine();
            return -1;
        }
    }
}

