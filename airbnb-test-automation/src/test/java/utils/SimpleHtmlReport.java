package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleHtmlReport {

    private List<String> logs = new ArrayList<>();
    private String reportFile = "test-report.html";

    public void log(String message) {
        logs.add(message);
        System.out.println(message);
    }

    public void generateReport() {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Test Report</title></head><body>");
        html.append("<h1>Test Report</h1>");
        html.append("<ul>");
        for (String log : logs) {
            html.append("<li>").append(log).append("</li>");
        }
        html.append("</ul>");
        html.append("</body></html>");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {
            writer.write(html.toString());
            System.out.println("Rapor oluşturuldu: " + reportFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
