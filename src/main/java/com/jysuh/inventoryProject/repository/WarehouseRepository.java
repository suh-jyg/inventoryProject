package com.jysuh.inventoryProject.repository;

import com.jysuh.inventoryProject.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    List<Warehouse> findAll();
}
