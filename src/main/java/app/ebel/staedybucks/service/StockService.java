package app.ebel.staedybucks.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StockService {
    String uploadStockListByCsvFile(MultipartFile file) throws IOException;
}
