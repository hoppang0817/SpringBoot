package com.bit.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "files")
@Entity
@Table(name = "tbl_pds")
public class PDSBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	private String pname;
	private String pwriter;
	
	
	//cascade 참조받는 테이블에서 객체에 변경이일어났을때 참조해주는 테이블에서의 객체도 변경된다
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn(name = "pdsno")
	private List<PDSFile> files;
	
}
