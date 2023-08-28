package by.clevertec.CleverBank.dao.api;

import by.clevertec.CleverBank.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IUserStorage extends ICUDRepository<User, UUID> {
    User get (UUID uuid);

    List<User> gatAll ();

    boolean isExistByUuid(UUID uuid);

    @Override
    User create(User item);

    @Override
    User update(User item, UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound;

    @Override
    User delete(UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound;
}
