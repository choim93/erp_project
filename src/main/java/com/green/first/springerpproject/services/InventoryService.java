package com.green.first.springerpproject.services;

import com.green.first.springerpproject.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // 창고이동 -> [출발지 창고 재고 감소], [목적지 창고 재고 증가], 창고이동 사유 번호는 3
    public void moveInventory(long departureStorageId, long destinationStorageId, long itemId, int itemQuantity) {
        inventoryRepository.insertInventory(departureStorageId, itemId, (-1 * itemQuantity), 3);
        inventoryRepository.insertInventory(destinationStorageId, itemId, itemQuantity, 3);
    }

    // 재고조정 -> [증가 아니면 감소], 재고조정 사유 번호는 4
    public void adjustInventory(long storageId, long itemId, int itemQuantity, boolean isIncrease) {
        if (isIncrease) {
            inventoryRepository.insertInventory(storageId, itemId, itemQuantity, 4);            // 증가하거나
        } else {
            inventoryRepository.insertInventory(storageId, itemId, (-1 * itemQuantity), 4);  // 감소하거나
        }
    }
}

