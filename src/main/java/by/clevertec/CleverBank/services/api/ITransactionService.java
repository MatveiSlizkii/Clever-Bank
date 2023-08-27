package by.clevertec.CleverBank.services.api;

import by.clevertec.CleverBank.model.Transaction;
import by.clevertec.CleverBank.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ITransactionService {

    Transaction get (UUID uuid);
    List<Transaction> gatAll ();
    Transaction create (User user);
    Transaction delete (UUID uuid, LocalDateTime dbLastUpdate);
    Transaction update (Transaction transaction, LocalDateTime dbLastUpdate);
}