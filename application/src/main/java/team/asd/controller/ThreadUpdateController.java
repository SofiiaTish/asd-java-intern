package team.asd.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.asd.dto.ProductDto;
import team.asd.service.ThreadUpdateService;
import team.asd.util.ConverterUtil;

import javax.validation.Valid;

@RestController
@RequestMapping(path = {"/thread"})
public class ThreadUpdateController {

	private final ThreadUpdateService threadUpdateService;

	public ThreadUpdateController(ThreadUpdateService threadUpdateService) {
		this.threadUpdateService = threadUpdateService;
	}

	@PutMapping(path = {"/"})
	public String updateProduct(@RequestBody @Valid ProductDto productDto) {
		return threadUpdateService.threadUpdateProduct(ConverterUtil.convertDtoToProduct(productDto));
	}
}
