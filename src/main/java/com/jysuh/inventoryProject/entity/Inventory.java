package com.jysuh.inventoryProject.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "inventory")
@Builder @AllArgsConstructor
@NoArgsConstructor @EqualsAndHashCode(of = "id")
public class Inventory {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private String type;

  private String description;

  //TODO can be developed further by applying restrictions on name - such as not null, or no duplicates

}
