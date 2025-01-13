package app.ebel.steadybucks.controller;

import app.ebel.steadybucks.dto.response.StockListRpDto;
import app.ebel.steadybucks.service.base.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping(value = "/", name = "주식 목록 조회")
    public ResponseEntity<StockListRpDto> getAllStocks() {
        StockListRpDto stockListDto = stockService.getStockList();

        return ResponseEntity.ok(stockListDto);
    }

    @PostMapping(value = "/upload-stocks", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, name = "주식 메타데이터 업로드")
    public ResponseEntity<String> uploadStockListCsvFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".csv")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file. Please upload a CSV file.");
        }

        try {
            stockService.uploadStockListByCsvFile(file);
            return ResponseEntity.ok("File uploaded and processed successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the file: " + e.getMessage());
        }
    }
}
