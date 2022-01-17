package com.jysuh.inventoryProject.repository;

import com.jysuh.inventoryProject.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
  Inventory save(Inventory inventory);

  List<Inventory> findAll();
}
