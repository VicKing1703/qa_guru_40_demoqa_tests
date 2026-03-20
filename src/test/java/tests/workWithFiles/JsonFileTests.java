package tests.workWithFiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.models.Metadata;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonFileTests {
    private ClassLoader cl = JsonFileTests.class.getClassLoader();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Проверка значений json файла")
    public void testJsonFile() throws Exception {
        try(InputStream inputStream = cl.getResourceAsStream("json/metadata.json")) {
            Metadata metadata = mapper.readValue(inputStream, Metadata.class);

            Assertions.assertEquals("buddy4study.com", metadata.getSource());
            Assertions.assertEquals("Selenium WebDriver (JavaScript rendered content)", metadata.getMethod());
            Assertions.assertEquals(38, metadata.getCategories().getFellowship());
        }
    }
}
