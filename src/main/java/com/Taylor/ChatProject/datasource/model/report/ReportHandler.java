package com.Taylor.ChatProject.datasource.model.report;

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
        System.out.println("Report: " + r.toString());
    }

    public Report getNextReport()
    {
        Report r = reportQueue.poll();
        assert r != null;
        System.out.println("Getting next Report: " + r.toString());
        return r;
    }

    public boolean hasReports() {
        return !reportQueue.isEmpty();
    }
}
