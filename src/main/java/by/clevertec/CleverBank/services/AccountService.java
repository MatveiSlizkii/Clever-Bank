package by.clevertec.CleverBank.services;

import by.clevertec.CleverBank.advice.ExceptionAdvice;
import by.clevertec.CleverBank.dao.AccountStorage;
import by.clevertec.CleverBank.dao.UserStorage;
import by.clevertec.CleverBank.dao.api.IAccountStorage;
import by.clevertec.CleverBank.model.Account;
import by.clevertec.CleverBank.model.enums.TypeCurrency;
import by.clevertec.CleverBank.services.api.IAccountService;
import by.clevertec.CleverBank.services.api.IBankService;
import by.clevertec.CleverBank.services.api.IUserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountService implements IAccountService {
    private final IAccountStorage accountStorage = AccountStorage.getInstance();
    private final IUserService userService = UserService.getInstance();
    private final IBankService bankService = BankService.getInstance();

    private static AccountService instance = null;

    // Приватный конструктор, чтобы запретить создание экземпляров класса извне
    private AccountService() {
        // Дополнительный код для инициализации объекта
    }
    // Статический метод, возвращающий единственный экземпляр класса. Если экземпляр ещё не создан, создаёт его
    public static synchronized AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }


    @Override
    public Account get(UUID uuid) {
        if (!this.isExistByUuid(uuid)) throw new ExceptionAdvice("Account not found");
        return accountStorage.get(uuid);
    }

    @Override
    public List<Account> getAll() {

        return accountStorage.getAll();
    }

    @Override
    public boolean isExistByUuid(UUID uuid) {
        return accountStorage.isExistByUuid(uuid);
    }

    @Override
    public Account create(Account account) {
        List<String> errors = new ArrayList<>();

        if (account.getName() == null) errors.add("You did not enter the field \"name\"");
        if (account.getUser() == null) errors.add("You did not enter the field \"user\"");
        if (account.getBank() == null) errors.add("You did not enter the field \"bank\"");
        if (account.getCurrency() == null) errors.add("You did not enter the field \"currency\"");
        if (account.getSum() == null) errors.add("You did not enter the field \"sum\"");
        if (errors.isEmpty()) throw new ExceptionAdvice(errors);
        if (!userService.isExistByUuid(account.getUser())) errors.add("User not found");
        if (!bankService.isExistByUuid(account.getBank())) errors.add("Bank not found");
        if (account.getSum() < 0) errors.add("You enter the field \"sum\" negative value");
        if (errors.isEmpty()) throw new ExceptionAdvice(errors);

        LocalDateTime ldt = LocalDateTime.now();
        account.setUuid(UUID.randomUUID());
        account.setDbCreate(ldt);
        account.setDbLastUpdate(ldt);


        return accountStorage.create(account);
    }

    @Override
    public Account delete(UUID uuid, LocalDateTime dbLastUpdate) {
        if (!this.isExistByUuid(uuid)) throw new ExceptionAdvice("Account not found");
        return accountStorage.delete(uuid, dbLastUpdate);
    }

    @Override
    public Account update(Account account, LocalDateTime dbLastUpdate) {
        if (!this.isExistByUuid(account.getUuid())) throw new ExceptionAdvice("Account not found");
        return accountStorage.update(account, account.getUuid(), dbLastUpdate);
    }

    @Override
    public Account updateSum(UUID uuid, double newSum, LocalDateTime dbLastUpdate) {
        if (!this.isExistByUuid(uuid)) throw new ExceptionAdvice("Account not found");
        return accountStorage.updateSum(uuid, newSum, dbLastUpdate);
    }
}
