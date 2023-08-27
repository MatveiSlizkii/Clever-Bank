package by.clevertec.CleverBank.dao.mappers;

import by.clevertec.CleverBank.model.Transaction;
import by.clevertec.CleverBank.model.enums.TypeTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionMapper implements IRowMapper<Transaction>{
    @Override
    public Transaction mapRow(ResultSet rs) throws SQLException {

        return Transaction.builder()
                .uuid(rs.getObject("uuid", UUID.class))
                .accountSenders(rs.getObject("account_senders", UUID.class))
                .bankSenders(rs.getObject("bank_senders", UUID.class))
                .accountRecipient(rs.getObject("account_recipient", UUID.class))
                .bankRecipient(rs.getObject("bank_recipient", UUID.class))
                .sum(rs.getDouble("sum"))
                .type(TypeTransaction.valueOf(rs.getString("type")))
                .dbCreate(rs.getObject("db_create", LocalDateTime.class))
                .dbLastUpdate(rs.getObject("db_create", LocalDateTime.class))
                .build();
    }
}
