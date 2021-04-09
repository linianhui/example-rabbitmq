package producer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class SpringTest {

    @Autowired
    protected WebTestClient http;

    @BeforeEach
    void beforeEach() {
        this.http = http.mutate()
            .responseTimeout(Duration.ofHours(1))
            .build();
    }

    @AfterEach
    protected void afterEach() {
        afterEachCore();
    }

    protected void afterEachCore() {
    }
}
