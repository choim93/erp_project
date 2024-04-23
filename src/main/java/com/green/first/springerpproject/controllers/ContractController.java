// ContractController
package com.green.first.springerpproject.controllers;

import com.green.first.springerpproject.dtos.BondDebtDto;
import com.green.first.springerpproject.dtos.ContractDto;
import com.green.first.springerpproject.dtos.InventoryDto;
import com.green.first.springerpproject.models.*;
import com.green.first.springerpproject.repositories.*;
import com.green.first.springerpproject.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContractController {

    ContractRepository contractRepository;
    Business_partnerRepository bpRepository;
    StoragesRepository storagesRepository;
    EmployeeRepository employeeRepository;
    ItemRepository itemRepository;

    BondDebtRepository bondDebtRepository;  // 차트 출력을 위해 추가

    ContractService contractService;

    @Autowired
    public ContractController(ContractRepository repository,
                              Business_partnerRepository bpRepository,
                              StoragesRepository storagesRepository,
                              EmployeeRepository employeeRepository,
                              ItemRepository itemRepository,
                              ContractService contractService,
                              BondDebtRepository bondDebtRepository) {
        this.contractRepository = repository;
        this.bpRepository = bpRepository;
        this.storagesRepository = storagesRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.contractService = contractService;
        this.bondDebtRepository = bondDebtRepository;
    }

    private ContractDto convertToDto(Contract contract) {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId(contract.getContractId());    // 거래 번호
        contractDto.setSelling(contract.isSelling());           // 구매/판매 여부
        BusinessPartner bp = bpRepository.findBusinessPartnerById(contract.getContractBusinessPartnerId());
        contractDto.setBusinessPartnerName(bp.getBusinessPartnerName());    // 거래처 상호명 ( 이름 )
        Storage storage = storagesRepository.findStorageById(contract.getContractStorageId());
        contractDto.setStorageName(storage.getStorageName());   // 창고 이름
        Employee employee = employeeRepository.findEmployeeById(contract.getContractResponsibleEmployeeId());
        contractDto.setResponsibleEmployeeName(employee.getEmployeeName()); // 책임사원 이름
        Item item = itemRepository.findItemById(contract.getContractItemId());  // 제품/원재료 이름
        contractDto.setItemName(item.getItemName());
        contractDto.setItemQuantity(contract.getContractItemQuantity());    // 수량
        contractDto.setTotalPrice(contractDto.getItemQuantity() * item.getItemPrice()); // 총 가격 계산
        contractDto.setContractDate(contract.getDealDate());                // 거래일
        return contractDto;
    }

    @GetMapping("/contracts_all")
    public String contractsPageUsingDto(Model model) {
        List<Contract> contractList = contractRepository.findAll();
        List<ContractDto> contractDtoList = contractList.stream()
                .map(this::convertToDto).toList();
        model.addAttribute("contract_dto_list", contractDtoList);
        return "contracts/contract_list";
    }

    @GetMapping("/contracts_sell")
    public String getSellingContractListPage(Model model) {
        List<Contract> contractList = contractRepository.findOnlySelling();
        List<ContractDto> contractDtoList = contractList.stream()
                .map(this::convertToDto).toList();
        model.addAttribute("contract_dto_list", contractDtoList);
        return "contracts/contract_list";
    }

    @GetMapping("/contracts_purchase")
    public String getPurchasingContractListPage(Model model) {
        List<Contract> contractList = contractRepository.findOnlyPurchasing();
        List<ContractDto> contractDtoList = contractList.stream()
                .map(this::convertToDto).toList();
        model.addAttribute("contract_dto_list", contractDtoList);
        return "contracts/contract_list";
    }

    @GetMapping("/contract/purchase")
    public String contractPurchasePage(Model model) {
        model.addAttribute("newContract", new Contract());
        List<BusinessPartner> businessPartnerList = bpRepository.findAllBusinessPartners();
        List<Storage> storageList = storagesRepository.findAllStorages();
        List<Employee> employeeList = employeeRepository.findEmployeesAll();
        List<Item> itemList = itemRepository.findOnlyRawMaterial();
        model.addAttribute("businessPartnerList", businessPartnerList);
        model.addAttribute("storageList", storageList);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("itemList", itemList);
        model.addAttribute("contractOption", 0); // 또는 1
        return "contracts/contract_add";
    }

    @GetMapping("/contract/sell")
    public String contractSellPage(Model model) {
        model.addAttribute("newContract", new Contract());
        List<BusinessPartner> businessPartnerList = bpRepository.findAllBusinessPartners();
        List<Storage> storageList = storagesRepository.findAllStorages();
        List<Employee> employeeList = employeeRepository.findEmployeesAll();
        List<Item> itemList = itemRepository.findOnlyProduct();
        model.addAttribute("businessPartnerList", businessPartnerList);
        model.addAttribute("storageList", storageList);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("itemList", itemList);
        model.addAttribute("contractOption", 1); // 또는 1
        return "contracts/contract_add";
    }

    @PostMapping("/contract/add_json/purchase")
    @ResponseBody
    public void addJsonContractPurchase(@RequestBody List<Contract> contracts) {
        for (Contract contract : contracts) {
            contractService.purchaseAndIncreaseInventory(contract);
        }
    }

    @PostMapping("/contract/add_json/sell")
    @ResponseBody
    public void addJsonContractSell(@RequestBody List<Contract> contracts) {
        for (Contract contract : contracts) {
            contract.setSelling(true);
            contractService.sellAndDecreaseInventory(contract);
        }
    }
}
