package com.bit;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.Board;
import com.bit.persistence.BoardRepository;

//문법같은거임 이유없이 써줘야함
@ExtendWith(SpringExtension.class)
@SpringBootTest
class Boot03ApplicationTests {

	//주입시키는거임
	@Autowired
	private BoardRepository repo;
	
	@Test
	public void testCreate() {
		System.out.println("테이블 생성 확인");
	}
	
	//더비 date 200개 입력
	@Test
	public void teseInsert200() {
		for(int i =0; i<200;i++) {
			Board board = new Board();
			board.setTitle("제목:"+i);
			board.setContent("내용...."+i+"채우기");
			board.setWriter("user0" +(i%10));
			repo.save(board);
		}
	}
	
	@Test
	public void testByTitle() {
		//두개 결과같음
		repo.findByTitle("제목:177").forEach(board ->System.out.println(board));
		
		List<Board> result = repo.findByTitle("제목:177");
		result.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByWriter() {
		//List의 상위 계층
		Collection<Board> result = repo.findByWriter("user00");
		result.forEach(board -> System.out.println(board));
		
		repo.findByWriter("user00").forEach(board -> System.out.println(board));
	}

	@Test
	public void testByWriterContaining() {
		List<Board> result = repo.findByWriterContaining("05");
		result.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByTitleContaining() {
		List<Board> result = repo.findByTitleContaining("7");
		result.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByContentEndingWith() {
		List<Board> result = repo.findByContentEndingWith("9채우기");
		result.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByTitleOrContent() {
		List<Board> result = repo.findByTitleContainingOrContentContaining("77", "55");
		result.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByTitleAndBno() {
		List<Board> result = repo.findByTitleContainingAndBnoGreaterThan("5",90L);
		result.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByBnoOrderBy() {
		List<Board> result = repo.findByBnoGreaterThanOrderByBnoDesc(90L);
		result.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByBnoOrderByPaging() {
		Pageable paing = PageRequest.of(0, 10);
		List<Board> result = repo.findByBnoGreaterThanOrderByBnoDesc(0L,paing);
		result.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByTitle2() {
		repo.findByTitle2("15").forEach(board ->System.out.println(board));
	}

	@Test
	public void testByTitle3() {
		repo.findByTitle3("15").forEach(arr->System.out.println(Arrays.toString(arr)));
	}
	
	@Test
	public void testByPage() {
		Pageable page = PageRequest.of(0, 10);
		repo.findByPage(page).forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByContent() {
		repo.findByContent("77").forEach(board-> System.out.println(board));
	}

}
