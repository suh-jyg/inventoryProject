package com.jysuh.inventoryProject;

import com.jysuh.inventoryProject.repository.InventoryRepository;
import com.jysuh.inventoryProject.repository.WarehouseRepository;
import com.jysuh.inventoryProject.service.InventoryService;
import com.jysuh.inventoryProject.service.WarehouseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
  private final InventoryRepository inventoryRepository;
  private final WarehouseRepository warehouseRepository;

  public Config(InventoryRepository inventoryRepository, WarehouseRepository warehouseRepository) {
    this.inventoryRepository = inventoryRepository;
    this.warehouseRepository = warehouseRepository;
  }

  @Bean
  public InventoryService inventoryService() {
    return new InventoryService(inventoryRepository);
  }

  @Bean
  public WarehouseService warehouseService() {
    return new WarehouseService(warehouseRepository, inventoryRepository);
  }
}
