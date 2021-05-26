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


//엔티티 클래스 설계
@Getter
@Setter
@ToString

//이 클래스는 엔티티클래스라고 알려줘야함
@Entity
@Table(name ="tbl_boards") //테이블명 *혹시 지정하지않으며 클래스명과 같은 테이블로 생성된다
public class Board {
	
	@Id //기본키 설정
	@GeneratedValue(strategy = GenerationType.AUTO)//디폴트, 특정DB에 맞게 자동증가 기능
	private Long bno;
	private String title;
	private String writer;
	private String content;
	
	@CreationTimestamp // 게시물 작성시간
	private Timestamp regdate;
	@UpdateTimestamp //게시물 최종 수정시간
	private Timestamp updatedate;
}
