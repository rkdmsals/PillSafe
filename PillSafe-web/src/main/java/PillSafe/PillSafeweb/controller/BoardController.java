package PillSafe.PillSafeweb.controller;

import PillSafe.PillSafeweb.Entity.Board;
import PillSafe.PillSafeweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/board/boardWrite")
    public String BoardWrite(){
        return "boardWrite";
    }

    @PostMapping("/board/boardWritedo")
    public String boardWritedo(Board board){
        System.out.println(board.getTitle());
        System.out.println("안녕");
        boardService.write(board);
        return "redirect:/postSuccess";
    }
}
