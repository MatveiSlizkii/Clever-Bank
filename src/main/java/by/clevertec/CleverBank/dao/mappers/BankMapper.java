package by.clevertec.CleverBank.dao.mappers;

import by.clevertec.CleverBank.model.Bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class BankMapper implements IRowMapper<Bank>{
    @Override
    public Bank mapRow(ResultSet rs) throws SQLException {

        return Bank.builder()
                .uuid(rs.getObject("uuid", UUID.class))
                .name(rs.getString("name"))
                .dbCreate(rs.getObject("db_create", LocalDateTime.class))
                .dbLastUpdate(rs.getObject("db_last_update", LocalDateTime.class))
                .build();
    }
}
