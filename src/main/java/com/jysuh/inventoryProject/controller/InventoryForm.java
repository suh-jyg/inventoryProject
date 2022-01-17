package com.jysuh.inventoryProject.controller;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryForm {
  private Integer id;

  private String name;

  private String type;

  private String description;
}
