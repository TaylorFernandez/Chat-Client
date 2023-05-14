package com.Taylor.ChatProject.datasource.report;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ReportHandler{

    private final ConcurrentLinkedQueue<Report> reportQueue = new ConcurrentLinkedQueue<>();

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
        reportQueue.add(r);
        System.out.println(r.toString());
    }

    public Report getNextReport() {
        return reportQueue.poll();
    }

    public boolean hasReports() {
        return !reportQueue.isEmpty();
    }
}
