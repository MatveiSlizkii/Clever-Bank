package by.clevertec.CleverBank.dao;

import by.clevertec.CleverBank.advice.ExceptionAdvice;
import by.clevertec.CleverBank.dao.api.ConnectStorage;
import by.clevertec.CleverBank.dao.api.EssenceNotFound;
import by.clevertec.CleverBank.dao.api.ITransactionStorage;
import by.clevertec.CleverBank.dao.mappers.IRowMapper;
import by.clevertec.CleverBank.dao.mappers.TransactionMapper;
import by.clevertec.CleverBank.model.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionStorage implements ITransactionStorage {

    private static TransactionStorage instance = null;

    // Приватный конструктор, чтобы запретить создание экземпляров класса извне
    private TransactionStorage() {
        // Дополнительный код для инициализации объекта
    }
    // Статический метод, возвращающий единственный экземпляр класса. Если экземпляр ещё не создан, создаёт его
    public static synchronized TransactionStorage getInstance() {
        if (instance == null) {
            instance = new TransactionStorage();
        }
        return instance;
    }


    private final IRowMapper<Transaction> mapper = new TransactionMapper();

    private final Statement stmt = ConnectStorage.connect();

    @Override
    public Transaction get(UUID uuid) {
        Transaction transaction;
        try {
            String getSql = "SELECT uuid, uuid_account_senders, uuid_bank_senders," +
                    " uuid_account_recipient, uuid_bank_recipient, sum, db_create, type_transaction, db_last_update" +
                    "\tFROM app.transactions WHERE \"uuid\" = '" + uuid + "';";
            try (ResultSet rs = stmt.executeQuery(getSql)) {
                transaction = mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transaction;
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String getAll = "SELECT uuid, uuid_account_senders, uuid_bank_senders," +
                    " uuid_account_recipient, uuid_bank_recipient, sum, db_create, type_transaction, db_last_update" +
                    "\tFROM app.transactions;";
            try (ResultSet rs = stmt.executeQuery(getAll)) {
                while (rs.next()) {
                    Transaction transaction = mapper.mapRow(rs);
                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }
    @Override
    public boolean isExistByUuid(UUID uuid) {
        boolean result = false;
        try {
            String insertSql = "SELECT EXISTS (\n" +
                    "    SELECT 1\n" +
                    "    FROM app.transactions\n" +
                    "    WHERE uuid = '" + uuid + "'\n" +
                    ");";
            try (ResultSet rs = stmt.executeQuery(insertSql)) {
                while (rs.next()) {
                    result =  rs.getObject("exists", Boolean.class);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Transaction create(Transaction transaction) {
        try {
            String insertSql = "INSERT INTO app.transactions(\n" +
                    "\tuuid, uuid_account_senders, uuid_bank_senders," +
                    " uuid_account_recipient, uuid_bank_recipient, sum, db_create, type_transaction, db_last_update" +
                    " VALUES('" + transaction.getUuid() + "', '" + transaction.getAccountSenders() + "', '" +
                    transaction.getBankSenders() + "', '" + transaction.getAccountRecipient() + "', '" +
                    transaction.getBankRecipient() + "', '" +
                    transaction.getSum() + "', '" +
                    transaction.getDbCreate() + "', '" +
                    transaction.getType() + "', '" +
                    transaction.getDbLastUpdate() + "')";
            stmt.executeUpdate(insertSql);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create entity \n" + e);
        }
        return transaction;
    }

    @Override
    public Transaction update(Transaction transaction, UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound {
        throw new ExceptionAdvice("This entity cannot be updated");
    }

    @Override
    public Transaction delete(UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound {
        Transaction transaction = this.get(uuid);
        try {
            String delete = "DELETE FROM app.transactions" +
                    "\tWHERE uuid = '" + uuid + "' AND db_last_update = " + lastUpdate;
            stmt.executeUpdate(delete);
        } catch (SQLException e) {
            throw new RuntimeException("The requested version is outdated");
        }
        return transaction;
    }

    @Override
    public List<Transaction> getAllByAccount() {
        return null;
    }

    @Override
    public List<Double> getSumSumByUuidAccountSenders(UUID uuid) {
        return null;
    }

    @Override
    public List<Double> getSumSumByUuidAccountRecipient(UUID uuid) {
        return null;
    }
}
