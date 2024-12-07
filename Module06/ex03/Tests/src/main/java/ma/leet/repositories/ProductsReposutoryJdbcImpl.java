package ma.leet.repositories;

import ma.leet.models.Product;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class  ProductsReposutoryJdbcImpl implements ProductsRepository {


    Connection connection;


    public ProductsReposutoryJdbcImpl(Connection con){
        this.connection = con;
    }

    public List<Product> findAll() throws SQLException  {
        List<Product> ret = new LinkedList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM product;");
        ResultSet result =  statement.executeQuery();

        while (result.next()){
            ret.add(new Product(result.getLong("id"),result.getLong("price"), result.getString("name")));
        }
        return ret;
    }

    public Optional<Product> findById(Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE id = ?;");
        statement.setLong(1, id);
        ResultSet result =  statement.executeQuery();
        if (result.next()){
            return Optional.ofNullable(new Product(result.getLong("id"),result.getLong("price"), result.getString("name")));
        }
        return Optional.ofNullable(null);
    }

    public void update(Product product) throws SQLException {
        Long id = product.getId();
        PreparedStatement statement = connection.prepareStatement("UPDATE  product SET price = ?, name = ? WHERE id = ?;");
        statement.setLong(3, product.getId());
        statement.setLong(1, product.getPrice());
        statement.setString(2, product.getName());

        statement.executeUpdate();

    }

    public void save(Product product) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO  product (name, price) VALUES (?,  ?);", PreparedStatement.RETURN_GENERATED_KEYS);

        statement.executeUpdate();

        ResultSet result =  statement.getGeneratedKeys();

        if (result.next())
            product.setId(result.getLong("id"));
    }

    public void delete(Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE id = ? ;", PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setLong(1, id);
        statement.executeUpdate();
    }
}