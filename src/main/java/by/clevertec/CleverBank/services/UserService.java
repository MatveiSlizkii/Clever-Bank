package by.clevertec.CleverBank.services;

import by.clevertec.CleverBank.dao.UserStorage;
import by.clevertec.CleverBank.dao.api.IUserStorage;
import by.clevertec.CleverBank.model.User;
import by.clevertec.CleverBank.services.api.IUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserService implements IUserService {
    private final IUserStorage userStorage = new UserStorage();



    @Override
    public User get(UUID uuid) {
        User user = userStorage.get(uuid);
        return user;
    }

    @Override
    public List<User> getAll() {
        return userStorage.gatAll();
    }

    @Override
    public Boolean isExist(UUID uuid) {
        return userStorage.existByUuid(uuid);
    }

    @Override
    public User create(User user) {
        LocalDateTime ldt = LocalDateTime.now();
        user.setUuid(UUID.randomUUID());
        user.setDbCreate(ldt);
        user.setDbLastUpdate(ldt);
        userStorage.create(user);

        return user;
    }

    @Override
    public User delete(UUID uuid, LocalDateTime dbLastUpdate) {
        User user = userStorage.delete(uuid, dbLastUpdate);
        return user;
    }

    @Override
    public User update(User user, LocalDateTime dbLastUpdate) {

        User user1 = userStorage.update(user, user.getUuid(), dbLastUpdate);
        return user1;
    }
}
