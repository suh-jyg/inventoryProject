package com.jysuh.inventoryProject.service;

import com.jysuh.inventoryProject.entity.Inventory;
import com.jysuh.inventoryProject.entity.Warehouse;
import com.jysuh.inventoryProject.entity.WarehouseInventory;
import com.jysuh.inventoryProject.repository.InventoryRepository;
import com.jysuh.inventoryProject.repository.WarehouseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public class WarehouseService {
  private final WarehouseRepository warehouseRepository;
  private final InventoryRepository inventoryRepository;

  public WarehouseService(WarehouseRepository warehouseRepository, InventoryRepository inventoryRepository) {
    this.warehouseRepository = warehouseRepository;
    this.inventoryRepository = inventoryRepository;
  }

  @Transactional
  public Integer create(String name, Integer warehouseId) {
    Optional<Inventory> optional = inventoryRepository.findById(warehouseId);
    Inventory inventory = null;
    if (optional.isPresent()) {
      inventory = optional.get();
    } else {
      throw new RuntimeException(" Warehouse not found for id :: " + warehouseId);
    }

    WarehouseInventory warehouseInventory = WarehouseInventory.createWarehouseInventory(inventory);
    Warehouse warehouse = Warehouse.createWarehouse(warehouseInventory);
    warehouse.setName(name);

    warehouseRepository.save(warehouse);

    return warehouse.getId();
  }

  @Transactional
  public void updateWarehouse(Integer warehouseId, String name) {
    Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseId);
    warehouse.ifPresent(i -> i.setName(name));
  }

  public void deleteWarehouseById(Integer warehouseId) {
    warehouseRepository.deleteById(warehouseId);
  }

  public Warehouse findById(Integer warehouseId) {
    Optional<Warehouse> optional = warehouseRepository.findById(warehouseId);
    Warehouse warehouse = null;
    if (optional.isPresent()) {
      warehouse = optional.get();
    } else {
      throw new RuntimeException(" WarehouseInventory not found for id :: " + warehouseId);
    }
    return warehouse;
  }

  public List<Warehouse> findWarehouses() {
    return warehouseRepository.findAll();
  }

  public List<WarehouseInventory> findWarehouseInventories(Integer warehouseId) {
    Optional<Warehouse> optional = warehouseRepository.findById(warehouseId);
    Warehouse warehouse = null;
    if (optional.isPresent()) {
      warehouse = optional.get();
    } else {
      throw new RuntimeException(" WarehouseInventory not found for id :: " + warehouseId);
    }
    return warehouse.getWarehouseInventories();
  }
}
