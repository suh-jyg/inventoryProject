package com.jysuh.inventoryProject.controller;

import com.jysuh.inventoryProject.entity.Inventory;
import com.jysuh.inventoryProject.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InventoryController {
  private final InventoryService inventoryService;

  // Creates new inventory
  // Uses the InventoryForm class and add its variable to the thymeleaf, and posts using below method
  @GetMapping("/inventory/new")
  public String createForm(Model model) {
    model.addAttribute("form", new InventoryForm());
    return "inventory/createNewInventoryForm";
  }

  // Posts new inventory
  @PostMapping("/inventory/new")
  public String createInventory(InventoryForm inventoryForm) {
    inventoryService.createInventory(inventoryForm);
    return "redirect:/";
  }

  // Retrieves the list of the inventory
  @GetMapping("/inventory")
  public String listInventory(Model model) {
    List<Inventory> inventories = inventoryService.findInventories();
    model.addAttribute("inventories", inventories);
    return "inventory/inventoryList";
  }

  // Updates the inventory
  // Gets the variables through using InventoryForm, then add it to the model
  @GetMapping("inventory/{inventoryId}/edit")
  public String updateInventoryForm(@PathVariable("inventoryId") Integer inventoryID, Model model) {
    Inventory inventory = inventoryService.findById(inventoryID);
    model.addAttribute("inventory", inventory);

    InventoryForm inventoryForm = InventoryForm.builder()
            .name(inventory.getName())
            .type(inventory.getType())
            .description(inventory.getDescription())
            .build();

    model.addAttribute("form", inventoryForm);
    return "inventory/updateInventoryForm";
  }

  // Posts the update
  @PostMapping("inventory/{inventoryId}/edit")
  public String updateInventory(
      @PathVariable("inventoryId") Integer inventoryId,
      @ModelAttribute("form") InventoryForm form) {
    inventoryService.updateInventory(
        inventoryId, form.getName(), form.getType(), form.getDescription());
    return "redirect:/inventory";
  }

  // Delete the inventory based on the id of inventory
  @GetMapping("/inventory/{inventoryId}/delete")
  public String deleteInventories(@PathVariable("inventoryId") Integer inventoryId) {
    inventoryService.deleteInventoryById(inventoryId);
    return "redirect:/inventory";
  }
}
