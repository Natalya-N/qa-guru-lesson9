import com.fasterxml.jackson.databind.ObjectMapper;
import model.Catalog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonParsingTest {

    private final ClassLoader cl = JsonParsingTest.class.getClassLoader();


    @Test
    void jsonParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("example.json")) {
            Catalog catalog = new ObjectMapper().readValue(is, Catalog.class);
            Assertions.assertEquals(catalog.getConditions(), ("Предложения действительны для Москвы, Переславль-Залесского и Костромской области"));
            Assertions.assertEquals(catalog.getDate().getDateEnd(), "2020-06-12");
            Assertions.assertEquals(catalog.getDate().getDateStart(), "2020-06-05");
            Assertions.assertEquals(catalog.getId(), 1234);
            Assertions.assertEquals(catalog.getIsMain(), true);
            Assertions.assertEquals(catalog.getOffers(), List.of("11111", "22222", "33333"));
        }

    }
}
