package com.my.backend;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@Slf4j
class BackendApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void testData() {
        try (Connection connection = dataSource.getConnection()) {
            log.info("db 연결 성공 {}", connection.getCatalog());
        } catch (SQLException e) {
            log.info("db 연결 실패 {}", e.getErrorCode());

        }

    }
}
