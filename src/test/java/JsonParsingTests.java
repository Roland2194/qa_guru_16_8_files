import JsonData.SuperHero;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


public class JsonParsingTests {

    @Test
    @DisplayName("Парсинг JSON-файла")
    void getParsingJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/exampleJSON.json");
        SuperHero superHero;

        {
            try {
                superHero = objectMapper.readValue(file, SuperHero.class);
                assertThat(superHero.members[0].name).contains("Molecule Man");
                assertThat(superHero.members[1].powers[2]).contains("Superhuman reflexes");
                assertThat(superHero.active).isTrue();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
