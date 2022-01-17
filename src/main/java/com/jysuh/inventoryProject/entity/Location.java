package com.jysuh.inventoryProject.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "locations")
@Getter
@Setter
public class Location {

  @Id
  @GeneratedValue
  @Column(name = "location_id")
  private Integer id;

  private String name;

  @BatchSize(size = 1000)
  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
  private List<LocationInventory> locationInventories = new ArrayList<>();

  public void addLocationInventory(LocationInventory locationInventory) {
    locationInventories.add(locationInventory);
  }

  public static Location createLocation(String name) {
    Location location = new Location();
    location.setName(name);
    return location;
  }

}
