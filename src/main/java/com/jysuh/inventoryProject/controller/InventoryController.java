package com.jysuh.inventoryProject.controller;

import com.jysuh.inventoryProject.entity.Inventory;
import com.jysuh.inventoryProject.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventories/new")
    public String createForm(){
        return "inventories/createNewInventoryForm";
    }

    @PostMapping(value = "/inventories/new")
    public String createInventory(InventoryForm form) {
        Inventory inventory = new Inventory();
        inventory.setName(form.getName());
        inventory.setCount(form.getCount());
        inventory.setValue(form.getValue());
        inventoryService.create(inventory);
        return "redirect:/";
    }

    @GetMapping(value = "/inventories")
    public String list(Model model) {
        List<Inventory> inventories = inventoryService.findInventories();
        model.addAttribute("inventories", inventories);
        return "inventories/inventoryList";
    }

}
