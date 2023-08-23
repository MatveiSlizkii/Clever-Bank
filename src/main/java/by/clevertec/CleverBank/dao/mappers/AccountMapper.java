package by.clevertec.CleverBank.dao.mappers;

import by.clevertec.CleverBank.model.Account;
import by.clevertec.CleverBank.model.Bank;
import by.clevertec.CleverBank.model.enums.TypeCurrency;
import by.clevertec.CleverBank.model.enums.TypeTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class AccountMapper implements IRowMapper<Account>{
    @Override
    public Account mapRow(ResultSet rs) throws SQLException {

        return Account.builder()
                .uuid(rs.getObject("uuid", UUID.class))
                .name(rs.getLong("name"))
                .user(rs.getObject("user", UUID.class))
                .bank(rs.getObject("bank", UUID.class))
                .currency(TypeCurrency.valueOf(rs.getString("currency")))
                .sum(rs.getDouble("sum"))
                .dbCreate(rs.getObject("db_create", LocalDateTime.class))
                .dbLastUpdate(rs.getObject("db_last_update", LocalDateTime.class))
                .build();
    }
}
