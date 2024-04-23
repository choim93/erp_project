package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.dtos.InventoryDto;
import com.green.first.springerpproject.models.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ContractRepository {
    JdbcTemplate template;

    @Autowired
    public ContractRepository(JdbcTemplate template) {
        this.template = template;
    }

    private static final String SELECT_ALL_QUERY = "SELECT * FROM contracts";

    private static final String SELECT_ONLY_PURCHASING_QUERY = "SELECT * FROM contracts WHERE is_selling=0 ORDER BY deal_date DESC,contract_id desc";
    private static final String SELECT_ONLY_SELLING_QUERY = "SELECT * FROM contracts WHERE is_selling=1 ORDER BY deal_date DESC,contract_id desc";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM contracts WHERE contract_id = ?";
    private static final String INSERT_QUERY = """
                INSERT INTO contracts ( is_selling,
                                        contract_business_partner_id,
                                        contract_storage_id,
                                        contract_responsible_employee_id,
                                        contract_item_id,
                                        contract_item_quantity,
                                        deal_date)
                VALUES ( ?, ?, ?, ?, ?, ?, ? )
            """;
    private static final String UPDATE_QUERY = """
                UPDATE contracts SET    is_selling = ?,
                                        contract_business_partner_id = ?, 
                                        contract_storage_id = ?,
                                        contract_responsible_employee_id = ?,
                                        contract_item_id = ?,
                                        deal_date = ?
                WHERE contract_id = ?
            """;
    private static final String DELETE_QUERY = "DELETE FROM contract WHERE contract_id = ?";

    //제품 판매량 차트를 위한 쿼리문
    private static final String ITEM_CHART = """
            SELECT i.item_name , is_selling,sum(c.contract_item_quantity) as itemQuantity
            FROM contracts as c
            join items as i on i.Item_id = c.contract_item_id
            GROUP BY i.item_name,is_selling
            HAVING c.is_selling = 1
            ORDER BY itemQuantity DESC;
                        
            """;

    public List<Contract> findAll() {
        return template.query(SELECT_ALL_QUERY, new ContractRowMapper());
    }

    public List<Contract> findOnlyPurchasing() {
        return template.query(SELECT_ONLY_PURCHASING_QUERY, new ContractRowMapper());
    }

    public List<Contract> findOnlySelling() {
        return template.query(SELECT_ONLY_SELLING_QUERY, new ContractRowMapper());
    }

    public Contract findById(long id) {
        return template.queryForObject(SELECT_BY_ID_QUERY, new BeanPropertyRowMapper<>(Contract.class), id);
    }

    public void insertContract(Contract contract) {
        System.out.println(contract.isSelling());
        template.update(INSERT_QUERY, contract.isSelling(),
                contract.getContractBusinessPartnerId(),
                contract.getContractStorageId(),
                contract.getContractResponsibleEmployeeId(),
                contract.getContractItemId(),
                contract.getContractItemQuantity(),
                contract.getDealDate());
    }

    public void updateById(boolean isSelling, String businessPartnerId, long storageId, long responsibleEmployeeId, long itemId, LocalDate dealTime, long id) {
        template.update(UPDATE_QUERY, isSelling, businessPartnerId, storageId, responsibleEmployeeId, itemId, dealTime, id);
    }

    public void deleteById(long id) {
        template.update(DELETE_QUERY, id);
    }

    //제품 판매량 차트를 위한 메소드
    public List<InventoryDto> findItemRank() {
        return template.query(ITEM_CHART, new BeanPropertyRowMapper<>(InventoryDto.class));
    }
}
