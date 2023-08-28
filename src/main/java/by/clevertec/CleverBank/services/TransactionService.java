package by.clevertec.CleverBank.services;

import by.clevertec.CleverBank.advice.ExceptionAdvice;
import by.clevertec.CleverBank.dao.TransactionStorage;
import by.clevertec.CleverBank.dao.api.ITransactionStorage;
import by.clevertec.CleverBank.model.Transaction;
import by.clevertec.CleverBank.services.api.IAccountService;
import by.clevertec.CleverBank.services.api.IBankService;
import by.clevertec.CleverBank.services.api.ITransactionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService implements ITransactionService {
    private final ITransactionStorage transactionStorage = new TransactionStorage();
    private final IAccountService accountService = new AccountService();
    private final IBankService bankService = new BankService();

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
        if (errors.isEmpty()) throw new ExceptionAdvice(errors);


        LocalDateTime ldt = LocalDateTime.now();
        transaction.setUuid(UUID.randomUUID());
        transaction.setDbCreate(ldt);
        transaction.setDbLastUpdate(ldt);
        //TODO правило снятия денежных средств






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
