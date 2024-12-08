package pl.amilossh.spring_postgresql.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.amilossh.spring_postgresql.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JdbcProductRepository implements ProductRepository, RowMapper<Product> {

    private final JdbcOperations jdbcOperations;

    @Override
    public List<Product> getAll() {
        return jdbcOperations.query("select * from product", this);
    }

    @Override
    public void save(Product product) {
        jdbcOperations.update("""
            insert into product(id, c_name) values (?, ?)
            """, new Object[]{product.id(), product.name()});
    }

    @Override
    public Optional<Product> getById(Integer id) {
        return jdbcOperations.query("select * from product p where p.id = ?", this, new Object[]{id})
            .stream().findFirst();
    }

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(rs.getObject("id", UUID.class), rs.getString("c_name"));
    }
}
