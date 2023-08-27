package by.clevertec.CleverBank.model;

import by.clevertec.CleverBank.model.enums.TypeTransaction;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Transaction {
    private UUID uuid;
    private UUID accountSenders;
    private UUID bankSenders;
    private UUID accountRecipient;
    private UUID bankRecipient;
    private Double sum;
    private TypeTransaction type;
    private LocalDateTime dbCreate;
    private LocalDateTime dbLastUpdate;
}
