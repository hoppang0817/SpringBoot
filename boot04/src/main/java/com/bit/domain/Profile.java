package com.bit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
//toString 할시 
@ToString(exclude = "member")
@Entity
@Table(name = "tbl_profile")
@EqualsAndHashCode(of="fname")
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fno;
	private String fname;
	private boolean current;
	// member와 1:n 관계를 형성시키기위헤 @ManyToOne 사용한다
	@ManyToOne
	private Member member;
}
