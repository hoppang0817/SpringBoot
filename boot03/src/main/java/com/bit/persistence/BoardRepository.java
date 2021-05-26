package com.bit.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bit.domain.Board;

//인터페이스명은 및에 페이지 참조 조건에 맞는 명을 쓰자
//https://docs.spring.io/spring-data/jpa/docs/2.3.3.RELEASE/reference/html/#jpa.query-methods.query-creation 

//CrudRepository<domain에있는 java클래스명, 기본키의 타입>
public interface BoardRepository extends CrudRepository<Board, Long>{

	//Title조건으로 검색
	public List<Board>findByTitle(String title);
	
	
	//writer조건으로 검색
	public Collection<Board>findByWriter(String writer);
	
	//writer의 like 구문
	public List<Board> findByWriterContaining(String writer);
	
	//title의 like 구문
	public List<Board>findByTitleContaining(String title);

	//content에 like %?구문
	public List<Board>findByContentEndingWith(String content);
	
	//or 조건
	//title like '%?%' or content like '%?%'
	public List<Board>findByTitleContainingOrContentContaining(String title,String content);

	
	//title like '%title%' and bno > ?
	public List<Board>findByTitleContainingAndBnoGreaterThan(String title, Long bno);
	
	
	//bno >? order by bno desc
	public List<Board>findByBnoGreaterThanOrderByBnoDesc(Long bno);

	
	//bno > ? order by bno desc limit ?,?
	//첫? =시작점 if10이라면 11번부터
	//두? = 개수
	public List<Board>findByBnoGreaterThanOrderByBnoDesc(Long bno,Pageable paging);
	
	
	//JPQL
	@Query("SELECT b FROM Board b WHERE b.title like %?1% and b.bno > 0 ORDER BY b.bno desc")
	public List<Board>findByTitle2(String title);
	
	
	//실제 생성한 테이블명은 tbl_board이지만 vo에 생성한 클래스명을 따라 Board라고 적어줌
	@Query("SELECT board.bno, board.title, board.writer, board.regdate"
			+ " FROM Board board WHERE board.title like %?1% and board.bno > 0 ORDER BY board.bno desc")
	public List<Object[]>findByTitle3(String title);
	
	
	@Query("SELECT board FROM Board board WHERE board.bno>0 ORDER BY board.bno DESC")
	public List<Board>findByPage(Pageable pageable);
	
	//@Query 너무 남용하지 않는게 좋음<- JPA의 목적에서 벗어남 <- 목적은 DB에 종속적이지 않고 어느 테이블이라도 사용가능하게 하기위함
	@Query("SELECT board FROM Board board WHERE board.bno>0 AND board.content LIKE %:content% ORDER BY board.bno DESC")
	public List<Board> findByContent(@Param("content")String content);
}
