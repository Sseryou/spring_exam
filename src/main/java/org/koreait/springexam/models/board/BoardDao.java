package org.koreait.springexam.models.board;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardDao {

    private final JdbcTemplate jdbcTemplate;

    private Board boardMapper(ResultSet rs, int i) throws SQLException{
        Board board = new Board();
        board.setId(rs.getLong("ID"));
        board.setSubject(rs.getString("SUBJECT"));
        board.setContent(rs.getString("CONTENT"));
        board.setRegDt(rs.getTimestamp("REGDT").toLocalDateTime());

        Timestamp modDt = rs.getTimestamp("MODDT");
        if(modDt != null){
            board.setModDt(modDt.toLocalDateTime());
        }
        return board;
    }

    public List<Board> gets(){

        String sql = "SELECT * FROM BOARD ORDER BY ID DESC";
        List<Board> boards = jdbcTemplate.query(sql, this::boardMapper);
        return boards;

    }

    public Board get(Long id){
        try{
            String sql = "SELECT * FROM BOARD WHERE ID = ?";
            Board board = jdbcTemplate.queryForObject(sql,this::boardMapper, id);
            return board;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public boolean insert(Board board){
        String sql = "INSERT INTO BOARD (ID, SUBJECT, CONTENT) " +
                " VALUES (SEQ_BOARD.nextval, ?, ?)";
        int cnt = jdbcTemplate.update(sql, board.getSubject(), board.getContent());

        return cnt > 0;
    }





}
