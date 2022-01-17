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
    public Integer create(Inventory inventory){
        checkDuplicatedInventory(inventory);
        inventoryRepository.save(inventory);
        return inventory.getId();
    }

    @Transactional
    public void updateInventory(Integer inventoryId, String name, int price, int quantity) {
        Optional<Inventory> foundInventory = inventoryRepository.findById(inventoryId);
        foundInventory.ifPresent(inventory -> inventory.setName(name));
        foundInventory.ifPresent(inventory -> inventory.setPrice(price));
        foundInventory.ifPresent(inventory -> inventory.setQuantity(quantity));
    }

    private void checkDuplicatedInventory(Inventory inventory) {
        if (inventoryRepository.findByName(inventory.getName()).isPresent() == true){
            throw new IllegalStateException("Inventory with same name already exists");
        }
    }

    public List<Inventory> findInventories(){
        return inventoryRepository.findAll();
    }

    public Inventory findById(Integer inventoryId){
        Optional<Inventory> optional = inventoryRepository.findById(inventoryId);
        Inventory inventory = null;
        if (optional.isPresent()) {
            inventory = optional.get();
        }
        else {
            throw new RuntimeException(" Inventory not found for id :: " + inventoryId);
        }
        return inventory;
    }

    public void deleteInventoryById(Integer inventoryId) {
        inventoryRepository.deleteById(inventoryId);
    }
}