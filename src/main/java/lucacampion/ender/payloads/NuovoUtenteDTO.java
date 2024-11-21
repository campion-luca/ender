package lucacampion.ender.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NuovoUtenteDTO(@NotEmpty(message = "Il nome è obbligatorio!")
                             @Size(min = 3, max = 20, message = "Il nome deve essere compreso tra 3 e 20!")
                             String nome,
                             @NotEmpty(message = "Il cognome è obbligatorio!")
                             @Size(min = 3, max = 30, message = "Il cognome deve essere compreso tra 3 e 30!")
                             String cognome,
                             @NotEmpty(message = "l'email è obbligatoria!")
                             @Email(message = "L'email inserita non è un'email valida")
                             String email,
                             @NotEmpty(message = "Il nickname è obbligatorio!")
                             @Size(min = 3, max = 20, message = "Il nickname deve essere compreso tra 3 e 20!")
                             String nickname,
                             @NotEmpty(message = "La password è obbligatoria!")
                             @Size(min = 8, max = 30, message = "Il cognome deve essere compreso tra 8 e 30!")
                             @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                                     message = "La password deve contenere almeno una lettera maiuscola, una lettera minuscola, un numero e un carattere speciale!")
                             String pasword
) {
}
