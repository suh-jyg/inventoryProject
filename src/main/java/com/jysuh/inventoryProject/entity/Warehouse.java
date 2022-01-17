package com.jysuh.inventoryProject.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "warehouses")
@Getter
@Setter
public class Warehouse {

  @Id
  @GeneratedValue
  @Column(name = "warehouse_id")
  private Integer id;

  private String name;

  @BatchSize(size = 1000)
  @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
  private List<WarehouseInventory> warehouseInventories = new ArrayList<>();

  public void addWarehouseInventory(WarehouseInventory warehouseInventory) {
    warehouseInventories.add(warehouseInventory);
    warehouseInventory.setWarehouse(this);
  }

  public static Warehouse createWarehouse(WarehouseInventory... warehouseInventories) {
    Warehouse warehouse = new Warehouse();
    for (WarehouseInventory warehouseInventory : warehouseInventories) {
      warehouse.addWarehouseInventory(warehouseInventory);
    }
    return warehouse;
  }

}
