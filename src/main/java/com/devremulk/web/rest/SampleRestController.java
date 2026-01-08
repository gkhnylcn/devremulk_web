package com.devremulk.web.rest;

import com.devremulk.web.dto.SampleDto;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/samples")
public class SampleRestController {
    private static final Logger logger = LoggerFactory.getLogger(SampleRestController.class);

    private final SampleRestService sampleRestService;
    private final MessageSource messageSource;

    public SampleRestController(SampleRestService sampleRestService, MessageSource messageSource) {
        this.sampleRestService = sampleRestService;
        this.messageSource = messageSource;
    }

    @PostMapping
    public ResponseEntity<SampleDto> create(@Valid @RequestBody SampleDto dto) {
        logger.info("Sample create request received");
        return ResponseEntity.status(HttpStatus.CREATED).body(sampleRestService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SampleDto> update(@PathVariable Long id, @Valid @RequestBody SampleDto dto) {
        logger.info("Sample update request received for id {}", id);
        return ResponseEntity.ok(sampleRestService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Sample delete request received for id {}", id);
        sampleRestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SampleDto> get(@PathVariable Long id) {
        logger.info("Sample get request received for id {}", id);
        return ResponseEntity.ok(sampleRestService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<SampleDto>> list() {
        logger.info("Sample list request received");
        return ResponseEntity.ok(sampleRestService.list());
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting(Locale locale) {
        String message = messageSource.getMessage("sample.greeting", null, locale);
        return ResponseEntity.ok(message);
    }
}
