package com.example.board.mapper;

import com.example.board.domain.BoardDto;
import com.example.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

    //페이징 처리된 리스트 조회
    List<BoardDto> selectListPaging(Criteria criteria);

    //특정 페이지 상세보기
    BoardDto selectDetail(long boardSeq);

    // 총 게시글 수
    int totalCount();

    // 새 글 작성
    int newInsertBoard(BoardDto boardDto);

    //답글 작성시 원글 기준 위치 변경
    int changeLocation(HashMap hashMap);

    //답글 작성
    int insertReply(BoardDto boardDto);

    //글 수정(작성자만 가능)
    int updateBoard(BoardDto boardDto);

    // 답글이 존재하는지 여부 확인
    long countReply(long boardSeq);

    //글 삭제(작성자만 가능)
    int deleteBoard(HashMap hashMap);

}
