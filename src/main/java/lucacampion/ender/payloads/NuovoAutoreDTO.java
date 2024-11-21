package lucacampion.ender.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NuovoAutoreDTO(@NotEmpty(message = "Il nome dell'autore è importante e obbligatorio!")
                             @Size(min = 3, max = 50, message = "Il nome deve essere compreso tra 3 e 50!")
                             String nome
) {
}
