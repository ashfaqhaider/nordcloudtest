package nordcloudtest.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NCController {
	
	@RequestMapping("/")
	public String home() {
		return "index is ok";
	}
	

}
