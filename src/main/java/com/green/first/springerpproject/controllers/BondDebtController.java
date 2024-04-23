package com.green.first.springerpproject.controllers;

import com.green.first.springerpproject.dtos.BondDebtDto;
import com.green.first.springerpproject.repositories.BondDebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BondDebtController {
    BondDebtRepository bondDebtRepository;

    @Autowired
    public BondDebtController(BondDebtRepository bondDebtRepository) {
        this.bondDebtRepository = bondDebtRepository;
    }

    @GetMapping("bond_list")
    public String getAllBondGroupByBusinessPartner(Model model) {
        List<BondDebtDto> bondDebtDtoList = bondDebtRepository.findAllBondGroupByBusinessPartner();
        model.addAttribute("bondDebtDtoList", bondDebtDtoList);
        return "bond/bond_page";
    }

    @GetMapping("debt_list")
    public String getAllDebtGroupByBusinessPartner(Model model) {
        List<BondDebtDto> bondDebtDtoList = bondDebtRepository.findAllDebtGroupByBusinessPartner();
        model.addAttribute("bondDebtDtoList", bondDebtDtoList);
        return "debt/debt_page";
    }

    @GetMapping("business_partner/sell")
    public String getAllSellGroupByBusinessPartner(Model model) {
        List<BondDebtDto> sellDtoList = bondDebtRepository.findAllTotalSellGroupByBusinessPartner();
        model.addAttribute("sellDtoList", sellDtoList);
        return "bond/bond_sell";
    }

    @GetMapping("business_partner/purchase")
    public String getAllPurchaseGroupByBusinessPartner(Model model) {
        List<BondDebtDto> purchaseDtoList = bondDebtRepository.findAllTotalPurchaseGroupByBusinessPartner();
        model.addAttribute("purchaseDtoList", purchaseDtoList);
        return "debt/debt_purchase";
    }

    @GetMapping("business_partner/deposit")
    public String getAllDepositGroupByBusinessPartner(Model model) {
        List<BondDebtDto> depositDtoList = bondDebtRepository.findAllTotalDepositGroupByBusinessPartner();
        model.addAttribute("depositDtoList", depositDtoList);
        return "bond/bond_deposit";
    }

    @GetMapping("business_partner/whitdraw")
    public String getAllWithdrawGroupByBusinessPartner(Model model) {
        List<BondDebtDto> withdrawDtoList = bondDebtRepository.findAllTotalWithdrawGroupByBusinessPartner();
        model.addAttribute("withdrawDtoList", withdrawDtoList);
        return "debt/debt_withdraw";
    }
}
