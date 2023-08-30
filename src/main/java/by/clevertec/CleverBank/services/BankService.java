package by.clevertec.CleverBank.services;

import by.clevertec.CleverBank.advice.ExceptionAdvice;
import by.clevertec.CleverBank.dao.BankStorage;
import by.clevertec.CleverBank.dao.api.IBankStorage;
import by.clevertec.CleverBank.model.Bank;
import by.clevertec.CleverBank.model.User;
import by.clevertec.CleverBank.services.api.IBankService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BankService implements IBankService {
    private final IBankStorage bankStorage = BankStorage.getInstance();

    private static BankService instance = null;

    // Приватный конструктор, чтобы запретить создание экземпляров класса извне
    private BankService() {
        // Дополнительный код для инициализации объекта
    }
    // Статический метод, возвращающий единственный экземпляр класса. Если экземпляр ещё не создан, создаёт его
    public static synchronized BankService getInstance() {
        if (instance == null) {
            instance = new BankService();
        }
        return instance;
    }

    @Override
    public Bank get(UUID uuid) {
        if (!this.isExistByUuid(uuid)) throw new ExceptionAdvice("Bank not found");
        return bankStorage.get(uuid);
    }

    @Override
    public List<Bank> getAll() {
        return bankStorage.getAll();
    }

    @Override
    public boolean isExistByUuid(UUID uuid) {
        return bankStorage.isExistByUuid(uuid);
    }

    @Override
    public Bank create(Bank bank) {

        if (bank.getName() == null) throw new ExceptionAdvice("You did not enter the field \"name\"");
        System.out.println(bank.getName() == null);
        LocalDateTime ldt = LocalDateTime.now();

        bank.setUuid(UUID.randomUUID());
        bank.setDbCreate(ldt);
        bank.setDbLastUpdate(ldt);
        bankStorage.create(bank);

        return bank;
    }

    @Override
    public Bank delete(UUID uuid, LocalDateTime dbLastUpdate) {
        if (!this.isExistByUuid(uuid)) throw new ExceptionAdvice("Bank not found");
        return bankStorage.delete(uuid, dbLastUpdate);
    }

    @Override
    public Bank update(Bank bank, LocalDateTime dbLastUpdate) {
        if (!isExistByUuid(bank.getUuid())){
            throw new ExceptionAdvice("Bank not found");
        }
        return bankStorage.update(bank, bank.getUuid(), dbLastUpdate);
    }
}
