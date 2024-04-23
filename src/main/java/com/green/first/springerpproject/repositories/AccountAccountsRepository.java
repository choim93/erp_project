package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.models.AccountAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountAccountsRepository {

    @Autowired
    JdbcTemplate jdbcTemp;

    static final String SELECT_ACCOUNTS_BYID = """
            select * from account_accounts as a where a.account_id = ?
            """;
    static final String SELECT_ACCOUNTS_ALL = "select * from account_accounts";
    static final String INSERT_ACCOUNTS = "insert into account_accounts (account_name, account_type) values (?,?)";
    static final String UPDATE_ACCOUNTS_BYID = "update account_accounts set account_name = ? , account_type = ? where account_id = ? ";
    static final String DELETE_ACCOUNTS_BYID = "delete from account_accounts as a where a.account_id = ?";

    public AccountAccount findAccountsById(long id) {
        return jdbcTemp.queryForObject(SELECT_ACCOUNTS_BYID, new BeanPropertyRowMapper<>(AccountAccount.class), id);
    }

    public List<AccountAccount> findAccountsAll() {
        return jdbcTemp.query(SELECT_ACCOUNTS_ALL, new BeanPropertyRowMapper<>(AccountAccount.class));
    }

    public void insertAccounts(String accountName, String accountType) {
        jdbcTemp.update(INSERT_ACCOUNTS, accountName, accountType);
    }

    public void updateAccountsById(String accountName, String accountType, long id) {
        jdbcTemp.update(UPDATE_ACCOUNTS_BYID, accountName, accountType, id);
    }

    public void deleteAccountsById(long id) {
        jdbcTemp.update(DELETE_ACCOUNTS_BYID, id);
    }

}