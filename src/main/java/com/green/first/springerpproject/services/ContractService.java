package com.green.first.springerpproject.services;

import com.green.first.springerpproject.models.Contract;
import com.green.first.springerpproject.models.Inventory;
import com.green.first.springerpproject.repositories.ContractRepository;
import com.green.first.springerpproject.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {
    ContractRepository contractRepository;
    InventoryRepository inventoryRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository, InventoryRepository inventoryRepository) {
        this.contractRepository = contractRepository;
        this.inventoryRepository = inventoryRepository;
    }

    // 구매는 [거래], [재고] 테이블에 접근합니다
    // 원재료를 구매하면 재고에 등록됩니다
    public void purchaseAndIncreaseInventory(Contract contract) {
        contractRepository.insertContract(contract);    // 거래 테이블에 INSERT
        Inventory inventory = new Inventory();      // 재고 테이블에 INSERT하기 위해 재고 객체 생성
        inventory.setItemId(contract.getContractItemId());                  //  재고 레코드의 각각의 속성은
        inventory.setStorageId(contract.getContractStorageId());            //  거래 객체의 정보를 바탕으로
        inventory.setItemQuantity(contract.getContractItemQuantity());      //  객체에 setter()로 설정하여 INSERT 합니다
        inventory.setMoveReasonId(1L);              // 재고 이동 사유 ID 1은 구매 ( move_reason table 참고 )
        inventoryRepository.insertInventory(inventory); // 재고 테이블에 INSERT
    }

    public void sellAndDecreaseInventory(Contract contract) {
        contractRepository.insertContract(contract);    // 거래 테이블에 INSERT
        Inventory inventory = new Inventory();          // 재고 테이블에 INSERT하기 위해 재고 객체 생성
        inventory.setItemId(contract.getContractItemId());
        inventory.setStorageId(contract.getContractStorageId());
        inventory.setItemQuantity((-1 * contract.getContractItemQuantity()));
        inventory.setMoveReasonId(2L);  // 2는 판매
        inventoryRepository.insertInventory(inventory);
    }
}
