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
  public Integer create(String name, Integer inventoryId) {
    Inventory inventory = removeOptionalInv(inventoryRepository.findById(inventoryId));
    Location location = Location.createLocation(name);

    LocationInventory locationInventory = LocationInventory.createLocationInventory(inventory, location);
    location.addLocationInventory(locationInventory);

    locationRepository.save(location);
    return location.getId();
  }

  @Transactional
  public void updateLocation(Integer locationId, String name) {
    Location location = removeOptionalWare(locationRepository.findById(locationId));
    location.setName(name);
  }

  @Transactional
  public void deleteLocationById(Integer locationId) {
    locationRepository.deleteById(locationId);
  }

  public Location findById(Integer locationId) {
    Optional<Location> optional = locationRepository.findById(locationId);
    Location location = null;
    if (optional.isPresent()) {
      location = optional.get();
    } else {
      throw new RuntimeException(" LocationInventory not found for id :: " + locationId);
    }
    return location;
  }

  public List<Location> findLocations() {
    return locationRepository.findAll();
  }

  public List<LocationInventory> findLocationInventories(Integer locationId) {
    Optional<Location> optional = locationRepository.findById(locationId);
    Location location = null;
    if (optional.isPresent()) {
      location = optional.get();
    } else {
      throw new RuntimeException(" LocationInventory not found for id :: " + locationId);
    }
    return location.getLocationInventories();
  }

  public Inventory removeOptionalInv(Optional<Inventory> optionalInv) {
    Inventory inventory = null;
    if (optionalInv.isPresent()) {
      inventory = optionalInv.get();
    } else {
      throw new RuntimeException(" No ID found");
    }
    return inventory;
  }

  public Location removeOptionalWare(Optional<Location> optionalWare) {
    Location location = null;
    if (optionalWare.isPresent()) {
      location = optionalWare.get();
    } else {
      throw new RuntimeException(" No ID found");
    }
    return location;
  }

}
