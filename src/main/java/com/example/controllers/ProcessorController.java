package com.example.controllers;

import com.example.model.LojaDTO;
import com.example.services.ProcessorService;
import com.example.utils.BussinessException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("http://localhost:4200")
public class ProcessorController {

    @Autowired
    private ProcessorService service;

    @PostMapping(path = "/processFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    public ResponseEntity<?> processFile(@RequestParam("file") MultipartFile file) throws IOException, BussinessException {
        return service.processFile(file)
                ? new ResponseEntity<String>(HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/listMovimentos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LojaDTO>> listMovimentos() throws IOException {
        return ResponseEntity.ok(service.listMovimentos());
    }

    @DeleteMapping(path = "/reset")
    public void reset() {
        service.limparTable();
    }
}
