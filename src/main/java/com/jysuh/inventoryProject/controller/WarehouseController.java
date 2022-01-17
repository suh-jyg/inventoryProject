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
  public String createWarehouse(WarehouseForm form, Model model, @RequestParam("id") Integer id) {
    String name = form.getName();
    warehouseService.create(name, id);
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
    List<WarehouseInventory> warehouseInventories = warehouseService.findWarehouseInventories(warehouseId);
    model.addAttribute("warehouse", warehouse);
    model.addAttribute("warehouseInventories", warehouseInventories);
    return "warehouse/warehouse";
  }
}
