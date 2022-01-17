package com.jysuh.inventoryProject.service;

import com.jysuh.inventoryProject.controller.InventoryForm;
import com.jysuh.inventoryProject.entity.Inventory;
import com.jysuh.inventoryProject.repository.InventoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class InventoryServiceTest {
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    InventoryService inventoryService;

    @Test
    void postInventory() {
        // Create an inventory and save
        InventoryForm inventoryForm = new InventoryForm();
        Inventory inventory = inventoryService.createInventory(inventoryForm);

        // Retrieve an inventory from the repository
        Inventory foundInventory = inventoryRepository.findById(inventory.getId()).get();

        // Check if they are equal
        Assertions.assertEquals(inventory.getName(), foundInventory.getName());
    }

    @Test
    void editInventory() {
        // Create inventory 1 and save
        InventoryForm inventoryForm1 = new InventoryForm();
        inventoryForm1.setName("test1");
        Inventory inventory1 = inventoryService.createInventory(inventoryForm1);

        // Create inventory 2 and save
        InventoryForm inventoryForm2 = new InventoryForm();
        inventoryForm2.setName("test2");
        Inventory inventory2 = inventoryService.createInventory(inventoryForm2);

        // Change inventory2's name into the name of inventory1
        inventoryService.updateInventory(inventory2.getId(),inventory1.getName(), inventory2.getType(), inventory2.getDescription());

        // Retrieve the inventories from the repository
        Inventory foundInventory1 = inventoryRepository.findById(inventory1.getId()).get();
        Inventory foundInventory2 = inventoryRepository.findById(inventory2.getId()).get();

        // Two inventories should now have same name
        Assertions.assertEquals(foundInventory1.getName(), foundInventory2.getName());
    }

    @Test
    void deleteInventory() {
        // Create inventory and save
        InventoryForm inventoryForm = new InventoryForm();
        Inventory inventory = inventoryService.createInventory(inventoryForm);

        // Delete inventory in the repository
        inventoryService.deleteInventoryById(inventory.getId());

        // It should not be able to find the inventory since it is deleted.
        Throwable exception = assertThrows(NoSuchElementException.class, () -> inventoryRepository.findById(inventory.getId()).get());

  }
}