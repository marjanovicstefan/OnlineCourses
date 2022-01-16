import com.example.OnlineCourses.OnlineCoursesApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        OnlineCoursesApplication.class
})
public class OnlineCoursesApplicationIT {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void shouldCreateApplicationContext() {
        assertNotNull(applicationContext);
    }
}
