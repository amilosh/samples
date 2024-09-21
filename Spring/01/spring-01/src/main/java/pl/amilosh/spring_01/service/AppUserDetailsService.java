package pl.amilosh.spring_01.service;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.amilosh.spring_01.model.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.UUID;

@Service
public class AppUserDetailsService extends MappingSqlQuery<User> implements UserDetailsService {

    public AppUserDetailsService(DataSource ds) {
        super(ds, "select * from users where username = :username");
        this.declareParameter(new SqlParameter("username", Types.VARCHAR));
        this.compile();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.executeByNamedParam(Map.of("username", username)).stream().findFirst()
            .orElseThrow(() -> new UsernameNotFoundException("Couldn't find user " + username));
    }

    @Override
    protected User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
            rs.getObject("id", UUID.class),
            rs.getString("username"),
            rs.getString("password"));
    }
}
