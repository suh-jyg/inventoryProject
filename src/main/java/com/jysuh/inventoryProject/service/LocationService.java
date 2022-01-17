package com.jysuh.inventoryProject.service;

import com.jysuh.inventoryProject.entity.Inventory;
import com.jysuh.inventoryProject.entity.Location;
import com.jysuh.inventoryProject.entity.LocationInventory;
import com.jysuh.inventoryProject.repository.InventoryRepository;
import com.jysuh.inventoryProject.repository.LocationRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public class LocationService {
  private final LocationRepository locationRepository;
  private final InventoryRepository inventoryRepository;

  public LocationService(LocationRepository locationRepository, InventoryRepository inventoryRepository) {
    this.locationRepository = locationRepository;
    this.inventoryRepository = inventoryRepository;
  }

  @Transactional
  public Location createLocation(String name, Integer inventoryId) {
    Inventory inventory = returnInventory(inventoryRepository.findById(inventoryId));
    Location location = Location.createLocation(name);

    LocationInventory locationInventory = LocationInventory.createLocationInventory(inventory, location);
    location.addLocationInventory(locationInventory);

    locationRepository.save(location);
    return location;
  }

  @Transactional
  public void updateLocation(Integer locationId, String name) {
    Location location = returnLocation(locationRepository.findById(locationId));
    location.setName(name);
  }

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
