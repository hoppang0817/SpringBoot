package com.bit;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.Board;
import com.bit.persistence.BoardRepository;

//@RunWith(SpringRunner.class)였지만 바겼음
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BoardRepositoryTests {

	//주입
	@Autowired
	private BoardRepository bRepo;
	
	
	@Test
	public void inspect() {
		System.out.println("테스트");
//		Class<?> clz =bRepo.getClass();
//		System.out.println("1.*********:"+clz.getName());
//		Class<?> interfaces[] = clz.getInterfaces();
//		Stream.of(interfaces).forEach(inter-> System.out.println("2.**********:"+inter.getName()));
//		Class<?>superClass = clz.getSuperclass();
//		System.out.println("3.********:"+superClass.getName());
		
	}
	
	@Test
	public void testInsert() {
		//insert into tbl_boards(Title,Content,Writer) value( , , ,);
		Board board = new Board();
		board.setTitle("게시물의 제목");
		board.setContent("게시물 내용 넣기");
		board.setWriter("홍길동");
		
		bRepo.save(board);
	}
	
	@Test
	public void testRead() {
		//select * from tbl_boards where bno=1   L을 사용한이유는 bno타입니 Long이라서
		Optional<Board> board = bRepo.findById(1L);
		System.out.println("********result:"+ board);
	}
	
	@Test
	public void testUpdate() {
		System.out.println("Read First.........................");
		//select * from tbl_boards where bno=2
		Optional<Board> board = bRepo.findById(2L);
		System.out.println("Update Title........................");
		//select한 결과에서 get()제목한후 setTitle로수정
		board.get().setTitle("수정된 제목입니다.");
		System.out.println("Call Save().........................");
		bRepo.save(board.get());
	}
	
	@Test
	public void testDelete() {
		//delete from tbl_boards where bno =2;
		System.out.println("Delete Entity.....................");
		bRepo.deleteById(2L);
	}
}
