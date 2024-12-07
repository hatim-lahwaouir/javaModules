package ma.leet.repositories;


import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class EmbeddedDataSourceTest{
    EmbeddedDatabase db;

    @BeforeEach
    void init(){

        this.db = new EmbeddedDatabaseBuilder().setType( EmbeddedDatabaseType.valueOf("HSQL")).addScript("schema.sql").addScript("data.sql").build();

    }


    @Test
    void getConnectionTest() throws SQLException {
        assertNotNull(db.getConnection());
    }




}