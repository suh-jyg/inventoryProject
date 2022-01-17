package com.jysuh.inventoryProject.service;

import com.jysuh.inventoryProject.controller.InventoryForm;
import com.jysuh.inventoryProject.entity.Inventory;
import com.jysuh.inventoryProject.entity.Location;
import com.jysuh.inventoryProject.repository.InventoryRepository;
import com.jysuh.inventoryProject.repository.LocationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LocationServiceIntegrationTest {
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    LocationService locationService;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    InventoryService inventoryService;

    @Test
    void postLocation() {
        // Create an inventory, save, then retrieve
        InventoryForm inventoryForm = new InventoryForm();
        Inventory inventory = inventoryService.createInventory(inventoryForm);
        Inventory foundInventory = inventoryRepository.findById(inventory.getId()).get();

        // Create a location with the inventory above, save, then retrieve
        Location location = locationService.createLocation("LocationTestName", foundInventory.getId());
        Location foundLocation = locationRepository.findById(location.getId()).get();

        // Check that it the found location in the repository is same as the intended.
        Assertions.assertEquals("LocationTestName", foundLocation.getName());
    }

    @Test
    void editLocation() {
        // Create an inventory 1, save, then retrieve
        InventoryForm inventoryForm1 = new InventoryForm();
        Inventory inventory1 = inventoryService.createInventory(inventoryForm1);
        Inventory foundInventory1 = inventoryRepository.findById(inventory1.getId()).get();

        // Create a location 1 with inventory 1, save, then retrieve
        Location location1 = locationService.createLocation("testName1", foundInventory1.getId());
        Location foundLocation1 = locationRepository.findById(location1.getId()).get();

        // Create an inventory 2, save, then retrieve
        InventoryForm inventoryForm2 = new InventoryForm();
        Inventory inventory2 = inventoryService.createInventory(inventoryForm2);
        Inventory foundInventory2 = inventoryRepository.findById(inventory2.getId()).get();

        // Create a location 2 with inventory 2, save, then retrieve
        Location location2 = locationService.createLocation("testName2", foundInventory2.getId());
        Location foundLocation2 = locationRepository.findById(location2.getId()).get();

        // Change the name of location 2 as location 1's name
        locationService.updateLocation(location2.getId(), location1.getName());

        // Then the name of location 1 and 2 should be same:
        Assertions.assertEquals(location1.getName(), location2.getName());

    }

    @Test
    void deleteLocation() {
        // Create an inventory, save, then retrieve
        InventoryForm inventoryForm = new InventoryForm();
        Inventory inventory = inventoryService.createInventory(inventoryForm);
        Inventory foundInventory = inventoryRepository.findById(inventory.getId()).get();

        // Create a location with the inventory above, save, then retrieve
        Location location = locationService.createLocation("LocationTestName", foundInventory.getId());
        Location foundLocation = locationRepository.findById(location.getId()).get();

        // Delete location in the repository
        locationService.deleteLocationById(location.getId());

        // It should not be able to find the inventory since it is deleted.
        Throwable exception = assertThrows(NoSuchElementException.class, () -> locationRepository.findById(location.getId()).get());
    }
}