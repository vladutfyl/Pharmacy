package utils.report;

import model.entity.BoughtDrug;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class TXTReport implements ReportFactory {
    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void saveReport(Set<BoughtDrug> boughtDrugs) throws IOException {
        FileWriter myWriter = new FileWriter(file.getCanonicalFile());
        for(BoughtDrug boughtDrug:boughtDrugs){
            myWriter.write(boughtDrug.toString());
            myWriter.write("\n");
        }
        myWriter.close();
    }
}
