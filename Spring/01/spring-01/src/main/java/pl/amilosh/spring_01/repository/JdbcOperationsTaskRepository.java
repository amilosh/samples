package pl.amilosh.spring_01.repository;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.amilosh.spring_01.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcOperationsTaskRepository implements TaskRepository, RowMapper<Task> {

    private final JdbcOperations jdbcOperations;

    public JdbcOperationsTaskRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Task> findAll() {
        return jdbcOperations.query("select * from task", this);
    }

    @Override
    public void save(Task task) {
        jdbcOperations.update("""
            insert into task(id, details, completed, user_id) values (?, ?, ?, ?)
            """, new Object[]{task.id(), task.details(), task.completed(), task.userId()});
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return this.jdbcOperations.query("select * from task where id = ?", new Object[]{id}, this)
            .stream().findFirst();
    }

    @Override
    public List<Task> findByUserId(UUID id) {
        return this.jdbcOperations.query("select * from task where user_id = ?", this, id);
    }

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Task(rs.getObject("id", UUID.class),
            rs.getString("details"),
            rs.getBoolean("completed"),
            rs.getObject("user_id", UUID.class));
    }
}
