package bankstatement_analyzer_project.utils;

import bankstatement_analyzer_project.interfaces.IExporter;
import bankstatement_analyzer_project.services.SummaryStatistics;

public class HtmlExporter implements IExporter {

    @Override
    public String export(SummaryStatistics summaryStatistics) {
        String result = "<!doctype html>";

        result += "<html lang='en'>";
        result += "<head><title>Bank Transaction report</title></head>";
        result += "<body>";
        result += "<ul>";
        result += "<li><strong>The sum is</strong>: " + summaryStatistics.getSum() + "</li>";
        result += "<li><strong>The average is</strong>: " + summaryStatistics.getAverage() + "</li>";
        result += "<li><strong>The max is</strong>: " + summaryStatistics.getMax() + "</li>";
        result += "<li><strong>The min is</strong>: " + summaryStatistics.getMin() + "</li>";
        result += "</ul>";
        result += "</body>";
        result += "</html>";

        return result;
    }

}
