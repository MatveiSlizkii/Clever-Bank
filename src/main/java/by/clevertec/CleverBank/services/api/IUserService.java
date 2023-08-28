package by.clevertec.CleverBank.services.api;

import by.clevertec.CleverBank.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IUserService {

    User get (UUID uuid);
    List<User> getAll();

    Boolean isExistByUuid(UUID uuid);
    User create (User user);
    User delete (UUID uuid, LocalDateTime dbLastUpdate);
    User update (User user, LocalDateTime dbLastUpdate);
}
