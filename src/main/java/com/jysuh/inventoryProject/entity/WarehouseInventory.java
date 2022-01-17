package com.jysuh.inventoryProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WarehouseInventory {

  @Id
  @GeneratedValue
  @Column(name = "warehouse_inventory_id")
  private Integer id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "inventory_id")
  private Inventory inventory;

  @JsonIgnore
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "warehouse_id")
  private Warehouse warehouse;

  public static WarehouseInventory createWarehouseInventory(Inventory inventory, Warehouse warehouse) {
    WarehouseInventory warehouseInventory = new WarehouseInventory();
    warehouseInventory.setInventory(inventory);
    warehouseInventory.setWarehouse(warehouse);
    return warehouseInventory;
  }
}
