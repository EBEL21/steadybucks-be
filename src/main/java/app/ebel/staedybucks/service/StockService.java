package app.ebel.staedybucks.service;

import app.ebel.staedybucks.dto.StockDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StockService {
    String uploadStockListByCsvFile(MultipartFile file) throws IOException;

    List<StockDto> getStockList();
}
