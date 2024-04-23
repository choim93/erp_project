package com.green.first.springerpproject.controllers;

import com.green.first.springerpproject.dtos.InventoryDto;
import com.green.first.springerpproject.models.Item;
import com.green.first.springerpproject.models.Storage;
import com.green.first.springerpproject.repositories.InventoryRepository;
import com.green.first.springerpproject.repositories.ItemRepository;
import com.green.first.springerpproject.repositories.StoragesRepository;
import com.green.first.springerpproject.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InventoryController {
    InventoryRepository inventoryRepository;
    InventoryService inventoryService;
    StoragesRepository storagesRepository;
    ItemRepository itemRepository;

    @Autowired
    public InventoryController(InventoryRepository inventoryRepository,
                               InventoryService inventoryService,
                               StoragesRepository storagesRepository,
                               ItemRepository itemRepository) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryService = inventoryService;
        this.storagesRepository = storagesRepository;
        this.itemRepository = itemRepository;
    }


    @GetMapping("/inventories")
    public String getAllInventory(Model model) {
        List<InventoryDto> inventoryDtoList = inventoryRepository.findInventoryTotal();
        model.addAttribute("inventoryDtoList", inventoryDtoList);
        return "inventories/inventory_list";
    }

    @GetMapping("/inventories/history/{storage_id}")
    public String getInventoryHistoryByStorageName(@PathVariable long storage_id, Model model) {
        List<InventoryDto> inventoryHistoryList = inventoryRepository.findInventoryHistoryByStorageId(storage_id);
        model.addAttribute("inventoryHistoryList", inventoryHistoryList);
        return "inventories/inventory_history";
    }

    @GetMapping("/inventories/adjust")  // 재고 조정
    public String insertInventoy(Model model) {
        model.addAttribute("itemList", itemRepository.findAllItem());
        model.addAttribute("storageList", storagesRepository.findAllStorages());
        return "inventories/inventory_adjust";
    }

    @PostMapping("/inventories/adjust")
    public String getInventoryAddAdjustment(@RequestParam(name = "storageSelect") long storageId,
                                            @RequestParam(name = "itemSelect") long itemId,
                                            @RequestParam(name = "isIncrease") boolean isIncrease,
                                            @RequestParam(name = "adjustQuantity") int adjustQuantity) {
        inventoryService.adjustInventory(storageId, itemId, adjustQuantity, isIncrease);
        return "redirect:/inventories";
    }

    @GetMapping("/inventories/move")
    public String inventoryMovePage(Model model) {
        List<Storage> storages = storagesRepository.findAllStorages();
        List<Item> items = itemRepository.findAllItem();
        model.addAttribute("storages", storages);
        model.addAttribute("items", items);
        return "inventories/inventory_move";
    }

    @PostMapping("/inventories/move")
    public String inventoryMovePage(@RequestParam(name = "departureStorageSelect") long departureStorageSelect,
                                    @RequestParam(name = "destinationStorageSelect") long destinationStorageSelect,
                                    @RequestParam(name = "itemSelect") long itemSelect,
                                    @RequestParam(name = "moveQuantity") int moveQuantity) {
        inventoryService.moveInventory(departureStorageSelect, destinationStorageSelect, itemSelect, moveQuantity);
        return "redirect:/inventories";
    }
}