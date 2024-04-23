package com.green.first.springerpproject.controllers;

import com.green.first.springerpproject.models.Item;
import com.green.first.springerpproject.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ItemController {

    ItemRepository repository;

    @Autowired
    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/items")
    public String getItemListPage(Model model) {
        List<Item> itemList = repository.findAllItem();
        model.addAttribute("item_list", itemList);
        return "items/item_list";
    }

    @GetMapping("/items_raw_material")
    public String getOnlyRawMaterialPage(Model model) {
        List<Item> itemList = repository.findOnlyRawMaterial();
        model.addAttribute("item_list", itemList);
        return "items/item_list";
    }

    @GetMapping("/items_product")
    public String getOnlyProductPage(Model model) {
        List<Item> itemList = repository.findOnlyProduct();
        model.addAttribute("item_list", itemList);
        return "items/item_list";
    }

    @GetMapping("/item/edit/{id}")
    public String editItemPage(@PathVariable long id, Model model) {
        Item item = repository.findItemById(id);
        model.addAttribute("selected_item", item);
        return "items/item_edit";
    }

    @PostMapping("/item/edit/{id}")
    public String editItem(@ModelAttribute Item item) {
        repository.updateItemById(item.isRawMaterial(), item.getItemPrice(), item.getItemName(), item.getItemId());
        return "redirect:/items";
    }

    @GetMapping("/item/add")
    public String addItemPage(Model model) {
        model.addAttribute("new_item", new Item());
        return "items/item_add";
    }

    @PostMapping("/item/add")
    public String addItem(@ModelAttribute(name = "new_item") Item item) {
        repository.insertItem(item.isRawMaterial(), item.getItemPrice(), item.getItemName());
        return "redirect:/items";
    }

    @PostMapping("/item/delete/{id}")
    public String addItem(@PathVariable long id) {
        repository.deleteItemById(id);
        return "redirect:/items";
    }

    @GetMapping("/getItemDetails")
    @ResponseBody
    public Item getItemInformation(@RequestParam(name = "itemId") long id) {
        return repository.findItemById(id);
    }

}
