package com.jysuh.inventoryProject.controller;

import com.jysuh.inventoryProject.entity.Inventory;
import com.jysuh.inventoryProject.entity.Location;
import com.jysuh.inventoryProject.entity.LocationInventory;
import com.jysuh.inventoryProject.service.InventoryService;
import com.jysuh.inventoryProject.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LocationController {
  private final LocationService locationService;
  private final InventoryService inventoryService;

  // Creates the location
  // Also requires the list of inventory since location should be chosen between them.
  @GetMapping("/location/new")
  public String createLocation(Model model) {
    List<Inventory> inventories = inventoryService.findInventories();
    model.addAttribute("inventories", inventories);
    model.addAttribute("form", new LocationForm());
    return "location/createNewLocationForm";
  }

  // Posts the location
  @PostMapping("/location/new")
  public String createLocation(LocationForm form, Model model, @RequestParam("inventoryId") Integer inventoryId) {
    String name = form.getName();
    locationService.createLocation(name, inventoryId);
    return "redirect:/location";
  }

  // Retrieves the list of the location
  @GetMapping("/location")
  public String locationList(Model model) {
    List<Location> locations = locationService.findLocations();
    model.addAttribute("locations", locations);
    return "location/locationList";
  }

  // Retrieves the information of the inventory for specific location
  @GetMapping("location/{locationId}")
  public String getLocationContent(@PathVariable("locationId") Integer locationId, Model model) {
    Location location = locationService.findById(locationId);
    List<LocationInventory> locationInventories =
        locationService.findLocationInventories(locationId);
    model.addAttribute("location", location);
    model.addAttribute("locationInventories", locationInventories);
    return "location/location";
  }

  // Update the location
  // Gets the variables through using LocationForm, then add it to the model
  @GetMapping("location/{locationId}/edit")
  public String updateLocationForm(@PathVariable("locationId") Integer locationId, Model model) {
    Location location = locationService.findById(locationId);
    model.addAttribute("location", location);

    LocationForm locationForm = LocationForm.builder()
            .name(location.getName())
            .build();

    model.addAttribute("form", locationForm);
    return "location/updateLocationForm";
  }

  // Post the update
  @PostMapping("location/{locationId}/edit")
  public String updateLocation(
      @PathVariable("locationId") Integer locationId,
      @ModelAttribute("form") InventoryForm form) {
    locationService.updateLocation(locationId, form.getName());
    return "redirect:/location";
  }

  // Delete the location
  @GetMapping("location/{locationId}/delete")
  public String deleteLocation(@PathVariable("locationId") Integer locationId) {
    locationService.deleteLocationById(locationId);
    return "redirect:/location";
  }

  //TODO can be developed further by allow locations have multiple inventories

}
