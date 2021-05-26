package com.bit.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_boards")
public class Board {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO) //자동증가 시퀀스가생성됨
	@GeneratedValue(strategy = GenerationType.IDENTITY) //옵션으로 Auto-increment 적용됨
	private Long bno;
	private String title; 
	private String writer;
	private String content;
	
	@CreationTimestamp
	private Timestamp regdate;
	@UpdateTimestamp
	private Timestamp updatedate;
}
