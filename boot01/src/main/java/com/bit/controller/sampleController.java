package com.bit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bit.domain.sampleVO;

@RestController
//자동으로 json으로 만들어짐
public class sampleController {

	//따로 html 만들 필요가 없다. 자동적으로 웹페이지가 생성
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World 한글";
	}
	
	@GetMapping("/sample")
	public sampleVO makeSample() {
		sampleVO vo = new sampleVO();
		vo.setVal1("변수1");
		vo.setVal2("변수2");
		vo.setVal3("변수3");
		return vo; //to String 호출
	}
}
