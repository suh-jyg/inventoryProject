package com.jysuh.inventoryProject;

import com.jysuh.inventoryProject.repository.InventoryRepository;
import com.jysuh.inventoryProject.service.InventoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
  private final InventoryRepository inventoryRepository;

  public Config(InventoryRepository inventoryRepository) {
    this.inventoryRepository = inventoryRepository;
  }

  @Bean
  public InventoryService inventoryService() {
    return new InventoryService(inventoryRepository);
  }
}
