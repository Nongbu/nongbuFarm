package exchange.model.vo;

import java.sql.Date;

public class Exchange implements java.io.Serializable{
	private static final long serialVersionUID = 2L;
	
	private int exNo;
	private String exTitle;
	private java.sql.Date exDate;
	private String exWriter;
	private String exContent;
	private String originalFileName;
	private String renameFileName;
	private int exRef;	//참조하는 원글번호, 원글은 자기번호, 댓글과 대댓글도 참조하는 원글번호
	private int exReplyRef;			//댓글의 자기번호, 대댓글은 참조하는 댓글번호, 원글은 null
	private int exReplyLev;			//원글은 1/원글의 댓글은 2, 댓글의 댓글이면 3 레밸
	private int exReplySeq;			//댓글 순번, 최근글이 1번으로 처리함
	private int exReadCount;			//게시글 읽은 조회수
	
	public Exchange() {}

	
	public int getExNo() {
		return exNo;
	}

	public void setExNo(int exNo) {
		this.exNo = exNo;
	}

	public String getExTitle() {
		return exTitle;
	}

	public void setExTitle(String exTitle) {
		this.exTitle = exTitle;
	}

	public java.sql.Date getExDate() {
		return exDate;
	}

	public void setExDate(java.sql.Date exDate) {
		this.exDate = exDate;
	}

	public String getExWriter() {
		return exWriter;
	}

	public void setExWriter(String exWriter) {
		this.exWriter = exWriter;
	}

	public String getExContent() {
		return exContent;
	}

	public void setExContent(String exContent) {
		this.exContent = exContent;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRenameFileName() {
		return renameFileName;
	}

	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}

	public int getExRef() {
		return exRef;
	}

	public void setExRef(int exRef) {
		this.exRef = exRef;
	}

	public int getExReplyRef() {
		return exReplyRef;
	}

	public void setExReplyRef(int exReplyRef) {
		this.exReplyRef = exReplyRef;
	}

	public int getExReplyLev() {
		return exReplyLev;
	}

	public void setExReplyLev(int exReplyLev) {
		this.exReplyLev = exReplyLev;
	}

	public int getExReplySeq() {
		return exReplySeq;
	}

	public void setExReplySeq(int exReplySeq) {
		this.exReplySeq = exReplySeq;
	}

	public int getExReadCount() {
		return exReadCount;
	}

	public void setExReadCount(int exReadCount) {
		this.exReadCount = exReadCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Exchange [exNo=" + exNo + ", exTitle=" + exTitle + ", exDate=" + exDate + ", exWriter=" + exWriter
				+ ", exContent=" + exContent + ", originalFileName=" + originalFileName + ", renameFileName="
				+ renameFileName + ", exRef=" + exRef + ", exReplyRef=" + exReplyRef + ", exReplyLev=" + exReplyLev
				+ ", exReplySeq=" + exReplySeq + ", exReadCount=" + exReadCount + "]";
	}

	public Exchange(int exNo, String exTitle, Date exDate, String exWriter, String exContent, String originalFileName,
			String renameFileName, int exRef, int exReplyRef, int exReplyLev, int exReplySeq, int exReadCount) {
		super();
		this.exNo = exNo;
		this.exTitle = exTitle;
		this.exDate = exDate;
		this.exWriter = exWriter;
		this.exContent = exContent;
		this.originalFileName = originalFileName;
		this.renameFileName = renameFileName;
		this.exRef = exRef;
		this.exReplyRef = exReplyRef;
		this.exReplyLev = exReplyLev;
		this.exReplySeq = exReplySeq;
		this.exReadCount = exReadCount;
	}
	
}
