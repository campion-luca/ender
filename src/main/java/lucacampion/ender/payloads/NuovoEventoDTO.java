package lucacampion.ender.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NuovoEventoDTO(@NotEmpty(message = "Il nome dell'evento è importante e obbligatorio!")
                             @Size(min = 3, max = 50, message = "Il nome deve essere compreso tra 3 e 50!")
                             String nome,
                             @NotEmpty(message = "La descrizione dell'evento è obbligatoria!")
                             @Size(min = 10, max = 200, message = "La descrizione deve essere di minimo 10 caratteri e massimo 200!")
                             String descrizione,
                             @NotEmpty(message = "il prezzo è obbligatorio!")  // nessuna @Size, se sarà zero verrà automaticamente "gratis" o "evento aperto al publico"
                             Double prezzo,
                             @NotNull(message = "La data dell'evento è obbligatoria!")
                             @FutureOrPresent(message = "La data dell'evento deve essere MINIMO oggi o nel futuro!") // PastOrPresent se voglio il contrario
                             @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "La data deve essere nel formato DD/MM/YYYY") // regex per il formato della data
                             LocalDate dataEvento,
                             @NotEmpty(message = "Il luogo dell'evento è assolutamente obbligatorio!")
                             @Size(min = 10, max = 50, message = "Il luogo deve esistere, almeno 10 caratteri e massimo 50!")
                             String luogo,
                             @NotEmpty(message = "L'autore è obbligatorio!")
                             String autore
) {
}