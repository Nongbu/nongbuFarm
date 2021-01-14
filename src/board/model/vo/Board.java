package board.model.vo;

import java.sql.Date;

public class Board implements java.io.Serializable {
	private static final long serialVersionUID = 3L;
	
	private int boardNum;				//게시글 번호
	private String boardCategory;		//게시글 카테고리
	private String boardWriter;			//게시글 작성자 아이디
	private String boardTitle;			//게시글 제목
	private String boardContent;			//게시글 내용
	private String boardOriginalFileName;	//게시글 첨부파일 원래 이름
	private String boardRenameFileName;	//게시글 첨부파일 바뀐 이름
	private int boardRef;	//참조하는 원글번호, 원글은 자기번호, 댓글과 대댓글도 참조하는 원글번호
	private int boardReplyRef;			//댓글의 자기번호, 대댓글은 참조하는 댓글번호, 원글은 null
	private int boardReplyLev;			//원글은 1/원글의 댓글은 2, 댓글의 댓글이면 3 레밸
	private int boardReplySeq;			//댓글 순번, 최근글이 1번으로 처리함
	private int boardReadCount;			//게시글 읽은 조회수
	private java.sql.Date boardDate;		//게시글 등록 날짜
	
	public Board() {}

	public Board(int boardNum, String BoardCategory, String boardWriter, String boardTitle, String boardContent, String boardOriginalFileName,
			String boardRenameFileName, int boardRef, int boardReplyRef, int boardReplyLev, int boardReplySeq,
			int boardReadCount, Date boardDate) {
		super();
		this.boardNum = boardNum;
		this.boardCategory = boardCategory;
		this.boardWriter = boardWriter;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardOriginalFileName = boardOriginalFileName;
		this.boardRenameFileName = boardRenameFileName;
		this.boardRef = boardRef;
		this.boardReplyRef = boardReplyRef;
		this.boardReplyLev = boardReplyLev;
		this.boardReplySeq = boardReplySeq;
		this.boardReadCount = boardReadCount;
		this.boardDate = boardDate;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardOriginalFileName() {
		return boardOriginalFileName;
	}

	public void setBoardOriginalFileName(String boardOriginalFileName) {
		this.boardOriginalFileName = boardOriginalFileName;
	}

	public String getBoardRenameFileName() {
		return boardRenameFileName;
	}

	public void setBoardRenameFileName(String boardRenameFileName) {
		this.boardRenameFileName = boardRenameFileName;
	}

	public int getBoardRef() {
		return boardRef;
	}

	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}

	public int getBoardReplyRef() {
		return boardReplyRef;
	}

	public void setBoardReplyRef(int boardReplyRef) {
		this.boardReplyRef = boardReplyRef;
	}

	public int getBoardReplyLev() {
		return boardReplyLev;
	}

	public void setBoardReplyLev(int boardReplyLev) {
		this.boardReplyLev = boardReplyLev;
	}

	public int getBoardReplySeq() {
		return boardReplySeq;
	}

	public void setBoardReplySeq(int boardReplySeq) {
		this.boardReplySeq = boardReplySeq;
	}

	public int getBoardReadCount() {
		return boardReadCount;
	}

	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}

	public java.sql.Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(java.sql.Date boardDate) {
		this.boardDate = boardDate;
	}
	
	public String getBoardCategory() {
		return boardCategory;
	}

	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Board [boardNum=" + boardNum + ", boardCategory=" + boardCategory + ", boardWriter=" + boardWriter
				+ ", boardTitle=" + boardTitle + ", boardContent=" + boardContent + ", boardOriginalFileName="
				+ boardOriginalFileName + ", boardRenameFileName=" + boardRenameFileName + ", boardRef=" + boardRef
				+ ", boardReplyRef=" + boardReplyRef + ", boardReplyLev=" + boardReplyLev + ", boardReplySeq="
				+ boardReplySeq + ", boardReadCount=" + boardReadCount + ", boardDate=" + boardDate + "]";
	}

}

