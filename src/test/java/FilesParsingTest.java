import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FilesParsingTest {

    private final ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @Test
    void pdfParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("Архив.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null ) {
                if (entry.getName().equals("PUBLIC-b91bfc86-a99c-4b8a-aa43-7943888b2d56.pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertEquals(pdf.numberOfPages, 1);
                    Assertions.assertEquals(pdf.producer, "OpenPDF 1.1.0" );
                    break;
                }
            }
        }
    }

    @Test
    void csvParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("Архив.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null ) {
                if (entry.getName().equals("import_contact_csv.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();
                    Assertions.assertEquals(6, data.size());
                    Assertions.assertArrayEquals(
                            new String[] {"import_contact_csv"},
                            data.get(0)
                    );
                    Assertions.assertArrayEquals(
                            new String[] {"Number;Email;Phone;Surname"},
                            data.get(1)
                    );
                    break;
                }
            }
        }
    }

    @Test
    void xlsxParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("Архив.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("all.xlsx")) {
                    XLS xls = new XLS(zis);
                    Assertions.assertTrue(xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue().contains("Прайс-лист"));
                    Assertions.assertTrue(xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue().contains("https://abrissvet.ru/"));
                }
            }
        }
    }
}
