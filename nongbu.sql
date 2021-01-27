--DROP TABLE USERS CASCADE CONSTRAINTS; 

--drop table notice;
--drop table qna;

CREATE TABLE USERS( -- 우린
    USER_ID VARCHAR(20) CONSTRAINT PK_USERID PRIMARY KEY,
    USER_PWD VARCHAR2(20) NOT NULL,
    USER_NAME VARCHAR2(20) NOT NULL,
    USER_START_DATE DATE NOT NULL,  --수정
    USER_MODIFY_DATE DATE, -- 수정
    PHONE VARCHAR2(50) NOT NULL,
    ADDR VARCHAR2(100) NOT NULL,
    EMAIL VARCHAR2(100) NOT NULL, 
    MY_BLOCK VARCHAR2(20),
    USER_LEV CHAR(5) NOT NULL
);

COMMENT ON COLUMN
users.user_start_date IS '가입날짜';
COMMENT ON COLUMN
users.user_modify_date IS '정보수정날짜';
COMMENT ON COLUMN
USERS.USER_ID IS '아이디';
COMMENT ON COLUMN
USERS.USER_PWD IS '비밀번호';
COMMENT ON COLUMN
USERS.USER_NAME IS '이름';
COMMENT ON COLUMN
USERS.PHONE IS '전화번호';
COMMENT ON COLUMN
USERS.ADDR IS '주소';
COMMENT ON COLUMN
USERS.EMAIL IS '이메일';
COMMENT ON COLUMN
USERS.MY_BLOCK IS '차단회원';
COMMENT ON COLUMN
USERS.USER_LEV IS '사용자분류'; --A이면 구매자 , B이면 판매자, C이면 구매자이자 판매자, D이면 관리자(디폴트 기본값은 A)

INSERT INTO USERS VALUES('gungfill11','gung1','황경필',sysdate,null,'010-1122-2233','경기도 화성시','gung@ict.co.kr',NULL,'A');
INSERT INTO USERS VALUES('worin12','worin12','김우린',sysdate,null,'010-1111-2222','경기도 일산시','worin@ict.co.kr',NULL,'A');
INSERT INTO USERS VALUES('jeewon','jeewon1','이지원',sysdate,null,'010-2220-1101','전남 목포시','jeewon@ict.co.kr',NULL,'B');
INSERT INTO USERS VALUES('sumsu','sumsu3','서민수',sysdate,null,'010-2211-1122','경기도 세종시','sumsu@ict.co.kr',NULL,'B');
INSERT INTO USERS VALUES('heyin','heyin00','이혜인',sysdate,null,'010-2111-1222','서울시 ','heyin@ict.co.kr',NULL,'D');
INSERT INTO USERS VALUES('princess','flower','정가영',sysdate,null,'010-1101-2101','경기도 인천시','heyin@ict.co.kr','gungfill11','D');


CREATE TABLE POINT( -- 우린
    P_NO VARCHAR2(20) CONSTRAINT  PK_PNO PRIMARY KEY,
    P_ID VARCHAR2(20) NOT NULL,
    P_DATE DATE,
    P_UPDATE DATE,
    P_CATEGORY VARCHAR2(20),
    P_BALANCE NUMBER(37) NOT NULL,
   
    CONSTRAINT FK_PID FOREIGN KEY(P_ID) -- P_ID: FOREIGNKEY 지정
        REFERENCES USERS ON DELETE CASCADE -- 참조하는 부모키는 USER 테이블 삭제할때도 같이 삭제 된다.
);

INSERT INTO POINT VALUES('10P001','gungfill11',SYSDATE,'2020/12/13','1',3000);
INSERT INTO POINT VALUES('10P002','worin12',SYSDATE,NULL,'1',2000); 
INSERT INTO POINT VALUES('10P003','sumsu',SYSDATE,NULL,'2',1000);
INSERT INTO POINT VALUES('10P004','heyin',SYSDATE,NULL,'2',5000);
INSERT INTO POINT VALUES('10P005','princess',SYSDATE,NULL,'3',1500);


-- USER 테이블에 새 회원정보가 입력되면 
-- POINT 테이블에 자동으로 새 회원 아이디를 INSERT하게 함

--CREATE OR REPLACE TRIGGER NEW_UPID
--AFTER INSERT ON USERS
--FOR EACH ROW
--BEGIN
--    INSERT INTO POINT
--    VALUES(:NEW.P_ID);
--END;


CREATE TABLE COUPON( -- 우린
    C_NO VARCHAR2(20) CONSTRAINT PK_CNO PRIMARY KEY,
    C_ID VARCHAR2(20) NOT NULL,
    C_DATE DATE,
    C_UPDATE DATE,
    C_CATEGORY VARCHAR2(20),
    
    CONSTRAINT FK_CID FOREIGN KEY(C_ID)
        REFERENCES USERS ON DELETE CASCADE
);

INSERT INTO COUPON VALUES('10C001','gungfill11',SYSDATE,'2020/12/13','1');
INSERT INTO COUPON VALUES('10C002','worin12',SYSDATE,NULL,'1');
INSERT INTO COUPON VALUES('10C003','sumsu',SYSDATE,NULL,'2');
INSERT INTO COUPON VALUES('10C004','heyin',SYSDATE,NULL,'2');
INSERT INTO COUPON VALUES('10C005','princess',SYSDATE,NULL,'3');


--CREATE OR REPLACE TRIGGER NEW_UCID
--AFTER INSERT ON USER
--FOR EACH ROW
--BEGIN
--    INSERT INTO COUPON
--    VALUES(:NEW.C_ID);
--END;


-- FAQ 테이블(민수)

CREATE TABLE FAQ(
   FAQ_NUM   NUMBER,   
    FAQ_CATEGORY CHAR(20) not null,
   FAQ_TITLE   VARCHAR2(50) NOT NULL,
   FAQ_CONTENT VARCHAR2(2000) NOT NULL,
    CONSTRAINT PK_FAQ PRIMARY KEY (FAQ_NUM)
);

COMMENT ON COLUMN FAQ.FAQ_NUM IS '게시글번호';
COMMENT ON COLUMN FAQ.FAQ_CATEGORY IS '게시글분류';
COMMENT ON COLUMN FAQ.FAQ_TITLE IS '게시글제목';
COMMENT ON COLUMN FAQ.FAQ_CONTENT IS '게시글내용';

INSERT INTO FAQ
VALUES (1, '주문/배송', '관리자 게시글', '저희 사이트를 이용해 주셔서 감사합니다.');

INSERT INTO FAQ
VALUES ((select max(FAQ_NUM) + 1 from FAQ), '주문/배송', 'MVC Model2', '웹 어플리케이션 설계 방식 중 MVC 디자인 패턴 모델2 방식의 한 유형입니다.');

INSERT INTO FAQ 
VALUES ((select max(FAQ_NUM) + 1 from FAQ), '환불/교환', '설계방식2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.');

INSERT INTO FAQ 
VALUES ((select max(FAQ_NUM) + 1 from FAQ), '환불/교환', '설계방식3', '3번째 방식은 액션을 이용하는 방식입니다.');

INSERT INTO FAQ 
VALUES ((select max(FAQ_NUM) + 1 from FAQ), '쿠폰/적립금', 'MVC Model2', '웹 어플리케이션 설계 방식 중 MVC 디자인 패턴 모델2 방식의 한 유형입니다.');

INSERT INTO FAQ
VALUES ((select max(FAQ_NUM) + 1 from FAQ), '쿠폰/적립금', '설계방식2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.');

INSERT INTO FAQ
VALUES ((select max(FAQ_NUM) + 1 from FAQ), '판매자', '설계방식2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.');

INSERT INTO FAQ
VALUES ((select max(FAQ_NUM) + 1 from FAQ), '기타', '설계방식2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.');

INSERT INTO FAQ
VALUES ((select max(FAQ_NUM) + 1 from FAQ), '기타', '설계방식2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.');

--SELECT * FROM FAQ;


-- BOARD TABLE SCRIPT FILE 민수

CREATE TABLE BOARD(
   BOARD_NUM   NUMBER,   
    BOARD_CATEGORY CHAR(20) NOT NULL,
   BOARD_WRITER VARCHAR2(20) NOT NULL,
   BOARD_TITLE   VARCHAR2(50) NOT NULL,
   BOARD_CONTENT   VARCHAR2(2000) NOT NULL,
   BOARD_ORIGINAL_FILENAME   VARCHAR2(100),
  BOARD_RENAME_FILENAME VARCHAR2(100),
  BOARD_REF NUMBER,
   BOARD_REPLY_REF   NUMBER,
   BOARD_REPLY_LEV   NUMBER DEFAULT 1,
   BOARD_REPLY_SEQ NUMBER DEFAULT 1,
   BOARD_READCOUNT   NUMBER DEFAULT 0,
   BOARD_DATE   DATE DEFAULT SYSDATE,
  CONSTRAINT PK_BOARD PRIMARY KEY (BOARD_NUM),
  CONSTRAINT FK_BOARD_WRITER FOREIGN KEY (BOARD_WRITER) REFERENCES USERS ON DELETE SET NULL
);

COMMENT ON COLUMN BOARD.BOARD_NUM IS '게시글번호';
COMMENT ON COLUMN BOARD.BOARD_CATEGORY IS '게시글분류';
COMMENT ON COLUMN BOARD.BOARD_WRITER IS '작성자아이디';
COMMENT ON COLUMN BOARD.BOARD_TITLE IS '게시글제목';
COMMENT ON COLUMN BOARD.BOARD_CONTENT IS '게시글내용';
COMMENT ON COLUMN BOARD.BOARD_DATE IS '작성날짜';
COMMENT ON COLUMN BOARD.BOARD_ORIGINAL_FILENAME IS '원본첨부파일명';
COMMENT ON COLUMN BOARD.BOARD_RENAME_FILENAME IS '바뀐첨부파일명';
COMMENT ON COLUMN BOARD.BOARD_REF IS '원글번호';  -- 원글번호
COMMENT ON COLUMN BOARD.BOARD_REPLY_REF IS '참조답글번호';  -- 원글 : 0, 원글의 답글 : 자기번호, 답글의 답글 : 참조답글번호
COMMENT ON COLUMN BOARD.BOARD_REPLY_LEV IS '답글단계'; -- 원글 : 0, 원글의 답글 : 1, 답글의 답글 : 2
COMMENT ON COLUMN BOARD.BOARD_REPLY_SEQ IS '답글순번'; -- 원글 : 0, 같은 원글의 답글일 때 : 1 ....... 순차처리

INSERT INTO BOARD 
VALUES (1, '주문/배송', 'gungfill11', '질문있어요', 'ㄴㄴㄴ', 
NULL, NULL, 1, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (2, '주문/배송', 'gungfill11', '질문있어요2', 'ㅇㅇㅇ', 
NULL, NULL, 2, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (3, '환불/교환', 'gungfill11', '질문있어요3', 'ㅇㅇㅇ', 
NULL, NULL, 3, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (4, '환불/교환', 'gungfill11', '질문있어요4','ㅇㅇㅇ', 
NULL, NULL, 4, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (5, '환불/교환', 'worin12', '질문있어요5', 'ㅇㅇㅇ', 
NULL, NULL, 5, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (6, '쿠폰/적립금', 'worin12', '질문있어요6','ㅇㅇㅇ', 
NULL, NULL, 6, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (7, '쿠폰/적립금', 'worin12', '안녕하세요 질문1', 'ㅇㅇㅇ', 
NULL, NULL, 7, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (8, '쿠폰/적립금', 'worin12', '안녕하세요 질문2', 'ㅇㅇㅇ', 
NULL, NULL, 8, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (9, '쿠폰/적립금', 'worin12', '안녕하세요 질문3', 'ㅇㅇㅇ', 
NULL, NULL, 9, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (10, '판매자', 'sumsu', '안녕하세요 질문4','ㅇㅇㅇ', 
NULL, NULL, 10, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (11, '판매자', 'sumsu', '안녕하세요 질문5', 'ㅇㅇㅇ', 
NULL, NULL, 11, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (12, '기타', 'sumsu', '문의사항있습니다1', 'ㅇㅇㅇ', 
NULL, NULL, 12, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD 
VALUES (13, '기타', 'sumsu', '문의사항있습니다2', 'ㅇㅇㅇ', 
NULL, NULL, 13, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

--SELECT * FROM BOARD;


CREATE TABLE SALE( -- 지원
    SA_NO VARCHAR2(20) CONSTRAINT PK_SANO PRIMARY KEY,
    SA_ID VARCHAR2(20) NOT NULL,
    SA_RATE NUMBER NOT NULL,
    SA_DATE DATE NOT NULL,
    SA_EDATE DATE NOT NULL,
    
    CONSTRAINT FK_SAID FOREIGN KEY(SA_ID)
        REFERENCES USERS  ON DELETE CASCADE
);

INSERT INTO SALE VALUES('SA1001','gungfill11',10, SYSDATE, '21/01/12');


--DROP TABLE PRODUCT CASCADE CONSTRAINTS;

CREATE TABLE PRODUCT( -- 가영
    PR_NO NUMBER CONSTRAINT PK_PRNO PRIMARY KEY,
    PR_ID VARCHAR2(20) NOT NULL,
    PR_CTGR NUMBER(5) NOT NULL,
    PR_NAME VARCHAR2(20) NOT NULL,
    PR_DATE DATE NOT NULL,
    PR_ORIGIN VARCHAR2(50) NOT NULL,
    PR_PRICE NUMBER(37) NOT NULL,
    
    CONSTRAINT FK_PRID FOREIGN KEY(PR_ID)
        REFERENCES USERS ON  DELETE CASCADE
);

INSERT INTO PRODUCT VALUES(1,'gungfill11',1,'고구마','2020/12/12','제주도',5000);
INSERT INTO PRODUCT VALUES(2,'worin12',2,'쌀','2020/10/13','김제',10000);
INSERT INTO PRODUCT VALUES(3,'sumsu',3,'감자','2020/12/10','삼척',3000);
INSERT INTO PRODUCT VALUES(4,'heyin',4,'딸기','2020/11/30','논산',9000);
INSERT INTO PRODUCT VALUES(5,'princess',5,'사과','2020/12/01','인천',4000);




CREATE TABLE REVIEW( --가영
    RE_NO VARCHAR2(20) CONSTRAINT PK_RENO PRIMARY KEY,
    RE_ID VARCHAR2(20) NOT NULL,
    PR_NO NUMBER(20) NOT NULL,
    RE_TITLE VARCHAR2(100) NOT NULL,
    RE_CONTENT VARCHAR2(2000),
    RE_DATE DATE,
    
    CONSTRAINT FK_REID FOREIGN KEY(RE_ID)
        REFERENCES USERS ON DELETE CASCADE,
    CONSTRAINT FK_PRNO FOREIGN KEY(PR_NO)
        REFERENCES PRODUCT ON DELETE CASCADE
);

INSERT INTO REVIEW VALUES('RE1','gungfill11', 1,'고구마대박','고구마대박맛있어용짱','2020/12/13');
INSERT INTO REVIEW VALUES('RE2','worin12',2,'김제 쌀 후기','쌀이 윤기 돌고 맛있어요 짱짱맨','2020/11/30');
INSERT INTO REVIEW VALUES('RE3','sumsu', 3,'존맛탱구리','감자존맛탱구리 강추입니다 ㅎ','2020/12/11');
INSERT INTO REVIEW VALUES('RE4','heyin', 4, '집 나간 입맛이 돌아왔어요','진짜 달고 맛있는 딸기 ㅎㅎ최고','2020/12/01');
INSERT INTO REVIEW VALUES('RE5','princess', 5,'맛있는 사과','최고입니당 부모님도 좋아하세용ㅎ','2020/12/03');


CREATE TABLE FREE( --혜인
   FREENO   NUMBER,   
   FREEWRITER    VARCHAR2(20) NOT NULL,
   FREETITLE   VARCHAR2(50) NOT NULL,
   FREECONTENT   VARCHAR2(2000) NOT NULL,
   ORIGINALFILENAME   VARCHAR2(100),
    RENAMEFILENAME VARCHAR2(100),
    FREE_REF NUMBER,
   FREE_REPLY_REF   NUMBER,
   FREE_REPLY_LEV   NUMBER DEFAULT 1,
   FREE_REPLY_SEQ NUMBER DEFAULT 1,
   FREE_READCOUNT   NUMBER DEFAULT 0,
   FREEDATE   DATE DEFAULT SYSDATE,
  CONSTRAINT PK_FREE PRIMARY KEY (FREENO),
  CONSTRAINT FK_FREEWRITER FOREIGN KEY (FREEWRITER) REFERENCES USERS (USER_ID) ON DELETE SET NULL
);

COMMENT ON COLUMN FREE.FREENO IS '게시글번호';
COMMENT ON COLUMN FREE.FREEWRITER IS '작성자아이디';
COMMENT ON COLUMN FREE.FREETITLE IS '게시글제목';
COMMENT ON COLUMN FREE.FREECONTENT IS '게시글내용';
COMMENT ON COLUMN FREE.FREEDATE IS '작성날짜';
COMMENT ON COLUMN FREE.ORIGINALFILENAME IS '원본첨부파일명';
COMMENT ON COLUMN FREE.RENAMEFILENAME IS '바뀐첨부파일명';
COMMENT ON COLUMN FREE.FREE_REF IS '원글번호';  -- 원글번호
COMMENT ON COLUMN FREE.FREE_REPLY_REF IS '참조답글번호';  -- 원글 : 0, 원글의 답글 : 자기번호, 답글의 답글 : 참조답글번호
COMMENT ON COLUMN FREE.FREE_REPLY_LEV IS '답글단계'; -- 원글 : 0, 원글의 답글 : 1, 답글의 답글 : 2
COMMENT ON COLUMN FREE.FREE_REPLY_SEQ IS '답글순번'; -- 원글 : 0, 같은 원글의 답글일 때 : 1 ....... 순차처리

INSERT INTO FREE 
VALUES (1, 'worin12', '관리자 게시글', '저희 사이트를 이용해 주셔서 감사합니다.', 
NULL, NULL, 1, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO FREE
VALUES (2, 'worin12', 'MVC Model2', '웹 어플리케이션 설계 방식 중 MVC 디자인 패턴 모델2 방식의 한 유형입니다.', 
NULL, NULL, 2, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO FREE 
VALUES (3, 'heyin', '설계방식2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.', 
NULL, NULL, 3, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO FREE 
VALUES (4, 'sumsu', '설계방식3', '3번째 방식은 액션을 이용하는 방식입니다.', 
NULL, NULL, 4, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO FREE 
VALUES (5, 'heyin', 'MVC Model2', '웹 어플리케이션 설계 방식 중 MVC 디자인 패턴 모델2 방식의 한 유형입니다.', 
NULL, NULL, 5, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO FREE 
VALUES (6, 'princess', '설계방식2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.', 
NULL, NULL, 6, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO FREE 
VALUES (7, 'sumsu', '설계방식3', '3번째 방식은 액션을 이용하는 방식입니다.', 
NULL, NULL, 7, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO FREE 
VALUES (8, 'princess', 'MVC Model2', '웹 어플리케이션 설계 방식 중 MVC 디자인 패턴 모델2 방식의 한 유형입니다.', 
NULL, NULL, 8, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO FREE 
VALUES (9, 'worin12', '설계방식2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.', 
NULL, NULL, 9, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO FREE
VALUES (10, 'sumsu', '설계방식3', '3번째 방식은 액션을 이용하는 방식입니다.', 
NULL, NULL, 10, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO FREE 
VALUES (11, 'heyin', 'MVC Model2', '웹 어플리케이션 설계 방식 중 MVC 디자인 패턴 모델2 방식의 한 유형입니다.', 
NULL, NULL, 11, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO FREE
VALUES (12, 'princess', '설계방식2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.', 
NULL, NULL, 12, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);



CREATE TABLE EXCHANGE(
   EXNO   NUMBER,   
   EXWRITER    VARCHAR2(20) NOT NULL,
   EXTITLE   VARCHAR2(50) NOT NULL,
   EXCONTENT   VARCHAR2(2000) NOT NULL,
   ORIGINALFILENAME   VARCHAR2(100),
   RENAMEFILENAME VARCHAR2(100),
   EX_REF NUMBER,
   EX_REPLY_REF   NUMBER,
   EX_REPLY_LEV   NUMBER DEFAULT 1,
   EX_REPLY_SEQ NUMBER DEFAULT 1,
   EX_READCOUNT   NUMBER DEFAULT 0,
   EXDATE   DATE DEFAULT SYSDATE,
  CONSTRAINT PK_EXCHAGNE PRIMARY KEY (EXNO),
  CONSTRAINT FK_EXWRITER FOREIGN KEY (EXWRITER) REFERENCES USERS (USER_ID) ON DELETE SET NULL
);

COMMENT ON COLUMN EXCHANGE.EXNO IS '게시글번호';
COMMENT ON COLUMN EXCHANGE.EXWRITER IS '작성자아이디';
COMMENT ON COLUMN EXCHANGE.EXTITLE IS '게시글제목';
COMMENT ON COLUMN EXCHANGE.EXCONTENT IS '게시글내용';
COMMENT ON COLUMN EXCHANGE.EXDATE IS '작성날짜';
COMMENT ON COLUMN EXCHANGE.ORIGINALFILENAME IS '원본첨부파일명';
COMMENT ON COLUMN EXCHANGE.RENAMEFILENAME IS '바뀐첨부파일명';
COMMENT ON COLUMN EXCHANGE.EX_REF IS '원글번호';  -- 원글번호
COMMENT ON COLUMN EXCHANGE.EX_REPLY_REF IS '참조답글번호';  -- 원글 : 0, 원글의 답글 : 자기번호, 답글의 답글 : 참조답글번호
COMMENT ON COLUMN EXCHANGE.EX_REPLY_LEV IS '답글단계'; -- 원글 : 0, 원글의 답글 : 1, 답글의 답글 : 2
COMMENT ON COLUMN EXCHANGE.EX_REPLY_SEQ IS '답글순번'; -- 원글 : 0, 같은 원글의 답글일 때 : 1 ....... 순차처리

INSERT INTO EXCHANGE 
VALUES (1, 'worin12', '교환 게시글', '교환 서비스를 이용해 주셔서 감사합니다.', 
NULL, NULL, 1, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO EXCHANGE
VALUES (2, 'worin12', 'MVC Mod', ' MVC 디자인 패턴 모델2 방식의 한 유형입니다.', 
NULL, NULL, 2, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO EXCHANGE 
VALUES (3, 'heyin', '교환 글2', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.', 
NULL, NULL, 3, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO EXCHANGE 
VALUES (4, 'sumsu', '섬수교환합니다', '3번째 방식은 액션을 이용하는 방식입니다.', 
NULL, NULL, 4, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO EXCHANGE 
VALUES (5, 'heyin', '혠입니다', '웹 어플리케이션 설계 방식 중 MVC 디자인 패턴 모델2 방식의 한 유형입니다.', 
NULL, NULL, 5, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO EXCHANGE 
VALUES (6, 'princess', '공주 교환 신청합니다.', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.', 
NULL, NULL, 6, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO EXCHANGE 
VALUES (7, 'sumsu', '섬수교신합니다.', '3번째 방식은 액션을 이용하는 방식입니다.', 
NULL, NULL, 7, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO EXCHANGE 
VALUES (8, 'princess', 'MVC Model경', '웹 어플리케이션 설계 방식 중 MVC 디자인 패턴 모델2 방식의 한 유형입니다.', 
NULL, NULL, 8, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO EXCHANGE 
VALUES (9, 'worin12', '설계방식mod필', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.', 
NULL, NULL, 9, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO EXCHANGE
VALUES (10, 'sumsu', '삼시세끼', '3번째 방식은 액션을 이용하는 방식입니다.', 
NULL, NULL, 10, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO EXCHANGE 
VALUES (11, 'heyin', '농부의 텃밭', '웹 어플리케이션 설계 방식 중 MVC 디자인 패턴 모델2 방식의 한 유형입니다.', 
NULL, NULL, 11, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO EXCHANGE
VALUES (12, 'princess', '자유롭게 교환합니다', '설계방식 2번째로는 First Controller 를 사용하는 방식이 있습니다.', 
NULL, NULL, 12, null, DEFAULT, DEFAULT, DEFAULT, DEFAULT);

--민수
CREATE TABLE NOTICE(
  NOTICENO NUMBER CONSTRAINT PK_NOTICENO PRIMARY KEY,
  NOTICETITLE VARCHAR2(100) NOT NULL,
  NOTICEDATE DATE DEFAULT SYSDATE,
  NOTICEWRITER VARCHAR2(15) NOT NULL,
  NOTICECONTENT VARCHAR2(2000),
  ORIGINAL_FILEPATH VARCHAR2(100),
  RENAME_FILEPATH VARCHAR2(100),
  
  CONSTRAINT FK_NOTICEWRITER FOREIGN KEY (NOTICEWRITER) REFERENCES USERS ON DELETE CASCADE
);


INSERT INTO NOTICE VALUES('1', '모든 회원분들 필독해주세요', DEFAULT, 'heyin', '기본 이용방법 및 에티켓에 관한 게시물 내용~ ', NULL, NULL);
INSERT INTO NOTICE VALUES((select max(noticeno) + 1 from notice), '연휴기간 배송관련 공지', DEFAULT, 'heyin', '연휴 기간에는 배송량이 많아서 배송이 지연될 수 있으니 양해바랍니다.', NULL, NULL);
INSERT INTO NOTICE VALUES((select max(noticeno) + 1 from notice), '농부의 텃밭 카카오톡 채널 친구추가하고 쿠폰 받자!', DEFAULT, 'heyin', '카카오톡에서 농부의 텃밭 검색하고 쿠폰발송을 클릭하세요!', NULL, NULL);
INSERT INTO NOTICE VALUES((select max(noticeno) + 1 from notice), '블랙리스트 규정', DEFAULT, 'heyin', '다음과 같은 행위를 하시면 아이디 영구 차단 됩니다.', NULL, NULL);
INSERT INTO NOTICE VALUES((select max(noticeno) + 1 from notice), '수험생 자녀를 둔 부모님을 위한 이벤트!!', DEFAULT, 'heyin', '수험표 인증하시면 20%할인 쿠폰을 드립니다!', NULL, NULL);

COMMENT ON COLUMN
NOTICE.NOTICENO  IS '공지사항번호';
COMMENT ON COLUMN
NOTICE.NOTICETITLE  IS '공지사항제목';
COMMENT ON COLUMN
NOTICE.NOTICEWRITER  IS '공지사항작성자';
COMMENT ON COLUMN
NOTICE.NOTICECONTENT  IS '공지사항내용';
COMMENT ON COLUMN
NOTICE.NOTICEDATE  IS '공지사항등록날짜';
COMMENT ON COLUMN
NOTICE.ORIGINAL_FILEPATH  IS '원본첨부파일명';
COMMENT ON COLUMN
NOTICE.RENAME_FILEPATH  IS '바뀐첨부파일명';


CREATE TABLE EVENT( -- 지원
    E_NO VARCHAR2(20) PRIMARY KEY,
    E_TITLE VARCHAR2(40) NOT NULL,
    E_CONTENT VARCHAR2(2000) NOT NULL,
    E_SDATE DATE NOT NULL,
    E_EDATE DATE NOT NULL
);

INSERT INTO EVENT 
VALUES('E5001', '지금이 기회입니다.', 
           '8만원이상 구매시 배송비 없음.', SYSDATE, SYSDATE+30 );         
           
INSERT INTO EVENT 
VALUES('E5002', '사은품', 
           '3만원이상 구매시 사은품 증정.', SYSDATE, SYSDATE+10 );

--drop table sell;

create table sell(
product_no varchar2(20) constraint pk_sell_no primary key,
seller_id varchar2(20) not null,
seller_product varchar2(200) not null,
user_address varchar2(200) not null,

constraint fk_seller_id foreign key(seller_id)
    references users on delete cascade
);

alter table sell add(price number(20) not null);
alter table sell add(seller_original_filename varchar2(100),
seller_rename_filename varchar2(100));
alter table sell add(seller_date date);
alter table sell add(seller_content varchar2(2000));

COMMENT ON COLUMN
sell.product_no IS '상품번호';
COMMENT ON COLUMN
sell.seller_id IS '판매자아이디';
COMMENT ON COLUMN
sell.seller_product IS '상품명'; 
COMMENT ON COLUMN
sell.user_address IS '구매자주소';
COMMENT ON COLUMN
sell.price is '상품가격';
COMMENT ON COLUMN
sell.seller_original_filename is '원본첨부파일명';
COMMENT ON COLUMN
sell.seller_rename_filename is '수정첨부파일명';
COMMENT ON COLUMN
sell.seller_date is '상품등록날짜';
COMMENT ON COLUMN
sell.seller_content is '상품설명';

insert into sell values('SA1001', 'gungfill11', '배추', '서울시 강북구','32000',null, null, sysdate, null );
insert into sell values('SA1002', 'gungfill11', '사과', '경기도 하남시','30000',null, null, sysdate, null );
insert into sell values('SA1003', 'gungfill11', '배', '전라북도 익산시','32000',null, null, sysdate, null );

COMMIT;