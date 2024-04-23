package com.green.first.springerpproject.controllers;

import com.green.first.springerpproject.dtos.ProductionDto2;
import com.green.first.springerpproject.models.Item;
import com.green.first.springerpproject.models.Production;
import com.green.first.springerpproject.dtos.ProductionDto;
import com.green.first.springerpproject.models.Storage;
import com.green.first.springerpproject.repositories.ItemRepository;
import com.green.first.springerpproject.repositories.ProductionRepository;
import com.green.first.springerpproject.repositories.StoragesRepository;
import com.green.first.springerpproject.services.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    - ProductionController.java
    - 생산 등록 POST 요청을 처리할 DTO
    - 출력을 위한 DTO와는 달리 생산에 사용된 원재료들의 리스트를 포함하기 때문에 새로 만듬
*/
@Controller
public class ProductionController {
    ProductionRepository productionRepository;
    StoragesRepository storagesRepository;
    ItemRepository itemRepository;
    ProductionService productionService;

    @Autowired
    public ProductionController(ProductionRepository repository,
                                StoragesRepository storagesRepository,
                                ItemRepository itemRepository,
                                ProductionService productionService) {
        this.productionRepository = repository;
        this.storagesRepository = storagesRepository;
        this.itemRepository = itemRepository;
        this.productionService = productionService;
    }

    @GetMapping("/productions")
    public String getAllProductionPage(Model model) {
        List<ProductionDto> ProductionDtoList = productionRepository.findAllProduction();
        model.addAttribute("ProductionDtoList", ProductionDtoList);
        return "/production/production_page";
    }

    @GetMapping("/production/add")
    public String addProduction(Model model) {
        List<Storage> storages = storagesRepository.findAllStorages();
        List<Item> products = itemRepository.findOnlyProduct();
        List<Item> rawMaterials = itemRepository.findOnlyRawMaterial();
        model.addAttribute("storages", storages);
        model.addAttribute("products", products);
        model.addAttribute("rawMaterials", rawMaterials);
        return "/production/production_add";
    }

    @PostMapping("/production/add")
    @ResponseBody
    public void addProduction(@RequestBody ProductionDto2 production) {
        productionService.produce(production);
    }
}