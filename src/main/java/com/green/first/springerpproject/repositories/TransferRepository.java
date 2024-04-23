package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.models.Transfer;
import com.green.first.springerpproject.dtos.TransferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TransferRepository {
    @Autowired
    JdbcTemplate jdbcTemp;

    static final String SELECT_TRANSFER_ALL = """
            SELECT t.*, a.account_name , b.business_partner_name
            FROM transfer as t join account_accounts as a on a.account_id = t.account_id
            join business_partners as b on t.business_partner_id = b.business_partner_id
            order by t.transfer_datetime desc
             """;
    static final String SELECT_TRANSFER_BYID = "select * from transfer where transfer_id = ?";
    static final String INSERT_TRANSFER = """
            INSERT INTO transfer (bank_account_number, bank_account_name, volume, account_id,
                                       info, business_partner_id, transfer_datetime, balance)
                                       SELECT ?, ?, ?, ?, ?, ?, NOW(), COALESCE((SELECT balance + ? FROM transfer ORDER BY transfer_id DESC LIMIT 1), ?) AS balance;
              """;

    /* 입출금은 중간에 데이터를 수정하면 이후 데이터의 잔액까지 저절로 수정되는 방법을 못 찾아서 update,delete 말고 insert를 -로 입력하기
    static final String UPDATE_TRANSFER_BYID = "update transfer set bank_account_number = ?, bank_account_name =?," +
            "volume = ? , account_id = ? , info = ? , business_partner_id = ? where transfer_id = ? ";

    static final String DELETE_TRANSFER_BYID = "delete * from transfer where transfer_id = ?";
     */

    public List<TransferDto> findAllTransfer() {
        return jdbcTemp.query(SELECT_TRANSFER_ALL, new BeanPropertyRowMapper<>(TransferDto.class));
    }

    public Transfer findTransferById(long id) {
        return jdbcTemp.queryForObject(SELECT_TRANSFER_BYID, new BeanPropertyRowMapper<>(Transfer.class), id);
    }

    public void insertTransfer(String bank_account_num, String bank_account_name, int volume1, long account_id,
                               String info, String business_partner_id, int volume2, int volume3) {
        jdbcTemp.update(INSERT_TRANSFER, bank_account_num, bank_account_name, volume1, account_id, info,
                business_partner_id, volume2, volume3);
    }
    /*입출금은 중간에 데이터를 수정하면 이후 데이터의 잔액까지 저절로 수정되는 방법을 못 찾아서 update,delete 말고 insert를 -로 입력하기
    public void updateTransferByid(String bank_account_num,String bank_account_name, int volume,long account_id,
                                   String info, String business_partner_id,long transfer_id){
        jdbcTemp.update(UPDATE_TRANSFER_BYID, bank_account_num,bank_account_name,volume,account_id,
                info,business_partner_id,transfer_id);
    }
    public void deleteTransferByid(long id){
        jdbcTemp.update(DELETE_TRANSFER_BYID,id);
    }
    */
}