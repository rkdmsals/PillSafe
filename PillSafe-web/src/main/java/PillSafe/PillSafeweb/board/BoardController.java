//package PillSafe.PillSafeweb.board;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class BoardController {
//
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @GetMapping("/boards")
//    public List<Board> getAllBoards() {
//        return boardRepository.findAll();
//    }
//
//    @PostMapping("/boards")
//    public Board createBoard(@Valid @RequestBody Board board) {
//        return boardRepository.save(board);
//    }

//    @GetMapping("/boards/{id}")
//    public Board getBoardById(@PathVariable(value = "id") Long boardId) {
//        return boardRepository.findById(boardId)
//                .orElseThrow(() -> new ResourceNotFoundException("Board", "id", boardId));
//    }
//
//    @PutMapping("/boards/{id}")
//    public Board updateBoard(@PathVariable(value = "id") Long boardId,
//                             @Valid @RequestBody Board boardDetails) {
//
//        Board board = boardRepository.findById(boardId)
//                .orElseThrow(() -> new ResourceNotFoundException("Board", "id", boardId));
//
//        board.setTitle(boardDetails.getTitle());
//        board.setContent(boardDetails.getContent());
//
//        Board updatedBoard = boardRepository.save(board);
//        return updatedBoard;
//    }
//
//    @DeleteMapping("/boards/{id}")
//    public ResponseEntity<?> deleteBoard(@PathVariable(value = "id") Long boardId) {
//        Board board = boardRepository.findById(boardId)
//                .orElseThrow(() -> new ResourceNotFoundException("Board", "id", boardId));
//
//        boardRepository.delete(board);
//
//        return ResponseEntity.ok().build();
//    }
//}
//
//@Entity
//@Table(name = "boards")
//public class Board extends AuditModel {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotBlank
//    private String title;
//
//    @NotBlank
//    private String content;
//
//    // getters and setters
//}
//
//@Repository
//public interface BoardRepository extends JpaRepository<Board, Long> {
//
//}

