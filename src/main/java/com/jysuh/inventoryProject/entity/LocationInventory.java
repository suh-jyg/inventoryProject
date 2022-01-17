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
public class LocationInventory {

  @Id
  @GeneratedValue
  @Column(name = "location_inventory_id")
  private Integer id;

  // Made as list and applied mapping restriction for the scalability
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "inventory_id")
  private Inventory inventory;

  @JsonIgnore
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "location_id")
  private Location location;

  public static LocationInventory createLocationInventory(Inventory inventory, Location location) {
    LocationInventory locationInventory = new LocationInventory();
    locationInventory.setInventory(inventory);
    locationInventory.setLocation(location);
    return locationInventory;
  }
}
