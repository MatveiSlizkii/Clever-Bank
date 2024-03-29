package by.clevertec.CleverBank.dao;

import by.clevertec.CleverBank.dao.api.ConnectStorage;
import by.clevertec.CleverBank.dao.api.EssenceNotFound;
import by.clevertec.CleverBank.dao.api.IBankStorage;
import by.clevertec.CleverBank.dao.mappers.BankMapper;
import by.clevertec.CleverBank.dao.mappers.IRowMapper;
import by.clevertec.CleverBank.model.Bank;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankStorage implements IBankStorage {


    private static BankStorage instance = null;

    // Приватный конструктор, чтобы запретить создание экземпляров класса извне
    private BankStorage() {
        // Дополнительный код для инициализации объекта
    }
    // Статический метод, возвращающий единственный экземпляр класса. Если экземпляр ещё не создан, создаёт его
    public static synchronized BankStorage getInstance() {
        if (instance == null) {
            instance = new BankStorage();
        }
        return instance;
    }
    private final IRowMapper<Bank> mapper = new BankMapper();

    private final Statement stmt = ConnectStorage.connect();

    @Override
    public Bank get(UUID uuid) {
        Bank bank;
        try {
            String getSql = "SELECT uuid, name, db_create, db_last_update\n" +
                    "\tFROM app.banks WHERE \"uuid\" = '" + uuid + "';";
            try (ResultSet rs = stmt.executeQuery(getSql)) {
                     bank = mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bank;
    }

    @Override
    public List<Bank> getAll() {
        List<Bank> banks = new ArrayList<>();
        try {
            String getAll = "SELECT uuid, name, db_create, db_last_update\n" +
                    "\tFROM app.banks";
            try (ResultSet rs = stmt.executeQuery(getAll)) {
                while (rs.next()) {
                    Bank bank = mapper.mapRow(rs);
                    banks.add(bank);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return banks;
    }
    @Override
    public boolean isExistByUuid(UUID uuid) {
        boolean result = false;
        try {
            String insertSql = "SELECT EXISTS (\n" +
                    "    SELECT 1\n" +
                    "    FROM app.banks\n" +
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
    public Bank create(Bank bank) {

        try {
            String insertSql = "INSERT INTO app.banks(uuid, name, db_create, db_last_update)"
                    + " VALUES('" + bank.getUuid() + "', '" + bank.getName() + "', '" + bank.getDbCreate()+"', '" +
                    bank.getDbLastUpdate()+"');";
            System.out.println(insertSql);
            stmt.executeUpdate(insertSql);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create entity \n" + e);
        }
        return bank;
    }

    @Override
    public Bank update(Bank bank, UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound {
        try {
            String update = "UPDATE app.banks\n" +
                    "\tSET name='" + bank.getName() + "'," +
                    ", db_last_update='" + LocalDateTime.now() + "' " +
                    "\tWHERE uuid = '" + uuid + "' AND db_last_update = '" + lastUpdate +"';";
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            throw new RuntimeException("The requested version is outdated");
        }
        return this.get(uuid);
    }

    @Override
    public Bank delete(UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound {
        Bank bank = this.get(uuid);
        try {
            String delete = "DELETE FROM app.banks\n" +
                    "\tWHERE uuid = '" + uuid + "' AND db_last_update = '" + lastUpdate +"';";
            stmt.executeUpdate(delete);
        } catch (SQLException e) {
            throw new RuntimeException("The requested version is outdated");
        }
        return bank;
    }
}
