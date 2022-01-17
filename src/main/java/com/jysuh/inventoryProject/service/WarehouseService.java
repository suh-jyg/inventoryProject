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
  public Integer create(String name, Integer inventoryId) {
    Inventory inventory = removeOptionalInv(inventoryRepository.findById(inventoryId));
    Warehouse warehouse = Warehouse.createWarehouse(name);

    WarehouseInventory warehouseInventory = WarehouseInventory.createWarehouseInventory(inventory, warehouse);
    warehouse.addWarehouseInventory(warehouseInventory);

    warehouseRepository.save(warehouse);
    return warehouse.getId();
  }

  @Transactional
  public void updateWarehouse(Integer warehouseId, String name) {
    Warehouse warehouse = removeOptionalWare(warehouseRepository.findById(warehouseId));
    warehouse.setName(name);
  }

  @Transactional
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

  public Inventory removeOptionalInv(Optional<Inventory> optionalInv) {
    Inventory inventory = null;
    if (optionalInv.isPresent()) {
      inventory = optionalInv.get();
    } else {
      throw new RuntimeException(" No ID found");
    }
    return inventory;
  }

  public Warehouse removeOptionalWare(Optional<Warehouse> optionalWare) {
    Warehouse warehouse = null;
    if (optionalWare.isPresent()) {
      warehouse = optionalWare.get();
    } else {
      throw new RuntimeException(" No ID found");
    }
    return warehouse;
  }

}
