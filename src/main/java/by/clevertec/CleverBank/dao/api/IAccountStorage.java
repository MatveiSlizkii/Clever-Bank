package by.clevertec.CleverBank.dao.api;

import by.clevertec.CleverBank.model.Account;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IAccountStorage extends ICUDRepository<Account, UUID> {

    Account get (UUID uuid);
    List<Account> getAll();


    @Override
    Account create(Account account);

    @Override
    Account update(Account account, UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound;

    @Override
    Account delete(UUID uuid, LocalDateTime lastUpdate) throws EssenceNotFound;
}
