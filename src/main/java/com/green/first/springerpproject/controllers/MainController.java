package com.green.first.springerpproject.controllers;

import com.green.first.springerpproject.dtos.BondDebtDto;
import com.green.first.springerpproject.dtos.InventoryDto;
import com.green.first.springerpproject.repositories.BondDebtRepository;
import com.green.first.springerpproject.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    BondDebtRepository bondDebtRepository;
    ContractRepository contractRepository;

    @Autowired
    public MainController(BondDebtRepository bondDebtRepository,
                          ContractRepository contractRepository) {
        this.bondDebtRepository = bondDebtRepository;
        this.contractRepository = contractRepository;
    }

    @GetMapping("/")
    public String main(Model model) {
        // 거래처별 채권 차트 출력을 위한 코드
        List<BondDebtDto> bondDtoList = bondDebtRepository.findAllBondGroupByBusinessPartner();
        model.addAttribute("bondDtoList", bondDtoList);

        // 거래처별 채무 차트 출력을 위한 코드
        List<BondDebtDto> DebtDtoList = bondDebtRepository.findAllDebtGroupByBusinessPartner();
        model.addAttribute("DebtDtoList", DebtDtoList);

        // 거래처별 총 판매 금액 차트 출력을 위한 코드
        List<BondDebtDto> businessPartnerSalesAmounts = bondDebtRepository.findAllTotalSellGroupByBusinessPartner();
        model.addAttribute("businessPartnerSalesAmounts", businessPartnerSalesAmounts);

        // 판매 제품 비중 차트 출력을 위한 코드
        List<InventoryDto> totalSalesProductQuantityList = contractRepository.findItemRank();
        model.addAttribute("totalSalesProductQuantityList", totalSalesProductQuantityList);
        return "main";
    }
}
