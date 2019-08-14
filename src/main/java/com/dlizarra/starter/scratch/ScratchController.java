package com.dlizarra.starter.scratch;

import com.dlizarra.starter.user.UserDto;
import com.dlizarra.starter.user.UserService;
import com.dlizarra.starter.purchase.PurchaseDto;
import com.dlizarra.starter.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScratchController {

	@RequestMapping(value = "/scratch/hello", method = RequestMethod.GET)
	public String helloWorld(){
		return "World";
	}

}
