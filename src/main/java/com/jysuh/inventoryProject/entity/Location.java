package com.jysuh.inventoryProject.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "locations")
@Getter
@Setter @EqualsAndHashCode(of = "id")
public class Location {

  @Id
  @GeneratedValue
  @Column(name = "location_id")
  private Integer id;

  private String name;

  // Made as list and applied mapping restriction for the scalability
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

  //TODO can be developed further by applying restrictions on name - such as not null, or no duplicates

}
