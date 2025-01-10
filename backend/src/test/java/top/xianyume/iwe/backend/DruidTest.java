package top.xianyume.iwe.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;

@SpringBootTest
public class DruidTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void getDataSourceTest() {
        System.out.println(Objects.requireNonNull(jdbcTemplate.getDataSource()).getClass());
    }
}
