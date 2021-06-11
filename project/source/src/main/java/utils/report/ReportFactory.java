package utils.report;

import model.entity.BoughtDrug;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public interface ReportFactory {
    void setFile(File file);
    void saveReport(Set<BoughtDrug> boughtDrugs) throws IOException;
}
