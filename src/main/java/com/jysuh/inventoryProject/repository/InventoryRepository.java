package com.jysuh.inventoryProject.repository;

import com.jysuh.inventoryProject.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
  Inventory save(Inventory inventory);

  Optional<Inventory> findByName(String name);

  List<Inventory> findAll();
}
