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
public class User {
    private UUID uuid;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDateTime dbCreate;
    private LocalDateTime dbLastUpdate;
}
