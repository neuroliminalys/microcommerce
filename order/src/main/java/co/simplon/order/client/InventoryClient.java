package co.simplon.order.client;

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

    public void initializeStock(UUID productId) {
        // Inventory utilise une quantité initiale de 0 par défaut. Le POST ne requiert
        // pas de corps.
        restClient.post()
                .uri("/inventory/{productId}", productId)
                .retrieve()
                .toBodilessEntity();
    }

    public Integer getStock(UUID productId) {
        return restClient.get()
                .uri("/inventory/{productId}", productId)
                .retrieve()
                .body(Integer.class);
    }

    public void updateStock(UUID productId, int qty) {
        restClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("/inventory/{productId}")
                        .queryParam("newQuantity", qty)
                        .build(productId))
                .retrieve()
                .toBodilessEntity();
    }
}
