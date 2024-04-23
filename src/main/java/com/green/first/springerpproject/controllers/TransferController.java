package com.green.first.springerpproject.controllers;

import com.green.first.springerpproject.models.Transfer;
import com.green.first.springerpproject.dtos.TransferDto;
import com.green.first.springerpproject.repositories.AccountAccountsRepository;
import com.green.first.springerpproject.repositories.Business_partnerRepository;
import com.green.first.springerpproject.repositories.EmployeeRepository;
import com.green.first.springerpproject.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TransferController {
    TransferRepository repository;
    EmployeeRepository employeeRepository;
    AccountAccountsRepository accountAccountsRepository;
    Business_partnerRepository businessPartnerRepository;

    @Autowired
    public TransferController(TransferRepository repository,
                              EmployeeRepository employeeRepository,
                              AccountAccountsRepository accountAccountsRepository,
                              Business_partnerRepository businessPartnerRepository) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
        this.accountAccountsRepository = accountAccountsRepository;
        this.businessPartnerRepository = businessPartnerRepository;
    }

    @GetMapping("/transfers")
    public String getAllTransferPage(Model model) {
        List<TransferDto> transferDtoList = repository.findAllTransfer();
        model.addAttribute("transferDtoList", transferDtoList);
        return "/transfer/transfer_page";
    }

    @GetMapping("/transfer/add")
    public String addTransfer(Model model) {
        model.addAttribute("employeeList", employeeRepository.findEmployeesAll());
        model.addAttribute("accountAccountList", accountAccountsRepository.findAccountsAll());
        model.addAttribute("businessPartnerList", businessPartnerRepository.findAllBusinessPartners());
        return "/transfer/transfer_add";
    }

    @PostMapping("/transfer/add")
    public String addTransfer(@RequestParam(name = "new_bank_account_number") String new_bank_account_number,
                              @RequestParam(name = "new_bank_account_name") String new_bank_account_name,
                              @RequestParam(name = "isWithdraw") boolean isWithdraw,
                              @RequestParam(name = "new_volume") int new_volume1,
                              @RequestParam(name = "new_account_id") long new_account_id,
                              @RequestParam(name = "new_info") String new_info,
                              @RequestParam(name = "new_business_partner_id") String new_business_partner_id,
                              @RequestParam(name = "new_volume") int new_volume2,
                              @RequestParam(name = "new_volume") int new_volume3
    ) {
        if(isWithdraw) {   // 출금이면 금액 -로 전환 후 쿼리 INSERT
            repository.insertTransfer(new_bank_account_number, new_bank_account_name,
                    (new_volume1 * -1), new_account_id, new_info, new_business_partner_id, (new_volume2 * -1), (new_volume3 * -1));
        } else {
            repository.insertTransfer(new_bank_account_number, new_bank_account_name,
                    new_volume1, new_account_id, new_info, new_business_partner_id, new_volume2, new_volume3);
        }
        return "redirect:/transfers";
    }
}