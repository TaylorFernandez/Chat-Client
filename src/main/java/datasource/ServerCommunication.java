package datasource;

import datasource.report.Report;

import java.io.IOException;

public interface ServerCommunication {

    void sendToServer() throws IOException;
}
