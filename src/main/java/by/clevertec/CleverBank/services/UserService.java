package by.clevertec.CleverBank.services;

import by.clevertec.CleverBank.advice.ExceptionAdvice;
import by.clevertec.CleverBank.dao.UserStorage;
import by.clevertec.CleverBank.dao.api.IUserStorage;
import by.clevertec.CleverBank.model.User;
import by.clevertec.CleverBank.services.api.IUserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService implements IUserService {
    private final IUserStorage userStorage = new UserStorage();



    @Override
    public User get(UUID uuid) {
        if (!this.isExistByUuid(uuid)) throw new ExceptionAdvice("User not found");

        return userStorage.get(uuid);
    }

    @Override
    public List<User> getAll() {
        return userStorage.gatAll();
    }

    @Override
    public Boolean isExistByUuid(UUID uuid) {
        return userStorage.isExistByUuid(uuid);
    }

    @Override
    public User create(User user) {
        List<String> errors = new ArrayList<>();
        if (user.getName() == null) errors.add("You did not enter the field \"Name\"");
        if (user.getSurname() == null) errors.add("You did not enter the field \"Surname\"");
        if (user.getPatronymic() == null) errors.add("You did not enter the field \"Patronymic\"");
        if (errors.isEmpty()) throw new ExceptionAdvice(errors);

        LocalDateTime ldt = LocalDateTime.now();
        user.setUuid(UUID.randomUUID());
        user.setDbCreate(ldt);
        user.setDbLastUpdate(ldt);
        userStorage.create(user);

        return user;
    }

    @Override
    public User delete(UUID uuid, LocalDateTime dbLastUpdate) {
        if (!this.isExistByUuid(uuid)) throw new ExceptionAdvice("User not found");
        User user = userStorage.delete(uuid, dbLastUpdate);
        return user;
    }

    @Override
    public User update(User user, LocalDateTime dbLastUpdate) {
        if (!isExistByUuid(user.getUuid())){
            throw new ExceptionAdvice("User not found");
        }

        User user1 = userStorage.update(user, user.getUuid(), dbLastUpdate);
        return user1;
    }
}
