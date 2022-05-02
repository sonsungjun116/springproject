package myspring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myspring.model.Board;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession session;
	
	public int insert(Board board) {
		return session.insert("insert", board);
	}
	
	public int getCount() {
		return session.selectOne("count"); //count(*)함수는 결과를 한개만 돌려주므로 selectone을 쓰는게 맞다
	}
	
	public List<Board> getBoardList(int page) {
		return session.selectList("list",page);
	}
	
	public void updatecount(int no) {
		session.update("hit",no);
	}
	
	public Board getBoard(int no) {		
		return session.selectOne("content", no);
	}
	
	public int update(Board board) {
		return session.update("update",board);
	}
	
	public int delete(int no) {
		return session.delete("delete", no);
	}
	
}
