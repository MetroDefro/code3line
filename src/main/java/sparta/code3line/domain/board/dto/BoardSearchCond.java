package sparta.code3line.domain.board.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BoardSearchCond {

    private int page;
    private String sort;
    private String search;
    private String writer;
    private LocalDate startDate;
    private LocalDate endDate;

}
