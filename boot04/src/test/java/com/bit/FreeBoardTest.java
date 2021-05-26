package com.bit;



import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.FreeBoard;
import com.bit.domain.FreeBoardReply;
import com.bit.persistence.FreeBoardReplyRepository;
import com.bit.persistence.FreeBoardRepository;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Commit
@Log
public class FreeBoardTest {
	
	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeBoardReplyRepository replyRepo;
	
	
	@Test
	public void insertDummy() {
		//System.out.println("*****************테이블생성");
		IntStream.range(1, 200).forEach(i ->{
			FreeBoard board = new FreeBoard();
			board.setTitle("FreeBoard..."+i);
			board.setContent("Free Content..."+i);
			board.setWriter("user"+i%10);
			boardRepo.save(board);
			
		});
	}
	
	@Transactional
	@Test
	//트랜잭션 cascade기능추가
	public void InsertReply2Way() {
		Optional<FreeBoard> result = boardRepo.findById(198L);
		result.ifPresent(board ->{
			List<FreeBoardReply> replies = board.getReplies();
			FreeBoardReply reply = new FreeBoardReply();
			reply.setReply("REPLY..................");
			reply.setReplyer("reply00");
			reply.setBoard(board);
			replies.add(reply);
			board.setReplies(replies);
			boardRepo.save(board);
		});
	}
	
	@Test
	public void InsertReply1Way() {
		FreeBoard board = new FreeBoard();
		board.setBno(199L);
		
		FreeBoardReply reply = new FreeBoardReply();
		reply.setReply("REPLY..................");
		reply.setReplyer("reply00");
		reply.setBoard(board);
		
		replyRepo.save(reply);
	}
	
	//Sort.Direction.DESC ,"bno" -> bno기준으로 내림차순 하겠다.
	@Test
	public void testList1() {
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		boardRepo.findByBnoGreaterThan(0L, pageable).forEach(board->{
			System.out.println("*******"+board.getBno()+":"+ board.getTitle());
		});
	}
	
	//참조하는 테이블과 연결하여 데이터를 가져와야할경우에는 @Transactional를 사용해야함
	//board.getReplies().size() 관련 bno의 답글 개수
	@Transactional
	@Test
	public void testList2() {
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		boardRepo.findByBnoGreaterThan(0L, pageable).forEach(board ->{
			System.out.println("******"+board.getBno()+":"+board.getTitle()+":"+board.getReplies().size());
		});
	}
	
	@Test
	public void testList3() {
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		boardRepo.getPage(pageable).forEach(arr->{
			System.out.println("*****"+Arrays.toString(arr));
		});
	}
	
}
