package com.bit.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "board")
@Entity
@Table(name = "tbl_free_replies" )
public class FreeBoardReply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	private String reply;
	private String replyer;
	
	@CreationTimestamp
	private Timestamp replydate;
	@UpdateTimestamp
	private Timestamp updatedate;
	
	//여러개의 답글에 한개의 게시물
	@ManyToOne
	private FreeBoard board;
}
