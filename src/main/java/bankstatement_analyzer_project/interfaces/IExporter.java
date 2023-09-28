package bankstatement_analyzer_project.interfaces;

import bankstatement_analyzer_project.services.SummaryStatistics;

public interface IExporter {
    String export(SummaryStatistics summaryStatistics);
}