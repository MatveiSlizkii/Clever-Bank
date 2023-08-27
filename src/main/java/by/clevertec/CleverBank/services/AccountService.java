package by.clevertec.CleverBank.services;

import by.clevertec.CleverBank.dao.AccountStorage;
import by.clevertec.CleverBank.dao.api.IAccountStorage;
import by.clevertec.CleverBank.model.Account;
import by.clevertec.CleverBank.services.api.IAccountService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AccountService implements IAccountService {
    private final IAccountStorage accountStorage = new AccountStorage();
    @Override
    public Account get(UUID uuid) {
        return accountStorage.get(uuid);
    }

    @Override
    public List<Account> gatAll() {

        return accountStorage.getAll();
    }

    @Override
    public Account create(Account account) {
        LocalDateTime ldt = LocalDateTime.now();
        account.setUuid(UUID.randomUUID());
        account.setDbCreate(ldt);
        account.setDbLastUpdate(ldt);
        return accountStorage.create(account);
    }

    @Override
    public Account delete(UUID uuid, LocalDateTime dbLastUpdate) {

        return accountStorage.delete(uuid, dbLastUpdate);
    }

    @Override
    public Account update(Account account, LocalDateTime dbLastUpdate) {
        return accountStorage.update(account, account.getUuid(), dbLastUpdate);
    }
}
