package com.green.first.springerpproject.controllers;

import com.green.first.springerpproject.models.AccountAccount;
import com.green.first.springerpproject.repositories.AccountAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class AccountAccountController {
    AccountAccountsRepository repository;

    @Autowired
    public AccountAccountController(AccountAccountsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/account_accounts")
    public String getAllAccountAccountsPage(Model model) {
        List<AccountAccount> accountList = repository.findAccountsAll();
        model.addAttribute("accountList", accountList);
        return "account_accounts/account_accounts_list";
    }

    @GetMapping("/account_accounts/edit/{account_id}")
    public String getEditAccountAccountsPage(@PathVariable(name = "account_id") long id, Model model) {
        AccountAccount selectedAccount = repository.findAccountsById(id);
        model.addAttribute("selected_account", selectedAccount);
        return "account_accounts/account_accounts_edit";
    }

    @PostMapping("/account_accounts/edit/{account_id}")
    public String EditAccountAccounts(@PathVariable(name = "account_id") long account_id, @RequestParam(name = "selected_account_name") String accountName, @RequestParam(name = "selected_account_type") String accountType, Model model) {
        repository.updateAccountsById(accountName, accountType, account_id);
        return "redirect:/account_accounts_list";
    }

    @PostMapping("/account_accounts/delete/{account_id}")
    public String deleteAccountAccounts(@PathVariable(name = "account_id") long account_id, Model model) {
        repository.deleteAccountsById(account_id);
        return "redirect:/account_accounts_list";
    }

    @GetMapping("/account_accounts/add")
    public String getAddAccountAccountsPage(Model model) {
        model.addAttribute("new_aa", new AccountAccount());
        return "account_accounts/account_accounts_add";
    }

    @PostMapping("/account_accounts/add")
    public String AddAccountAccounts(@ModelAttribute AccountAccount accountAccount) {
        repository.insertAccounts(accountAccount.getAccountName(), accountAccount.getAccountType());
        return "redirect:/account_accounts_list";
    }
}