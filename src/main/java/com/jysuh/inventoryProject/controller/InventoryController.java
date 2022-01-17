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
import java.util.Optional;

@Controller
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventories/new")
    public String createForm(Model model){
        model.addAttribute("form", new InventoryForm());
        return "inventories/createNewInventoryForm";
    }

    @PostMapping("/inventories/new")
    public String createInventory(InventoryForm form) {
        Inventory inventory = new Inventory();
        inventory.setName(form.getName());
        inventory.setPrice(form.getPrice());
        inventory.setQuantity(form.getQuantity());
        inventoryService.create(inventory);
        return "redirect:/";
    }

    @GetMapping("/inventories")
    public String list(Model model) {
        List<Inventory> inventories = inventoryService.findInventories();
        model.addAttribute("inventories", inventories);
        return "inventories/inventoryList";
    }

    @GetMapping("inventories/{inventoryId}/edit")
    public String updateInventoryForm(@PathVariable("inventoryId") Integer inventoryID, Model model) {
        Inventory foundInventory = inventoryService.findById(inventoryID);

        InventoryForm form = new InventoryForm();
        form.setId(foundInventory.getId());
        form.setName(foundInventory.getName());
        form.setPrice(foundInventory.getPrice());
        form.setQuantity(foundInventory.getQuantity());

        model.addAttribute("form", form);
        return "inventories/updateInventoryForm";
    }

    @PostMapping("inventories/{inventoryId}/edit")
    public String updateInventory(@PathVariable("inventoryId") Integer inventoryId, @ModelAttribute("form") InventoryForm form) {
        inventoryService.updateInventory(inventoryId,form.getName(), form.getPrice(), form.getQuantity());
        return "redirect:/inventories";
    }

    @GetMapping("/inventories/{inventoryId}/delete")
    public String deleteInventories(@PathVariable("inventoryId") Integer inventoryId) {
        inventoryService.deleteInventoryById(inventoryId);
        return "redirect:/inventories";
    }
}
