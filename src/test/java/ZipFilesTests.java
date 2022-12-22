import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class ZipFilesTests {
    ClassLoader cl = ZipFilesTests.class.getClassLoader();

    @Test
    @DisplayName("Парсинг ZIP-архива")
    void zipParsingTest() throws IOException, CsvException {
        try (
                InputStream resource = cl.getResourceAsStream("exampleZip.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                String fileSuffix = zipEntry.getName().split("\\.")[1];
                switch (fileSuffix) {
                    case "pdf":
                        PDF pdf = new PDF(zis);
                        assertThat(pdf.author).contains("Nuance Communications, Inc.");
                        break;
                    case "xls":
                        XLS xls = new XLS(zis);
                        assertThat(xls.excel.getSheetAt(0).getRow(2).getCell(3).getStringCellValue()).contains("Female");
                        break;
                    case "csv":
                        CSVReader reader = new CSVReader(new InputStreamReader(zis));
                        List<String[]> stringList = reader.readAll();
                        assertThat(stringList.get(2)[2]).contains("Chris Riley");
                        break;
                }
            }
        }
    }
}
