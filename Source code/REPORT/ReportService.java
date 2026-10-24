package REPORT;

import FINANCIAL_ANALYSIS.FinancialAnalysisService;
import FINANCIAL_ANALYSIS.FinancialAnalysisService.BudgetPerformance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReportService
{
    public static File generateMonthlyReport(
              String selectedPeriod,
              int managerId,
              int restaurantId
    ) throws SQLException, IOException
    {
        // ==================================================
        // GET MONTH AND YEAR
        // ==================================================

        String[] parts =
                selectedPeriod.split(" ");

        String monthName =
                parts[0];

        int year =
                Integer.parseInt(parts[1]);

        int month =
                getMonthNumber(monthName);


        // ==================================================
        // GET FINANCIAL DATA
        // ==================================================

        double plannedBudget =
                FinancialAnalysisService.getPlannedBudget(
                        selectedPeriod,
                        managerId,
                        restaurantId
                );

        double totalRevenue =
                FinancialAnalysisService.getTotalRevenue(
                        month,
                        year,
                        restaurantId
                );

        double totalExpenses =
                FinancialAnalysisService.getTotalExpenses(
                        month,
                        year,
                        restaurantId
                );

        double finalProfit =
                FinancialAnalysisService.getNetResult(
                        month,
                        year,
                        restaurantId
                );

        List<BudgetPerformance> budgetPerformance =
                FinancialAnalysisService.getBudgetPerformance(
                        selectedPeriod,
                        managerId,
                        restaurantId,
                        month,
                        year
                );


        // ==================================================
        // FINANCIAL STATUS
        // ==================================================

        String status;

        if (finalProfit > 0)
        {
            status = "PROFITABLE";
        }
        else if (finalProfit < 0)
        {
            status = "LOSS";
        }
        else
        {
            status = "BREAK-EVEN";
        }


        // ==================================================
        // CREATE REPORT FOLDER
        // ==================================================

        File reportFolder =
                new File("reports");

        if (!reportFolder.exists())
        {
            reportFolder.mkdirs();
        }


        // ==================================================
        // CREATE REPORT FILE
        // ==================================================

        File reportFile =
                new File(
                        reportFolder,
                        selectedPeriod.replace(
                                " ",
                                "_"
                        ) + "_Report.doc"
                );


        // ==================================================
        // WRITE REPORT
        // ==================================================

        try (
                FileWriter writer =
                        new FileWriter(reportFile)
        )
        {
            writer.write(
                    "<html>" +
                    "<head>" +
                    "<meta charset='UTF-8'>" +
                    "<title>Monthly Financial Report</title>" +
                    "</head>" +

                    "<body>" +

                    "<h1 align='center'>" +
                    "RESTAURANT BUDGET PLANNER" +
                    "</h1>" +

                    "<h2 align='center'>" +
                    "MONTHLY FINANCIAL REPORT" +
                    "</h2>" +

                    "<p><b>Period:</b> " +
                    selectedPeriod +
                    "</p>" +

                    "<hr>" +

                    "<h2>Financial Summary</h2>" +

                    "<table border='1' cellpadding='8' " +
                    "cellspacing='0' width='100%'>" +

                    "<tr>" +
                    "<th>Item</th>" +
                    "<th>Amount</th>" +
                    "</tr>" +

                    "<tr>" +
                    "<td>Planned Budget</td>" +
                    "<td>" +
                    formatAmount(plannedBudget) +
                    "</td>" +
                    "</tr>" +

                    "<tr>" +
                    "<td>Total Revenue</td>" +
                    "<td>" +
                    formatAmount(totalRevenue) +
                    "</td>" +
                    "</tr>" +

                    "<tr>" +
                    "<td>Total Expenses</td>" +
                    "<td>" +
                    formatAmount(totalExpenses) +
                    "</td>" +
                    "</tr>" +

                    "<tr>" +
                    "<td>Final Profit</td>" +
                    "<td>" +
                    formatAmount(finalProfit) +
                    "</td>" +
                    "</tr>" +

                    "<tr>" +
                    "<td>Status</td>" +
                    "<td>" +
                    status +
                    "</td>" +
                    "</tr>" +

                    "</table>" +


                    "<h2>Budget Performance</h2>" +

                    "<table border='1' cellpadding='8' " +
                    "cellspacing='0' width='100%'>" +

                    "<tr>" +
                    "<th>Category</th>" +
                    "<th>Planned</th>" +
                    "<th>Actual</th>" +
                    "<th>Difference</th>" +
                    "</tr>"
            );


            // ==================================================
            // BUDGET PERFORMANCE ROWS
            // ==================================================

            for (BudgetPerformance record :
                    budgetPerformance)
            {
                writer.write(
                        "<tr>" +

                        "<td>" +
                        record.getCategory() +
                        "</td>" +

                        "<td>" +
                        formatAmount(
                                record.getPlannedAmount()
                        ) +
                        "</td>" +

                        "<td>" +
                        formatAmount(
                                record.getActualAmount()
                        ) +
                        "</td>" +

                        "<td>" +
                        formatAmount(
                                record.getDifference()
                        ) +
                        "</td>" +

                        "</tr>"
                );
            }


            // ==================================================
            // CLOSE TABLE AND DOCUMENT
            // ==================================================

            writer.write(
                    "</table>" +

                    "<br>" +

                    "<p>" +
                    "This report was generated by " +
                    "Restaurant Budget Planner." +
                    "</p>" +

                    "</body>" +
                    "</html>"
            );
        }


        return reportFile;
    }


    // ==================================================
    // FORMAT AMOUNT
    // ==================================================

    private static String formatAmount(
            double amount
    )
    {
        return String.format(
                "$%,.2f",
                amount
        );
    }


    // ==================================================
    // MONTH NUMBER
    // ==================================================

    private static int getMonthNumber(
            String monthName
    )
    {
        switch (monthName)
        {
            case "January":
                return 1;

            case "February":
                return 2;

            case "March":
                return 3;

            case "April":
                return 4;

            case "May":
                return 5;

            case "June":
                return 6;

            case "July":
                return 7;

            case "August":
                return 8;

            case "September":
                return 9;

            case "October":
                return 10;

            case "November":
                return 11;

            case "December":
                return 12;

            default:
                return 0;
        }
    }
}