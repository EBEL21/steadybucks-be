package app.ebel.steadybucks.repository.impl;

import app.ebel.steadybucks.entity.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StockBatchRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveAllInBatch(List<Stock> stockList, int batch_size) {
        String sql = "INSERT INTO STOCK (code, industry, industry_code, isspac, market, name, section, section_code, type) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, stockList, batch_size, (ps, entity) -> {
            ps.setString(1, entity.getCode());
            ps.setString(2, entity.getIndustry());
            ps.setString(3, entity.getIndustryCode());
            ps.setBoolean(4, entity.getIsSPAC());
            ps.setString(5, entity.getMarket());
            ps.setString(6, entity.getName());
            ps.setString(7, entity.getSection());
            ps.setShort(8, entity.getSection_code());
            ps.setString(9, entity.getType());
        });
    }
}
