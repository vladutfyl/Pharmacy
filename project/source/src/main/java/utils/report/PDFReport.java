package utils.report;

import model.entity.BoughtDrug;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class PDFReport implements ReportFactory {
    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void saveReport(Set<BoughtDrug> boughtDrugs) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream pageContentStream = new PDPageContentStream(document, page);
        pageContentStream.beginText();
        pageContentStream.setFont( PDType1Font.TIMES_ROMAN , 10 );
        pageContentStream.setLeading(14.5f);
        pageContentStream.newLineAtOffset(30,  700);
        for(BoughtDrug boughtDrug:boughtDrugs){
            pageContentStream.showText(boughtDrug.toString());
            pageContentStream.newLine();
        }
        pageContentStream.endText();
        pageContentStream.close();
        document.save(file.getCanonicalFile());
    }
}
