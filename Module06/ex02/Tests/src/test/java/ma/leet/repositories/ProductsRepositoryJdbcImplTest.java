import ma.leet.models.Product;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;


import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;




import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.Optional;


import ma.leet.repositories.ProductsReposutoryJdbcImpl;


class ProductsRepositoryJdbcImplTest {
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new LinkedList<>(Arrays.asList( new Product(0L, 500L, "Product1") , new Product(1L,400L ,"Product2"), new Product(2L, 100L, "Product3") ));
    final Product EXPECTED_FIND_BY_ID_PRODUCT =  new Product(1L, 500L, "Product1");
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L,300L, "xxx");

    EmbeddedDatabase db;
    ProductsReposutoryJdbcImpl productRepo = null;
    // assertTrue

    @BeforeEach
    void init() throws SQLException{
        this.db = new EmbeddedDatabaseBuilder().setType( EmbeddedDatabaseType.valueOf("HSQL")).addScript("schema.sql").addScript("data.sql").build();
        this.productRepo = new ProductsReposutoryJdbcImpl(db.getConnection());
    }

    

    @Test
    void findAllProductTest() throws SQLException
    {
        List<Product>  result = this.productRepo.findAll();

        assertTrue(result.size() == EXPECTED_FIND_ALL_PRODUCTS.size());

        for (int i = 0; i < result.size(); i++){
            assertTrue(result.get(i).equals(EXPECTED_FIND_ALL_PRODUCTS.get(i)));
        }
    }

    @Test
    void findByIdTest() throws SQLException {
        Optional<Product> product = this.productRepo.findById(EXPECTED_FIND_BY_ID_PRODUCT.getId());

        assertTrue(product.isPresent());

        
        assertTrue(EXPECTED_FIND_BY_ID_PRODUCT.equals(product.get()));
    }


    @Test
    void updateTest() throws SQLException{
        Product product = new Product(EXPECTED_UPDATED_PRODUCT.getId(), EXPECTED_UPDATED_PRODUCT.getPrice(), EXPECTED_UPDATED_PRODUCT.getName());
        this.productRepo.update(product);
        assertTrue(EXPECTED_UPDATED_PRODUCT.equals(product));
    }



}