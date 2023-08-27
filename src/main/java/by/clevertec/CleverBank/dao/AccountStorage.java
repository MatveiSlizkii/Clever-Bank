package by.clevertec.CleverBank.dao;

import by.clevertec.CleverBank.dao.api.ConnectStorage;
import by.clevertec.CleverBank.dao.api.EssenceNotFound;
import by.clevertec.CleverBank.dao.api.IAccountStorage;
import by.clevertec.CleverBank.dao.mappers.AccountMapper;
import by.clevertec.CleverBank.dao.mappers.IRowMapper;
import by.clevertec.CleverBank.dao.mappers.UserMapper;
import by.clevertec.CleverBank.model.Account;
import by.clevertec.CleverBank.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountStorage implements IAccountStorage {

    private final IRowMapper<Account> mapper = new AccountMapper();

    Statement stmt = ConnectStorage.connect();

    @Override
    public Account get(UUID uuid) {
        Account account;
        try {
            String getSql = "SELECT uuid, name, uuid_user, uuid_bank, type_currency, sum, db_create, db_last_update\n" +
                    "\tFROM app.accounts WHERE \"uuid\" = '" + uuid + "';";
            try (ResultSet rs = stmt.executeQuery(getSql)) {
                account = mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            String getAll = "SELECT uuid, name, uuid_user, uuid_bank, type_currency, sum, db_create, db_last_update\n" +
                    "\tFROM app.accounts;";
            try (ResultSet rs = stmt.executeQuery(getAll)) {
                while (rs.next()) {
                    Account account = mapper.mapRow(rs);
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    @Override
    public Account create(Account account) {
        try {
            String insertSql = "INSERT INTO app.accounts(\n" +
                    "\tuuid, name, uuid_user, uuid_bank, type_currency, sum, db_create, db_last_update)" +
                    " VALUES('" + account.getUuid() + "', '" + account.getName() + "', '" +
                    account.getUser() + "', '" + account.getBank() + "', '" +
                    account.getCurrency() + "', '" +
                    account.getDbCreate() + "', '" +
                    account.getDbLastUpdate() + "')";
            stmt.executeUpdate(insertSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    public Account update(Account account, UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound {
        try {
            String update = "UPDATE app.accounts\n" +
                    "\tSET name='" + account.getName() + "', uuid_bank= '" + account.getBank() + "', sum = '"
                    + account.getSum() + "', " +
                    "db_last_update='" + LocalDateTime.now() + "' " +
                    "\tWHERE uuid = '" + uuid + "' AND db_last_update = " + lastUpdate;
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.get(uuid);
    }

    @Override
    public Account delete(UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound {
        Account account = this.get(uuid);
        try {
            String delete = "DELETE FROM app.accounts\n" +
                    "\tWHERE uuid = '" + uuid + "' AND db_last_update = " + lastUpdate;
            stmt.executeUpdate(delete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }
}
