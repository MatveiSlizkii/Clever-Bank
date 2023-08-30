package by.clevertec.CleverBank.dao;

import by.clevertec.CleverBank.dao.api.ConnectStorage;
import by.clevertec.CleverBank.dao.api.EssenceNotFound;
import by.clevertec.CleverBank.dao.api.IUserStorage;
import by.clevertec.CleverBank.dao.mappers.IRowMapper;
import by.clevertec.CleverBank.dao.mappers.UserMapper;
import by.clevertec.CleverBank.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserStorage implements IUserStorage {
    private static UserStorage instance = null;

    // Приватный конструктор, чтобы запретить создание экземпляров класса извне
    private UserStorage() {
        // Дополнительный код для инициализации объекта
    }
    // Статический метод, возвращающий единственный экземпляр класса. Если экземпляр ещё не создан, создаёт его
    public static synchronized UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }




    private final IRowMapper<User> mapper = new UserMapper();

    Statement stmt = ConnectStorage.connect();

    @Override
    public User get(UUID uuid) {
        User user = null;
        try {
            String getSql = "SELECT uuid, name, db_create, db_last_update, surname, patronymic " +
                    "FROM app.users WHERE \"uuid\" = '" + uuid + "';";
            try (ResultSet rs = stmt.executeQuery(getSql)) {
                while (rs.next()) {
                    user = mapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<User> gatAll() {
        List<User> users = new ArrayList<>();
        try {
            String getAll = "SELECT uuid, name, db_create, db_last_update, surname, patronymic\n" +
                    "\tFROM app.users;";
            try (ResultSet rs = stmt.executeQuery(getAll)) {
                while (rs.next()) {
                    User user = mapper.mapRow(rs);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public boolean isExistByUuid(UUID uuid) {
        boolean result = false;
        try {
            String insertSql = "SELECT EXISTS (\n" +
                    "    SELECT 1\n" +
                    "    FROM app.users\n" +
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
    public User create(User user) {
        try {
            String insertSql = "INSERT INTO app.users(\n" +
                    "\tuuid, name, surname, patronymic, db_create, db_last_update)"
                    + " VALUES('" + user.getUuid() + "', '" + user.getName() + "', '" +
                    user.getSurname() + "', '" + user.getPatronymic() + "', '" +
                    user.getDbCreate() + "', '" +
                    user.getDbLastUpdate() + "')";
            stmt.executeUpdate(insertSql);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create entity \n" + e);
        }
        return user;
    }

    @Override
    public User update(User user, UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound {
        try {
            String update = "UPDATE app.users\n" +
                    "\tSET name='" + user.getName() + "', surname= '" + user.getSurname() + "', patronymic = '"
                    + user.getPatronymic() + "', " +
                    "db_last_update='" + LocalDateTime.now() + "' " +
                    "\tWHERE uuid = '" + uuid + "' AND db_last_update = '" + lastUpdate + "';";
            System.out.println(update);
            stmt.executeUpdate(update);
        } catch (SQLException e) {
            throw new RuntimeException("The requested version is outdated");
        }
        return this.get(uuid);
    }

    @Override
    public User delete(UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound {
        User user = this.get(uuid);
        try {
            String delete = "DELETE FROM app.users\n" +
                    "\tWHERE uuid = '" + uuid + "' AND db_last_update = '" + lastUpdate + "';";
            stmt.executeUpdate(delete);
        } catch (SQLException e) {
            throw new RuntimeException("The requested version is outdated");
        }
        return user;
    }
}
