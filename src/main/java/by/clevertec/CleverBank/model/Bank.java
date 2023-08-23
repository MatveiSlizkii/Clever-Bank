package by.clevertec.CleverBank.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Bank {
    private UUID uuid;
    private String name;
    private LocalDateTime dbCreate;
    private LocalDateTime dbLastUpdate;
}
