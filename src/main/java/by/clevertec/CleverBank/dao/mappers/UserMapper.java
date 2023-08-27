package by.clevertec.CleverBank.dao.mappers;

import by.clevertec.CleverBank.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserMapper implements IRowMapper<User>{
    @Override
    public User mapRow(ResultSet rs) throws SQLException {

        return User.builder()
                .uuid(rs.getObject("uuid", UUID.class))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .patronymic(rs.getString("patronymic"))
                .dbCreate(rs.getObject("db_create", LocalDateTime.class))
                .dbLastUpdate(rs.getObject("db_last_update", LocalDateTime.class))
                .build();
    }
}
