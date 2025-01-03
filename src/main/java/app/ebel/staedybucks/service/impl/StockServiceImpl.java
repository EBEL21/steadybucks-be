package app.ebel.staedybucks.service.impl;

import app.ebel.staedybucks.entity.Stock;
import app.ebel.staedybucks.repository.StockRepository;
import app.ebel.staedybucks.repository.impl.StockBatchRepository;
import app.ebel.staedybucks.service.StockService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockBatchRepository stockBatchRepository;

    @Override
    public String uploadStockListByCsvFile(MultipartFile file) throws IOException {
        List<Stock> stockList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            boolean isHeader = true;
            while((line = reader.readLine()) !=null) {
                if(isHeader) {
                    isHeader = false;
                    continue;
                }
                // code,name,market,section_code,section_name,industry_code,industry,isSPAC
                String[] fields = line.split(",");

                Stock stock = Stock.builder()
                        .code(fields[0])
                        .name(fields[1])
                        .market(fields[2])
                        .section_code(Short.valueOf(fields[3]))
                        .section(fields[4])
                        .industryCode(fields[5])
                        .industry(fields[6])
                        .isSPAC(Objects.equals(fields[7], "1"))
                        .type("한국주식")
                        .build();

                stockList.add(stock);

                if (stockList.size() == 100) {
                    stockBatchRepository.saveAllInBatch(stockList, 100);
                    stockList.clear();
                }
            }
        }

        return "success";
    }
}