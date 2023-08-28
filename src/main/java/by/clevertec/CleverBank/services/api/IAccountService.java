package by.clevertec.CleverBank.services.api;

import by.clevertec.CleverBank.model.Account;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IAccountService {

    Account get (UUID uuid);
    List<Account> getAll();
    boolean isExistByUuid (UUID uuid);

    Account create (Account account);
    Account delete (UUID uuid, LocalDateTime dbLastUpdate);
    Account update (Account account, LocalDateTime dbLastUpdate);

    Account updateSum (UUID uuid, double newSum, LocalDateTime dbLastUpdate);
}
