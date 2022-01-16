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

    private void checkDuplicatedInventory(Inventory inventory) {
        if (inventoryRepository.findByName(inventory.getName()).isPresent() == true){
            throw new IllegalStateException("Inventory with same name already exists");
        }
    }

    public List<Inventory> findInventories(){
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> findInventoryByID(Inventory inventory){
        return inventoryRepository.findById(inventory.getId());
    }
}