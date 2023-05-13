package datasource.report;

import java.util.ArrayList;
import java.util.List;

public class ReportHandler {

    private final List<Report> reportList = new ArrayList<>();

    private static ReportHandler singleton;

    private ReportHandler() {
    }

    public static synchronized ReportHandler getSingleton() {
        if (singleton != null) {
            return singleton;
        }
        singleton = new ReportHandler();
        return singleton;
    }

    public void addNewReport(Report r) {
        synchronized (reportList) {
            reportList.add(r);
        }
        System.out.println(r.toString());
    }

    public List<Report> getAllReports() {
        synchronized (reportList) {
            return new ArrayList<>(reportList); // Return a copy of the list to prevent modification of the original list
        }
    }
}
