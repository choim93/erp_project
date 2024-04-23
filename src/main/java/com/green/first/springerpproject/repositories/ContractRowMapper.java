package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.models.Contract;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ContractRowMapper implements RowMapper<Contract> {

    @Override
    public Contract mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contract contract = new Contract();
        contract.setContractId(rs.getLong("contract_id"));
        contract.setSelling(rs.getBoolean("is_selling"));
        contract.setContractBusinessPartnerId(rs.getString("contract_business_partner_id"));
        contract.setContractStorageId(rs.getLong("contract_storage_id"));
        contract.setContractResponsibleEmployeeId(rs.getLong("contract_responsible_employee_id"));
        contract.setContractItemId(rs.getLong("contract_item_id"));
        contract.setContractItemQuantity(rs.getInt("contract_item_quantity"));
        contract.setDealDate(rs.getObject("deal_date", LocalDate.class));
        return contract;
    }
}
