package com.jysuh.inventoryProject.controller;

import com.jysuh.inventoryProject.entity.Inventory;
import com.jysuh.inventoryProject.entity.Warehouse;
import com.jysuh.inventoryProject.entity.WarehouseInventory;
import com.jysuh.inventoryProject.service.InventoryService;
import com.jysuh.inventoryProject.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WarehouseController {
  private final WarehouseService warehouseService;
  private final InventoryService inventoryService;

  @GetMapping("/warehouse/new")
  public String createForm(Model model) {
    List<Inventory> inventories = inventoryService.findInventories();
    model.addAttribute("inventories", inventories);
    model.addAttribute("form", new WarehouseForm());
    return "warehouse/createNewWarehouseForm";
  }

  @PostMapping("/warehouse/new")
  public String createWarehouse(WarehouseForm form, Model model, @RequestParam("inventoryId") Integer inventoryId) {
    String name = form.getName();
    warehouseService.create(name, inventoryId);
    return "redirect:/warehouse";
  }

  @GetMapping("/warehouse")
  public String warehouseList(Model model) {
    List<Warehouse> warehouses = warehouseService.findWarehouses();
    model.addAttribute("warehouses", warehouses);
    return "warehouse/warehouseList";
  }

  @GetMapping("warehouse/{warehouseId}")
  public String getWarehouseContent(@PathVariable("warehouseId") Integer warehouseId, Model model) {
    Warehouse warehouse = warehouseService.findById(warehouseId);
    List<WarehouseInventory> warehouseInventories =
        warehouseService.findWarehouseInventories(warehouseId);
    model.addAttribute("warehouse", warehouse);
    model.addAttribute("warehouseInventories", warehouseInventories);
    return "warehouse/warehouse";
  }

  @GetMapping("warehouse/{warehouseId}/edit")
  public String updateWarehouseForm(@PathVariable("warehouseId") Integer warehouseId, Model model) {
    Warehouse warehouse = warehouseService.findById(warehouseId);
    model.addAttribute("warehouse", warehouse);

    WarehouseForm form = new WarehouseForm();
    form.setName(warehouse.getName());

    model.addAttribute("form", form);
    return "warehouse/updateWarehouseForm";
  }

  @PostMapping("warehouse/{warehouseId}/edit")
  public String updateWarehouse(
      @PathVariable("warehouseId") Integer warehouseId,
      @ModelAttribute("form") InventoryForm form) {
    warehouseService.updateWarehouse(warehouseId, form.getName());
    return "redirect:/warehouse";
  }

  @GetMapping("warehouse/{warehouseId}/delete")
  public String deleteWarehouse(@PathVariable("warehouseId") Integer warehouseId) {
    warehouseService.deleteWarehouseById(warehouseId);
    return "redirect:/warehouse";
  }

}
