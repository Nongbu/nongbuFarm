package free.model.vo;

import java.sql.Date;

public class Free implements java.io.Serializable{
	private static final long serialVersionUID = 2L;
	
	private int freeNo;
	private String freeTitle;
	private java.sql.Date freeDate;
	private String freeWriter;
	private String freeContent;
	private String originalFileName;
	private String renameFileName;
	private int freeRef;	//참조하는 원글번호, 원글은 자기번호, 댓글과 대댓글도 참조하는 원글번호
	private int freeReplyRef;			//댓글의 자기번호, 대댓글은 참조하는 댓글번호, 원글은 null
	private int freeReplyLev;			//원글은 1/원글의 댓글은 2, 댓글의 댓글이면 3 레밸
	private int freeReplySeq;			//댓글 순번, 최근글이 1번으로 처리함
	private int freeReadCount;			//게시글 읽은 조회수
	
	public Free() {}
	
	
	public int getFreeNo() {
		return freeNo;
	}

	public void setFreeNo(int freeNo) {
		this.freeNo = freeNo;
	}

	public String getFreeTitle() {
		return freeTitle;
	}

	public void setFreeTitle(String freeTitle) {
		this.freeTitle = freeTitle;
	}

	public java.sql.Date getFreeDate() {
		return freeDate;
	}

	public void setFreeDate(java.sql.Date freeDate) {
		this.freeDate = freeDate;
	}

	public String getFreeWriter() {
		return freeWriter;
	}

	public void setFreeWriter(String freeWriter) {
		this.freeWriter = freeWriter;
	}

	public String getFreeContent() {
		return freeContent;
	}

	public void setFreeContent(String freeContent) {
		this.freeContent = freeContent;
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

	public int getFreeRef() {
		return freeRef;
	}

	public void setFreeRef(int freeRef) {
		this.freeRef = freeRef;
	}

	public int getFreeReplyRef() {
		return freeReplyRef;
	}

	public void setFreeReplyRef(int freeReplyRef) {
		this.freeReplyRef = freeReplyRef;
	}

	public int getFreeReplyLev() {
		return freeReplyLev;
	}

	public void setFreeReplyLev(int freeReplyLev) {
		this.freeReplyLev = freeReplyLev;
	}

	public int getFreeReplySeq() {
		return freeReplySeq;
	}

	public void setFreeReplySeq(int freeReplySeq) {
		this.freeReplySeq = freeReplySeq;
	}

	public int getFreeReadCount() {
		return freeReadCount;
	}

	public void setFreeReadCount(int freeReadCount) {
		this.freeReadCount = freeReadCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Free [freeNo=" + freeNo + ", freeTitle=" + freeTitle + ", freeDate=" + freeDate + ", freeWriter="
				+ freeWriter + ", freeContent=" + freeContent + ", originalFileName=" + originalFileName
				+ ", renameFileName=" + renameFileName + ", freeRef=" + freeRef + ", freeReplyRef=" + freeReplyRef
				+ ", freeReplyLev=" + freeReplyLev + ", freeReplySeq=" + freeReplySeq + ", freeReadCount="
				+ freeReadCount + "]";
	}

	public Free(int freeNo, String freeTitle, Date freeDate, String freeWriter, String freeContent,
			String originalFileName, String renameFileName, int freeRef, int freeReplyRef, int freeReplyLev,
			int freeReplySeq, int freeReadCount) {
		super();
		this.freeNo = freeNo;
		this.freeTitle = freeTitle;
		this.freeDate = freeDate;
		this.freeWriter = freeWriter;
		this.freeContent = freeContent;
		this.originalFileName = originalFileName;
		this.renameFileName = renameFileName;
		this.freeRef = freeRef;
		this.freeReplyRef = freeReplyRef;
		this.freeReplyLev = freeReplyLev;
		this.freeReplySeq = freeReplySeq;
		this.freeReadCount = freeReadCount;
	}
}
