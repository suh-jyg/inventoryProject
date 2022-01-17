package com.jysuh.inventoryProject.service;

import com.jysuh.inventoryProject.controller.InventoryForm;
import com.jysuh.inventoryProject.entity.Inventory;
import com.jysuh.inventoryProject.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class InventoryService {
  private final InventoryRepository inventoryRepository;

  // Create the inventory through the builder
  @Transactional
  public Inventory createInventory(InventoryForm inventoryForm) {
    Inventory newInventory = Inventory.builder()
            .name(inventoryForm.getName())
            .type(inventoryForm.getType())
            .description(inventoryForm.getDescription())
            .build();
    inventoryRepository.save(newInventory);
    return newInventory;
  }

  // Update the inventory by the input variables
  @Transactional
  public void updateInventory(Integer inventoryId, String name, String type, String desc) {
    Inventory inventory = returnInventory(inventoryRepository.findById(inventoryId));
    inventory.setName(name);
    inventory.setType(type);
    inventory.setDescription(desc);

  }

  // Delete the inventory
  @Transactional
  public void deleteInventoryById(Integer inventoryId) {
    inventoryRepository.deleteById(inventoryId);
  }

  public List<Inventory> findInventories() {
    return inventoryRepository.findAll();
  }

  public Inventory findById(Integer inventoryId) {
    Optional<Inventory> optional = inventoryRepository.findById(inventoryId);
    Inventory inventory = returnInventory(optional);
    return inventory;
  }

  // Since JPA returns the Objects as optional, remove it
  public Inventory returnInventory(Optional<Inventory> optionalInv) {
    Inventory inventory = null;
    if (optionalInv.isPresent()) {
      inventory = optionalInv.get();
    } else {
      throw new RuntimeException(" No ID found");
    }
    return inventory;
  }


}
