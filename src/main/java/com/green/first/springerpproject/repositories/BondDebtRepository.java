package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.dtos.BondDebtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BondDebtRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    // 거래처별 채권총액 구하기 ( 거래처별 판매총액 - 거래처별 수금총액 )
    static final String SELECT_BUSINESS_PARTNER_BOND_TOTAL = """
            WITH TotalAmountCTE AS (
                SELECT
                    b.business_partner_name,
                    c.is_selling,
                    SUM((c.contract_item_quantity * i.item_price)) -
                    COALESCE((
                        SELECT SUM(t.volume)
                        FROM transfer AS t
                        WHERE t.business_partner_id = c.contract_business_partner_id
                          AND t.account_id = 1
                        GROUP BY t.business_partner_id, t.account_id
                    ), 0) AS total_amount
                FROM contracts AS c
                JOIN items AS i
            	ON i.item_id = c.contract_item_id
                JOIN business_partners AS b
                ON b.business_partner_id = c.contract_business_partner_id
                WHERE c.is_selling = 1
                GROUP BY c.contract_business_partner_id, c.is_selling
            	)
            SELECT
                business_partner_name,
                is_selling,
                total_amount,
                DENSE_RANK() OVER (ORDER BY total_amount DESC) AS total_amount_rank,
                case
            		WHEN NTILE(3)over(ORDER BY total_amount DESC) = 1 THEN '위험'
                    WHEN NTILE(3)over(ORDER BY total_amount DESC) = 2 THEN '보통'
                    WHEN NTILE(3)over(ORDER BY total_amount DESC) = 3 THEN '안전'  
                    End AS risk_level
            FROM TotalAmountCTE
            ORDER BY total_amount DESC;
            """;

    // 거래처별 채무총액 구하기 ( 거래처별 구매총액 - 거래처별 지급총액 )
    static final String SELECT_BUSINESS_PARTNER_DEBT_TOTAL = """
            WITH TotalAmountCTE AS (
                SELECT
                    b.business_partner_name,
                    c.is_selling,
                    SUM((c.contract_item_quantity * i.item_price)) +
                    COALESCE((
                        SELECT SUM(t.volume)
                        FROM transfer AS t
                        WHERE t.business_partner_id = c.contract_business_partner_id
                          AND t.account_id = 2
                        GROUP BY t.business_partner_id, t.account_id
                    ), 0) AS total_amount
                FROM contracts AS c
                JOIN items AS i
            	ON i.item_id = c.contract_item_id
                JOIN business_partners AS b
                ON b.business_partner_id = c.contract_business_partner_id
                WHERE c.is_selling = 0
                GROUP BY c.contract_business_partner_id, c.is_selling	
            	)
            SELECT
                business_partner_name,
                is_selling,
                total_amount,
                DENSE_RANK() OVER (ORDER BY total_amount DESC) AS total_amount_rank,
                case
            		WHEN NTILE(3)over(ORDER BY total_amount DESC) = 1 THEN '시급'
                    WHEN NTILE(3)over(ORDER BY total_amount DESC) = 2 THEN '보통'
                    WHEN NTILE(3)over(ORDER BY total_amount DESC) = 3 THEN '안전'  End AS risk_level   \s
            FROM TotalAmountCTE
            ORDER BY total_amount DESC;
            """;
    //거래처별 판매총액 (is_selling 이 1인 내역들)
    static final String SELECT_BUSNINESS_TOTAL_SELL = """
            SELECT c.is_selling ,b.business_partner_name ,sum((c.contract_item_quantity * i.item_price)) as totalAmount
            	FROM contracts AS c
            	JOIN items as i on i.Item_id = c.contract_item_id
                JOIN business_partners as b on b.business_partner_id = c.contract_business_partner_id
            	GROUP BY c.contract_business_partner_id ,c.is_selling
            	HAVING c.is_selling = 1
                ORDER BY totalAmount DESC;
            """;
    //거래처별 구매총액 (is_selling이 0인 내역들)
    static final String SELECT_BUSNINESS_TOTAL_PURCHASE = """
            SELECT c.is_selling ,b.business_partner_name ,sum((c.contract_item_quantity * i.item_price)) as totalAmount
            	FROM contracts AS c
            	JOIN items as i on i.Item_id = c.contract_item_id
                JOIN business_partners as b on b.business_partner_id = c.contract_business_partner_id
            	GROUP BY c.contract_business_partner_id ,c.is_selling
            	HAVING c.is_selling = 0
            	ORDER BY totalAmount DESC;
            """;
    //거래처별 수금총액
    static final String SELECT_BUSNINESS_TOTAL_DEPOSIT = """
            SELECT t.account_id,b.business_partner_name , sum(t.volume) as totalAmount
             FROM transfer as t
             join business_partners as b on b.business_partner_id = t.business_partner_id
             GROUP BY t.business_partner_id, t.account_id
             HAVING t.account_id =1;            
            """;
    //거래처별 지급총액
    static final String SELECT_BUSNINESS_TOTAL_WITHDRAW = """
            SELECT t.account_id,b.business_partner_name , sum(t.volume) as totalAmount
             FROM transfer as t
             join business_partners as b on b.business_partner_id = t.business_partner_id
             GROUP BY t.business_partner_id, t.account_id
             HAVING t.account_id = 2;
            """;

    public List<BondDebtDto> findAllBondGroupByBusinessPartner() {
        return jdbcTemplate.query(SELECT_BUSINESS_PARTNER_BOND_TOTAL, new BeanPropertyRowMapper<>(BondDebtDto.class));
    }

    public List<BondDebtDto> findAllDebtGroupByBusinessPartner() {
        return jdbcTemplate.query(SELECT_BUSINESS_PARTNER_DEBT_TOTAL, new BeanPropertyRowMapper<>(BondDebtDto.class));
    }

    public List<BondDebtDto> findAllTotalSellGroupByBusinessPartner() {
        return jdbcTemplate.query(SELECT_BUSNINESS_TOTAL_SELL, new BeanPropertyRowMapper<>(BondDebtDto.class));
    }

    public List<BondDebtDto> findAllTotalPurchaseGroupByBusinessPartner() {
        return jdbcTemplate.query(SELECT_BUSNINESS_TOTAL_PURCHASE, new BeanPropertyRowMapper<>(BondDebtDto.class));
    }

    public List<BondDebtDto> findAllTotalDepositGroupByBusinessPartner() {
        return jdbcTemplate.query(SELECT_BUSNINESS_TOTAL_DEPOSIT, new BeanPropertyRowMapper<>(BondDebtDto.class));
    }

    public List<BondDebtDto> findAllTotalWithdrawGroupByBusinessPartner() {
        return jdbcTemplate.query(SELECT_BUSNINESS_TOTAL_WITHDRAW, new BeanPropertyRowMapper<>(BondDebtDto.class));
    }
}
