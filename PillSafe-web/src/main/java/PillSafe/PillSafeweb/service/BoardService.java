package PillSafe.PillSafeweb.service;

import PillSafe.PillSafeweb.Entity.Board;
import PillSafe.PillSafeweb.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    public void write(Board board){
        boardRepository.save(board); // 이렇게 생성한 서비스는 다시 컨트롤러에서 사용할 것
    }
}