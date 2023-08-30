package by.clevertec.CleverBank.services;

import by.clevertec.CleverBank.advice.ExceptionAdvice;
import by.clevertec.CleverBank.dao.TransactionStorage;
import by.clevertec.CleverBank.dao.api.ITransactionStorage;
import by.clevertec.CleverBank.model.Account;
import by.clevertec.CleverBank.model.Transaction;
import by.clevertec.CleverBank.model.enums.TypeTransaction;
import by.clevertec.CleverBank.services.api.IAccountService;
import by.clevertec.CleverBank.services.api.IBankService;
import by.clevertec.CleverBank.services.api.ITransactionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService implements ITransactionService {
    private final ITransactionStorage transactionStorage = TransactionStorage.getInstance();
    private final IAccountService accountService = AccountService.getInstance();
    private final IBankService bankService = BankService.getInstance();


    private static TransactionService instance = null;

    // Приватный конструктор, чтобы запретить создание экземпляров класса извне
    private TransactionService() {
        // Дополнительный код для инициализации объекта
    }
    // Статический метод, возвращающий единственный экземпляр класса. Если экземпляр ещё не создан, создаёт его
    public static synchronized TransactionService getInstance() {
        if (instance == null) {
            instance = new TransactionService();
        }
        return instance;
    }

    @Override
    public Transaction get(UUID uuid) {
        if (!this.isExistByUuid(uuid)) throw new ExceptionAdvice("Transaction not found");
        return transactionStorage.get(uuid);
    }

    @Override
    public List<Transaction> getAll() {
        return transactionStorage.getAll();
    }

    @Override
    public boolean isExistByUuid(UUID uuid) {
        return transactionStorage.isExistByUuid(uuid);
    }

    @Override
    public Transaction create(Transaction transaction) {

        List<String> errors = new ArrayList<>();
        if (transaction.getAccountSenders() != null && !accountService.isExistByUuid(transaction.getAccountSenders()))
            errors.add("AccountSenders not found");
        if (transaction.getBankSenders() != null && !bankService.isExistByUuid(transaction.getBankSenders()))
            errors.add("BankSenders not found");
        if (transaction.getAccountRecipient() != null && !accountService.isExistByUuid(transaction.getAccountRecipient()))
            errors.add("AccountRecipient not found");
        if (transaction.getBankRecipient() != null && !bankService.isExistByUuid(transaction.getBankRecipient()))
            errors.add("BankRecipient not found");
        if (transaction.getSum() < 0) errors.add("You enter the field \"sum\" negative value");
        if (errors.isEmpty()) throw new ExceptionAdvice(errors);


        LocalDateTime ldt = LocalDateTime.now();
        transaction.setUuid(UUID.randomUUID());
        transaction.setDbCreate(ldt);
        transaction.setDbLastUpdate(ldt);
        //TODO правило снятия денежных средств
        if (TypeTransaction.TRANSFER.equals(transaction.getType())) {
            //Проверили входные данные
            if (transaction.getAccountSenders() == null) errors.add("AccountSenders not found");
            if (transaction.getBankSenders() == null) errors.add("BankSenders not found");
            if (transaction.getAccountRecipient() == null) errors.add("AccountRecipient not found");
            if (transaction.getBankRecipient() == null) errors.add("BankRecipient not found");
            if (errors.isEmpty()) throw new ExceptionAdvice(errors);

            //получаем суммы со счетов
            Account accountSenders = accountService.get(transaction.getAccountSenders());
            Account accountRecipient = accountService.get(transaction.getAccountRecipient());
            Double sumSenders = accountSenders.getSum();
            Double sumRecipient = accountRecipient.getSum();
            // Проверка не уйдет ли в минус отправитель
            double deltaSumSenders = sumSenders - transaction.getSum();
            double deltaSumRecipient = sumRecipient + transaction.getSum();
            if (deltaSumSenders < 0) throw new ExceptionAdvice("Insufficient balance");
            //меняем значения в балансах счетов
            accountService.updateSum(accountSenders.getUuid(), deltaSumSenders, accountSenders.getDbLastUpdate());
            accountService.updateSum(accountRecipient.getUuid(), deltaSumRecipient, accountRecipient.getDbLastUpdate());
            return transactionStorage.create(transaction);
        }
        if (TypeTransaction.DEPOSIT.equals(transaction.getType())) {
            //обнуляем данные про отправителя если были
            transaction.setAccountSenders(null);
            transaction.setBankSenders(null);
            //проверка данных получателя
            if (transaction.getAccountRecipient() == null) errors.add("AccountRecipient not found");
            if (transaction.getBankRecipient() == null) errors.add("BankRecipient not found");
            //меняем значения баланса
            Account accountRecipient = accountService.get(transaction.getAccountRecipient());
            Double sumRecipient = accountRecipient.getSum();
            double deltaSumRecipient = sumRecipient + transaction.getSum();
            accountService.updateSum(accountRecipient.getUuid(), deltaSumRecipient, accountRecipient.getDbLastUpdate());
            return transactionStorage.create(transaction);
        }
        if (TypeTransaction.WITHDRAW.equals(transaction.getType())) {
            //обнуляем данные про получателя если были
            transaction.setAccountRecipient(null);
            transaction.setBankRecipient(null);
            //проверка данных отправителя
            if (transaction.getAccountSenders() == null) errors.add("AccountSenders not found");
            if (transaction.getBankSenders() == null) errors.add("BankSenders not found");
            //меняем значения баланса
            Account accountSenders = accountService.get(transaction.getAccountSenders());
            Double sumSenders = accountSenders.getSum();
            double deltaSumSenders = sumSenders - transaction.getSum();
            if (deltaSumSenders < 0) throw new ExceptionAdvice("Insufficient balance");
            //меняем значения в балансах счетов
            accountService.updateSum(accountSenders.getUuid(), deltaSumSenders, accountSenders.getDbLastUpdate());
            return transactionStorage.create(transaction);
        }
        return transactionStorage.create(transaction);
    }

    @Override
    public Transaction delete(UUID uuid, LocalDateTime dbLastUpdate) {
        if (!this.isExistByUuid(uuid)) throw new ExceptionAdvice("Transaction not found");
        return transactionStorage.delete(uuid, dbLastUpdate);
    }

    @Override
    public Transaction update(Transaction transaction, LocalDateTime dbLastUpdate) {
        throw new ExceptionAdvice("This entity cannot be updated");
    }

}
