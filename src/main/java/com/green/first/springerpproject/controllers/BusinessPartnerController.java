package com.green.first.springerpproject.controllers;

import com.green.first.springerpproject.models.BusinessPartner;
import com.green.first.springerpproject.repositories.Business_partnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BusinessPartnerController {
    Business_partnerRepository repository;

    @Autowired
    public BusinessPartnerController(Business_partnerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/business_partner_list")
    public String getAllBusinessPartnerList(Model model) {
        List<BusinessPartner> businessPartnerList = repository.findAllBusinessPartners();
        model.addAttribute("businessPartnerList", businessPartnerList);
        return "business_partners/business_partner_list";
    }

    @GetMapping("/business_partner/edit/{bpid}")
    public String getEditPage(@PathVariable String bpid, Model model) {
        BusinessPartner businessPartner = repository.findBusinessPartnerById(bpid);
        model.addAttribute("selected_business_partner", businessPartner);
        return "business_partners/business_partner_edit";
    }

    @PostMapping("/business_partner/edit/{bpid}")
    public String editBusinessPartner(@ModelAttribute BusinessPartner businessPartner) {
        repository.updateBusinessPartnerById(businessPartner);
        return "redirect:/business_partner_list";
    }

    @PostMapping("/business_partner/delete/{id}")
    public String deleteBusinessPartner(@PathVariable String id) {
        repository.deleteBusinessPartnerById(id);
        return "redirect:/business_partner_list";
    }

    @GetMapping("/business_partner/add")
    public String getAddBusinessPartnerPage(Model model) {
        model.addAttribute("new_business_partner", new BusinessPartner());
        return "business_partners/business_partner_add";
    }

    @PostMapping("/business_partner/add")
    public String AddBusinessPartner(@ModelAttribute BusinessPartner businessPartner) {
        repository.insertBusinessPartner(businessPartner);
        return "redirect:/business_partner_list";
    }
}
