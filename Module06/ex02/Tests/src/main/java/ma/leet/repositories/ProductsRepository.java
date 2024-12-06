package ma.leet.repositories;

import java.sql.SQLException;

import ma.leet.models.Product;
import java.util.List;
import java.util.Optional;


public interface ProductsRepository{
    public List<Product> findAll() throws SQLException;
    public Optional<Product> findById(Long id)throws SQLException;
    public void update(Product product) throws SQLException;
    public void save(Product product) throws SQLException;
    public void delete(Long id) throws SQLException;
}