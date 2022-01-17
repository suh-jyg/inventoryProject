package com.jysuh.inventoryProject.service;

import com.jysuh.inventoryProject.entity.Inventory;
import com.jysuh.inventoryProject.entity.Location;
import com.jysuh.inventoryProject.entity.LocationInventory;
import com.jysuh.inventoryProject.repository.InventoryRepository;
import com.jysuh.inventoryProject.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LocationService {
  private final LocationRepository locationRepository;
  private final InventoryRepository inventoryRepository;

  // Creates the location, and the locationInventory at the same time.
  // locationInventory is saved as list for the scalability - TODO: to develop further, allow locations to have multiple inventories
  @Transactional
  public Location createLocation(String name, Integer inventoryId) {
    Inventory inventory = returnInventory(inventoryRepository.findById(inventoryId));
    Location location = Location.createLocation(name);

    LocationInventory locationInventory = LocationInventory.createLocationInventory(inventory, location);
    location.addLocationInventory(locationInventory);

    locationRepository.save(location);
    return location;
  }

  // Update the location's name as input variable
  @Transactional
  public void updateLocation(Integer locationId, String name) {
    Location location = returnLocation(locationRepository.findById(locationId));
    location.setName(name);
  }

  // Delete the location
  @Transactional
  public void deleteLocationById(Integer locationId) {
    locationRepository.deleteById(locationId);
  }

  public Location findById(Integer locationId) {
    Location location = returnLocation(locationRepository.findById(locationId));
    return location;
  }

  public List<Location> findLocations() {
    return locationRepository.findAll();
  }

  public List<LocationInventory> findLocationInventories(Integer locationId) {
    Location location = returnLocation(locationRepository.findById(locationId));
    return location.getLocationInventories();
  }

  // Since JPA returns the Objects as optional, remove it, and same for the method below
  public Inventory returnInventory(Optional<Inventory> optionalInv) {
    Inventory inventory = null;
    if (optionalInv.isPresent()) {
      inventory = optionalInv.get();
    } else {
      throw new RuntimeException(" No ID found");
    }
    return inventory;
  }

  public Location returnLocation(Optional<Location> optionalWare) {
    Location location = null;
    if (optionalWare.isPresent()) {
      location = optionalWare.get();
    } else {
      throw new RuntimeException(" No ID found");
    }
    return location;
  }

}
