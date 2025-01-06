package app.ebel.staedybucks.controller;

import app.ebel.staedybucks.dto.StockDto;
import app.ebel.staedybucks.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping(value = "/upload-stocks", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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

    @GetMapping(value = "/list")
    public ResponseEntity<Map<String, Object>> getStockList() {
        List<StockDto> stockDtoList = stockService.getStockList();

        Map<String, Object> response = new HashMap<>();
        response.put("data", stockDtoList);

        return ResponseEntity.ok(response);
    }
}
