package com.jysuh.inventoryProject.service;

import com.jysuh.inventoryProject.entity.Inventory;
import com.jysuh.inventoryProject.repository.InventoryRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class InventoryService {
  private final InventoryRepository inventoryRepository;

  public InventoryService(InventoryRepository inventoryRepository) {
    this.inventoryRepository = inventoryRepository;
  }

  @Transactional
  public Integer create(Inventory inventory) {
    inventoryRepository.save(inventory);
    return inventory.getId();
  }

  @Transactional
  public void updateInventory(Integer inventoryId, String name, String type, String desc) {
    Optional<Inventory> inventory = inventoryRepository.findById(inventoryId);
    inventory.ifPresent(i -> i.setName(name));
    inventory.ifPresent(i -> i.setType(type));
    inventory.ifPresent(i -> i.setDescription(desc));
  }

  @Transactional
  public void deleteInventoryById(Integer inventoryId) {
    inventoryRepository.deleteById(inventoryId);
  }

  public List<Inventory> findInventories() {
    return inventoryRepository.findAll();
  }

  public Inventory findById(Integer inventoryId) {
    Optional<Inventory> optional = inventoryRepository.findById(inventoryId);
    Inventory inventory = null;
    if (optional.isPresent()) {
      inventory = optional.get();
    } else {
      throw new RuntimeException(" Inventory not found for id :: " + inventoryId);
    }
    return inventory;
  }

}
