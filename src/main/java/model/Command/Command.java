package model.Command;

import datasource.report.Report;

import java.io.IOException;

public interface Command {
    void execute() throws IOException;

}
