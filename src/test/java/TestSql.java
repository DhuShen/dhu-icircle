import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:spring-config.xml")
public class TestSql {
    @Test
    public void testFindAdmin() {
    }
}

