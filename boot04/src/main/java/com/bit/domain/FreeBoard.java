package com.bit.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "replies")
@Entity
@Table(name = "tbl_freeboards")
public class FreeBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	private String title;
	private String writer;
	private String content;
	
	@CreationTimestamp
	private Timestamp regdate;
	@UpdateTimestamp
	private Timestamp updatedate;
	
	//한개의 게시물에 여러개의 답글
	//mappedBy = "board" 에 board는 FreeBoardReply에 선언한 board
	@OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
	private List<FreeBoardReply> replies;
}
