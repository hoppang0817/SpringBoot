package com.bit.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"val3"}) //toString재정의 val3을 없애겠다
public class sampleVO {
	
	private String val1;
	private String val2;
	private String val3;
	
}
