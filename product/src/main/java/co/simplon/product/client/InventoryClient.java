package co.simplon.product.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class InventoryClient {

    private final RestClient restClient;

    public InventoryClient(RestClient.Builder builder, @Value("${inventory.base-url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public void initializeStock(UUID bookId) {
        // Inventory utilise une quantité initiale de 0 par défaut. Le POST ne requiert pas de corps.
        restClient.post()
                .uri("/inventory/{bookId}", bookId)
                .retrieve()
                .toBodilessEntity();
    }
}
