package by.clevertec.CleverBank.model;

import by.clevertec.CleverBank.model.enums.TypeCurrency;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Account {
    private UUID uuid;
    private Long name;
    private UUID user;
    private UUID bank;
    private TypeCurrency currency;
    private Double sum;
    private LocalDateTime dbCreate;
    private LocalDateTime dbLastUpdate;

}
