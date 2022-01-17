package com.jysuh.inventoryProject.controller;

import com.jysuh.inventoryProject.entity.Inventory;
import com.jysuh.inventoryProject.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class InventoryController {
  private final InventoryService inventoryService;

  @Autowired
  public InventoryController(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @GetMapping("/inventory/new")
  public String createForm(Model model) {
    model.addAttribute("form", new InventoryForm());
    return "inventory/createNewInventoryForm";
  }

  @PostMapping("/inventory/new")
  public String createInventory(InventoryForm form) {
    Inventory inventory = new Inventory();
    inventory.setName(form.getName());
    inventory.setType(form.getType());
    inventory.setDescription(form.getDescription());
    inventoryService.create(inventory);
    return "redirect:/";
  }

  @GetMapping("/inventory")
  public String list(Model model) {
    List<Inventory> inventories = inventoryService.findInventories();
    model.addAttribute("inventories", inventories);
    return "inventory/inventoryList";
  }

  @GetMapping("inventory/{inventoryId}/edit")
  public String updateInventoryForm(@PathVariable("inventoryId") Integer inventoryID, Model model) {
    Inventory inventory = inventoryService.findById(inventoryID);
    model.addAttribute("inventory", inventory);

    InventoryForm form = new InventoryForm();
    form.setName(inventory.getName());
    form.setType(inventory.getType());
    form.setDescription(inventory.getDescription());

    model.addAttribute("form", form);
    return "inventory/updateInventoryForm";
  }

  @PostMapping("inventory/{inventoryId}/edit")
  public String updateInventory(
      @PathVariable("inventoryId") Integer inventoryId,
      @ModelAttribute("form") InventoryForm form) {

    inventoryService.updateInventory(
        inventoryId, form.getName(), form.getType(), form.getDescription());
    return "redirect:/inventory";
  }

  @GetMapping("/inventories/{inventoryId}/delete")
  public String deleteInventories(@PathVariable("inventoryId") Integer inventoryId) {
    inventoryService.deleteInventoryById(inventoryId);
    return "redirect:/inventories";
  }
}
