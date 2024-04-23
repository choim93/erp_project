package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.models.BusinessPartner;
import com.green.first.springerpproject.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Business_partnerRepository {
    @Autowired
    JdbcTemplate jdbcTemp;

    static final String SELECT_BUSINESS_PARTNERS_ALL = "select * from business_partners order by business_partner_name";
    static final String SELECT_BUSINESS_PARTNER_BYID = "select * from business_partners as b " +
                                                        "where b.business_partner_id = ? ";
    static final String INSERT_BUSINESS_PARTNER = "insert into business_partners (business_partner_id," +
            "business_partner_name,business_partner_address,business_partner_contact)" +
            "values(?,?,?,?)";
    static final String UPDATE_BUSINESS_PARTNER_BYID = "update business_partners set business_partner_name = ?," +
            "business_partner_address = ?, business_partner_contact = ? " +
            "where business_partner_id = ? ";
    static final String DELETE_BUSINESS_PARTNER_BYID = "delete from business_partners where business_partner_id = ? ";

    public List<BusinessPartner> findAllBusinessPartners() {
        return jdbcTemp.query(SELECT_BUSINESS_PARTNERS_ALL, new BeanPropertyRowMapper<>(BusinessPartner.class));
    }

    public BusinessPartner findBusinessPartnerById(String id) {
        return jdbcTemp.queryForObject(SELECT_BUSINESS_PARTNER_BYID, new BeanPropertyRowMapper<>(BusinessPartner.class), id);
    }

    public void insertBusinessPartner(BusinessPartner businessPartner) {
        jdbcTemp.update(INSERT_BUSINESS_PARTNER, businessPartner.getBusinessPartnerId(), businessPartner.getBusinessPartnerName(),
                businessPartner.getBusinessPartnerAddress(), businessPartner.getBusinessPartnerContact());
    }

    public void updateBusinessPartnerById(BusinessPartner businessPartner) {
        jdbcTemp.update(UPDATE_BUSINESS_PARTNER_BYID, businessPartner.getBusinessPartnerName(),
                businessPartner.getBusinessPartnerAddress(), businessPartner.getBusinessPartnerContact(), businessPartner.getBusinessPartnerId());
    }

    public void deleteBusinessPartnerById(String id) {
        jdbcTemp.update(DELETE_BUSINESS_PARTNER_BYID, id);
    }
}
