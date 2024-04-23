package com.green.first.springerpproject.services;

import com.green.first.springerpproject.dtos.ProductionDto2;
import com.green.first.springerpproject.models.Inventory;
import com.green.first.springerpproject.models.ProductionRawMaterial;
import com.green.first.springerpproject.repositories.InventoryRepository;
import com.green.first.springerpproject.repositories.ProductionRawMaterialRepository;
import com.green.first.springerpproject.repositories.ProductionRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductionService {
    ProductionRepository productionRepository;
    ProductionRawMaterialRepository prmRepository;
    InventoryRepository inventoryRepository;

    public ProductionService(ProductionRepository productionRepository, ProductionRawMaterialRepository prmRepository, InventoryRepository inventoryRepository) {
        this.productionRepository = productionRepository;
        this.prmRepository = prmRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public void produce(ProductionDto2 production) {
        // 생산 테이블 레코드 추가 후 키 값을 소모 원재료 등록에 사용하기 위해 키 값을 저장
        long generatedKeyValue = productionRepository.insertProductionAndGetProductionId(production.getProductionDate(), production.getProductId(), production.getProductQuantity(), production.getDestinationStorageId());

        // 생산으로 제품 재고 증가 등록
        Inventory inventoryOfProduct = fromProductToInventory(production);
        inventoryRepository.insertInventory(inventoryOfProduct);

        // 원재료 항목 별로 소모 원재료 등록
        for (ProductionRawMaterial productionRawMaterial : production.getRawMaterials()) {
            productionRawMaterial.setProductionId(generatedKeyValue);   // 어느 생산 지시에 따른 원재료 사용인가? -> 생산 키 값 등록
            prmRepository.addProductionRawMaterial(productionRawMaterial);
            // 소모 원재료 재고 감소 등록
            Inventory inventoryOfRawMaterial = fromRawMaterialToInventory(productionRawMaterial);
            inventoryRepository.insertInventory(inventoryOfRawMaterial);
        }
    }

    // 생산으로 추가되는 제품 재고 등록을 위한 재고 객체 생성 메서드
    private static Inventory fromProductToInventory(ProductionDto2 production) {
        Inventory inventory = new Inventory();
        inventory.setItemId(production.getProductId());
        inventory.setStorageId(production.getDestinationStorageId());
        inventory.setItemQuantity(production.getProductQuantity());
        inventory.setMoveReasonId(5L);
        return inventory;
    }

    // 생산에 사용되는 원재료 재고 감소 등록을 위한 재고 객체 생성 메서드
    private static Inventory fromRawMaterialToInventory(ProductionRawMaterial rawMaterial) {
        Inventory inventory = new Inventory();
        inventory.setItemId(rawMaterial.getRawMaterialId());
        inventory.setStorageId(rawMaterial.getStorageId());
        inventory.setItemQuantity(-1 * rawMaterial.getRawMaterialQuantity());   // 원재료 감소
        inventory.setMoveReasonId(6L);
        return inventory;
    }
}
