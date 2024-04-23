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
public class ChartController {
    BondDebtRepository bondDebtRepository;
    ContractRepository contractRepository;

    @Autowired
    public ChartController(BondDebtRepository bondDebtRepository, ContractRepository contractRepository) {
        this.bondDebtRepository = bondDebtRepository;
        this.contractRepository = contractRepository;
    }

    //채권 차트
    @GetMapping("/bond_chart")
    public String BondChart(Model model) {
        List<BondDebtDto> bondDtoList = bondDebtRepository.findAllBondGroupByBusinessPartner();
        model.addAttribute("bondDtoList", bondDtoList);
        return "chart/bond_chart";
    }

    //채무 차트
    @GetMapping("/debt_chart")
    public String debtChart(Model model) {
        List<BondDebtDto> DebtDtoList = bondDebtRepository.findAllDebtGroupByBusinessPartner();
        model.addAttribute("DebtDtoList", DebtDtoList);
        return "chart/debt_chart";
    }

    //거래처 별 판매 금액 차트
    @GetMapping("/business_partner_chart")
    public String businessPartnerChart(Model model) {
        List<BondDebtDto> businessPartnerSalesAmounts = bondDebtRepository.findAllTotalSellGroupByBusinessPartner();
        model.addAttribute("businessPartnerSalesAmounts", businessPartnerSalesAmounts);
        return "chart/business_partner_chart";
    }

    // 판매 제품 비중 차트
    @GetMapping("/item_chart")
    public String itemChart(Model model) {
        List<InventoryDto> totalSalesProductQuantityList = contractRepository.findItemRank();
        model.addAttribute("totalSalesProductQuantityList", totalSalesProductQuantityList);
        return "chart/item_chart";
    }


}
