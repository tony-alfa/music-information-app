package com.expd.pricing;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NetworkPricingProvider implements  PricingProvider{

   private RestClient restClient;

   public NetworkPricingProvider() {
      this.restClient = RestClient.builder()
              .baseUrl("http://localhost:8081")
              .defaultHeader("Accept","application/json")
              .defaultHeader("Content-Type","application/json")
              .build();
   }

   @Override
   public int getPricing(int id) {
      ResponseEntity<Integer> result =  restClient.get()
              .uri("/api/pricing/{id}", 1)
              .retrieve()
              .toEntity(Integer.class);


      return result.getBody();
   }
}
