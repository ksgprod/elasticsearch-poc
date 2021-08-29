package br.com.ksgprod.controller;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ksgprod.controller.filter.ReceivableFilter;
import br.com.ksgprod.controller.response.ReceivableListResponse;
import br.com.ksgprod.service.ReceivableService;

@RestController
@Validated
@RequestMapping("/api/receivable")
public class ReceivableController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReceivableController.class);
	
	private ReceivableService service;
	
	public ReceivableController(ReceivableService service) {
		this.service = service;
	}

	@PostMapping()
    public void init() {
		
		LOGGER.info("stage=init method=ReceivableController.init");
		
		this.service.init();
		
		LOGGER.info("stage=end method=ReceivableController.init");
		
    }
	
	@GetMapping()
    public ReceivableListResponse find(
    		@RequestParam(value = "document", required = false) String document,
    		
    		@DateTimeFormat(iso = ISO.DATE)
            @RequestParam(value = "startDate", required = false) LocalDate startDate,

            @DateTimeFormat(iso = ISO.DATE)
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
    		
    		@NotNull()
    		@RequestParam(value = "page") Integer page,

		    @Valid @NotNull()
		    @Min(value = 1) @Max(value = 50)
		    @RequestParam(value = "quantity") Integer quantity) {
		
		ReceivableFilter filter = new ReceivableFilter()
				.document(document)
				.startDate(startDate)
				.endDate(endDate)
				.page(page)
				.quantity(quantity);
		
		LOGGER.info("stage=init method=ReceivableController.find by filter={}", filter);
		
		ReceivableListResponse response = this.service.find(filter);
		
		LOGGER.info("stage=end method=ReceivableController.find");
		
        return response;
    }
	
	@DeleteMapping()
	public void delete() {
		
		LOGGER.info("stage=init method=ReceivableController.delete");
		
		this.service.delete();
		
		LOGGER.info("stage=end method=ReceivableController.delete");
		
	}

}
