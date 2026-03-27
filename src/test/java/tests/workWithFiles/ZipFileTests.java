package tests.workWithFiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.google.common.io.Files.getFileExtension;

@Disabled("Чтоб не мешалось")
public class ZipFileTests {

    private ClassLoader cl = ZipFileTests.class.getClassLoader();

    @Test
    @DisplayName("В zip архиве есть файлы")
    void zipFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("zip/ForLesson.zip"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                System.out.println("Файл: " + fileName);
            }
        }
    }

    @Test
    @DisplayName("Проверка соответствия CSV файла")
    public void csvFileComplianceTest() throws Exception {
        boolean csvFound = false;
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("zip/ForLesson.zip"))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                String extension = getFileExtension(fileName);

                if (extension.equals("csv")) {
                    csvFound = true;
                    byte[] bytes = zis.readAllBytes();

                    try (CSVReader csvReader = new CSVReader(new InputStreamReader(new ByteArrayInputStream(bytes), StandardCharsets.UTF_8))) {
                        List<String[]> data = csvReader.readAll();
                        String[] row = data.get(0);
                        String[] expected = {"inside", "measure", "however", "determine", "trap"};
                        Assertions.assertArrayEquals(expected, row);
                        String expectedFileName = "bfotool-download.csv";
                        Assertions.assertTrue(fileName.contains(expectedFileName));
                    }
                }
            }
        }
        Assertions.assertTrue(csvFound);
    }

    @Test
    @DisplayName("Проверка соответствия XLSX файла")
    public void xlsxFileComplianceTest() throws Exception {
        boolean xlsxFound = false;
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("zip/ForLesson.zip"))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                String extension = getFileExtension(fileName);

                if (extension.equals("xlsx")) {
                    xlsxFound = true;
                    XLS xlsx = new XLS(zis.readAllBytes());
                    String actual = xlsx.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
                    String expected = "Группа проверок";
                    Assertions.assertEquals(expected, actual);
                }
            }
        }
        Assertions.assertTrue(xlsxFound);
    }

    @Test
    @DisplayName("Проверка соответствия PDF файла")
    public void pdfFileComplianceTest() throws Exception {
        boolean pdfFound = false;
        try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("zip/ForLesson.zip"))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                String extension = getFileExtension(fileName);

                if (extension.equals("pdf")) {
                    pdfFound = true;
                    PDF pdf = new PDF(zis.readAllBytes());
                    String expected = "Виктор Король";
                    Assertions.assertTrue(pdf.text.contains(expected));
                }
            }
        }
        Assertions.assertTrue(pdfFound);
    }
}
