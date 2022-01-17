package com.jysuh.inventoryProject;

import com.jysuh.inventoryProject.repository.InventoryRepository;
import com.jysuh.inventoryProject.repository.LocationRepository;
import com.jysuh.inventoryProject.service.InventoryService;
import com.jysuh.inventoryProject.service.LocationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
  private final InventoryRepository inventoryRepository;
  private final LocationRepository locationRepository;

  public Config(InventoryRepository inventoryRepository, LocationRepository locationRepository) {
    this.inventoryRepository = inventoryRepository;
    this.locationRepository = locationRepository;
  }

  @Bean
  public InventoryService inventoryService() {
    return new InventoryService(inventoryRepository);
  }

  @Bean
  public LocationService locationService() {
    return new LocationService(locationRepository, inventoryRepository);
  }
}
