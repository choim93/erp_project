package com.green.first.springerpproject.controllers;

import com.green.first.springerpproject.models.Storage;
import com.green.first.springerpproject.repositories.StoragesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StorageController {
    StoragesRepository repository;

    @Autowired
    public StorageController(StoragesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/storages")
    public String getAllStoragePage(Model model) {
        List<Storage> storageList = repository.findAllStorages();
        model.addAttribute("storageList", storageList);
        return "/storages/storage_list";
    }

    @GetMapping("/storage/add")
    public String addStoragePage(Model model) {
        model.addAttribute("new_storage", new Storage());
        return "/storages/storage_add";
    }

    @PostMapping("/storage/add")
    public String addStorage(@ModelAttribute Storage storage) {
        repository.insertStorage(storage);
        return "redirect:/storages";
    }

    @GetMapping("/storage/edit/{id}")
    public String editStoragePage(@PathVariable long id, Model model) {
        model.addAttribute("selected_storage", repository.findStorageById(id));
        return "/storages/storage_edit";
    }

    @PostMapping("/storage/edit/{selected_storage_id}")
    public String editStorage(@ModelAttribute Storage storage) {
        repository.updateStorageById(storage);
        return "redirect:/storages";
    }

    @PostMapping("/storage/delete/{id}")
    public String deleteStorage(@PathVariable long id) {
        repository.deleteStorageById(id);
        return "redirect:/storages";
    }
}
