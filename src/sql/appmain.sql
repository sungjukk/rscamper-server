select *
from board_category_tb;

select * from board_tb b, board_category_tb c where b.category_no = 2;
select * from board_tb b, board_category_tb c where b.category_no = 4;

select * from board_tb



show tables;

select * from board_image_tb;


SELECT *
FROM BOARD_BOOKMARK_TB;

select * from record_tb;
select * from board_bookmark_tb;
select * from record_tb;
select * from board_category_tb;

select count(*) from board_bookmark_tb
where target_type = 1 and user_uid = '1YboVQ9qoUf3IxMoPrxI6Wltwut2'; 


show columns from board_category_tb;
show columns from board_bookmark_tb;
insert into board_category_tb
values (7, '일정');

SELECT *
FROM BOARD_BOOKMARK_TB BB
WHERE BB.USER_UID = '1YboVQ9qoUf3IxMoPrxI6Wltwut2';
	
SELECT *
FROM BOARD_BOOKMARK_TB BB, BOARD_TB B
WHERE BB.TARGET_NO = B.BOARD_NO
	AND BB.USER_UID = '1YboVQ9qoUf3IxMoPrxI6Wltwut2';
	
SELECT *
FROM BOARD_BOOKMARK_TB BB, BOARD_TB B, RECORD_TB R
WHERE BB.USER_UID = '1YboVQ9qoUf3IxMoPrxI6Wltwut2'
	조건1 BB.TARGET_TYPE = '1'면, BB.TARGET_NO = B.BOARD_NO;
	조건2 BB.TARGET_TYPE = '3'면, BB.TARGET_NO = R.RECORD_NO;

	
(SELECT BB.TARGET_TYPE, BB.REG_DATE, B.USER_UID, TITLE, TITLE AS STRAPLINE, TITLE AS INTRODUCE, B.BOARD_NO AS PICTURE, U.PHOTO_URL, U.DISPLAY_NAME
FROM BOARD_BOOKMARK_TB BB, BOARD_TB B, USER_TB U
WHERE BB.USER_UID = '1YboVQ9qoUf3IxMoPrxI6Wltwut2' AND BB.TARGET_TYPE = '1' AND BB.TARGET_NO = B.BOARD_NO AND B.USER_UID = U.USER_UID)
UNION
(SELECT BB.TARGET_TYPE, BB.REG_DATE, R.USER_UID, TITLE, R.STRAPLINE, R.INTRODUCE, R.PICTURE, U.PHOTO_URL, U.DISPLAY_NAME
FROM BOARD_BOOKMARK_TB BB, RECORD_TB R, USER_TB U
WHERE BB.USER_UID = '1YboVQ9qoUf3IxMoPrxI6Wltwut2' AND BB.TARGET_TYPE = '3' AND BB.TARGET_NO = R.RECORD_NO AND R.USER_UID = U.USER_UID)
order by REG_DATE desc
limit 0, 10;


SELECT *
FROM BOARD_BOOKMARK_TB BB, BOARD_TB B
WHERE BB.USER_UID = '1YboVQ9qoUf3IxMoPrxI6Wltwut2' AND BB.TARGET_TYPE = '1' AND BB.TARGET_NO = B.BOARD_NO;
SELECT *
FROM BOARD_BOOKMARK_TB BB, RECORD_TB R
WHERE BB.USER_UID = '1YboVQ9qoUf3IxMoPrxI6Wltwut2' AND BB.TARGET_TYPE = '3' AND BB.TARGET_NO = R.RECORD_NO;
	
	
	
SELECT *
FROM BOARD_BOOKMARK_TB BB, BOARD_TB B, USER_TB U
WHERE BB.TARGET_NO = B.BOARD_NO
	AND B.USER_UID = U.USER_UID
	AND BB.USER_UID = '1YboVQ9qoUf3IxMoPrxI6Wltwut2';



(SELECT B.BOARD_NO AS NO, B.REG_DATE, B.USER_UID, TITLE, B.BOARD_NO AS PICTURE, U.PHOTO_URL, U.DISPLAY_NAME, 
		COALESCE((SELECT COUNT(*) FROM BOARD_LIKE_TB BL WHERE BL.TARGET_NO = B.BOARD_NO), 0) LIKE_CNT, 
		(SELECT DISTINCT COALESCE(TARGET_TYPE, 1) FROM BOARD_TB B LEFT JOIN BOARD_LIKE_TB BL ON BL.TARGET_NO = B.BOARD_NO) TARGET_TYPE
 FROM BOARD_TB B, USER_TB U
 WHERE B.USER_UID = U.USER_UID)
UNION
(SELECT R.RECORD_NO AS NO, R.REG_DATE, R.USER_UID, TITLE, R.PICTURE, U.PHOTO_URL, U.DISPLAY_NAME, 
		COALESCE((SELECT COUNT(*) FROM RECORD_LIKE_TB RL WHERE RL.RECORD_NO = R.RECORD_NO), 0) LIKE_CNT, 
		(SELECT DISTINCT COALESCE(TARGET_TYPE, 3) FROM RECORD_TB R LEFT JOIN RECORD_LIKE_TB RL ON RL.RECORD_NO = R.RECORD_NO WHERE RL.RECORD_NO = R.RECORD_NO) TARGET_TYPE
 FROM RECORD_TB R, USER_TB U
 WHERE R.USER_UID = U.USER_UID
 	AND R.ISOPEN = 1)
ORDER BY LIKE_CNT DESC, REG_DATE DESC


select * from record_tb



SELECT B.BOARD_NO AS NO, B.REG_DATE, B.USER_UID, TITLE, B.BOARD_NO AS PICTURE, U.PHOTO_URL, U.DISPLAY_NAME, 
		COALESCE((SELECT COUNT(*) FROM BOARD_LIKE_TB BL WHERE BL.TARGET_NO = B.BOARD_NO), 0) LIKE_CNT, 
		(SELECT DISTINCT COALESCE(TARGET_TYPE, 1) FROM BOARD_TB B LEFT JOIN BOARD_LIKE_TB BL ON BL.TARGET_NO = B.BOARD_NO) TARGET_TYPE
 FROM BOARD_TB B, USER_TB U
 WHERE B.USER_UID = U.USER_UID
SELECT R.RECORD_NO AS NO, R.REG_DATE, R.USER_UID, TITLE, R.PICTURE, U.PHOTO_URL, U.DISPLAY_NAME, 
		COALESCE((SELECT COUNT(*) FROM RECORD_LIKE_TB RL WHERE RL.RECORD_NO = R.RECORD_NO), 0) LIKE_CNT, 
		(SELECT DISTINCT COALESCE(TARGET_TYPE, 3) FROM RECORD_TB R LEFT JOIN RECORD_LIKE_TB RL ON RL.RECORD_NO = R.RECORD_NO WHERE RL.RECORD_NO = R.RECORD_NO) TARGET_TYPE
 FROM RECORD_TB R, USER_TB U
 WHERE R.USER_UID = U.USER_UID
 
 SELECT * FROM BOARD_TB
 SELECT * FROM BOARD_LIKE_TB
 SELECT * FROM BOARD_TB B LEFT JOIN BOARD_LIKE_TB BL ON BL.TARGET_NO = B.BOARD_NO
 
 SELECT B.BOARD_NO AS NO, B.REG_DATE, B.USER_UID, TITLE, B.BOARD_NO AS PICTURE, U.PHOTO_URL, U.DISPLAY_NAME, 
		COALESCE((SELECT COUNT(*) FROM BOARD_LIKE_TB BL WHERE BL.TARGET_NO = B.BOARD_NO), 0) LIKE_CNT
 FROM BOARD_TB B, USER_TB U
 WHERE B.USER_UID = U.USER_UID
 

SELECT C.CATEGORY_NAME, B.BOARD_NO AS NO, B.REG_DATE, B.USER_UID, TITLE, B.BOARD_NO AS PICTURE, U.PHOTO_URL, U.DISPLAY_NAME, 
		COALESCE((SELECT COUNT(*) FROM BOARD_LIKE_TB BL WHERE BL.TARGET_NO = B.BOARD_NO), 0) LIKE_CNT, 
		(SELECT DISTINCT COALESCE(TARGET_TYPE, 1) FROM BOARD_TB B LEFT JOIN BOARD_LIKE_TB BL ON BL.TARGET_NO = B.BOARD_NO) TARGET_TYPE
 FROM BOARD_TB B, BOARD_CATEGORY_TB C, USER_TB U
 WHERE B.USER_UID = U.USER_UID
 	AND B.CATEGORY_NO = C.CATEGORY_NO
 	AND B.CATEGORY_NO = 1d
 ORDER BY LIKE_CNT DESC, REG_DATE DESC
 
 select * from board_category_tb;
 SELECT COUNT(*)
		FROM BOARD_TB
		WHERE CATEGORY_NO = 1
 
 SELECT R.RECORD_NO AS NO, R.REG_DATE, R.USER_UID, TITLE, R.PICTURE, U.PHOTO_URL, U.DISPLAY_NAME, 
		COALESCE((SELECT COUNT(*) FROM RECORD_LIKE_TB RL WHERE RL.RECORD_NO = R.RECORD_NO), 0) LIKE_CNT, 
		(SELECT DISTINCT COALESCE(TARGET_TYPE, 3) FROM RECORD_TB R LEFT JOIN RECORD_LIKE_TB RL ON RL.RECORD_NO = R.RECORD_NO WHERE RL.RECORD_NO = R.RECORD_NO) TARGET_TYPE
 FROM RECORD_TB R, USER_TB U
 WHERE R.USER_UID = U.USER_UID
 ORDER BY LIKE_CNT DESC, REG_DATE DESC
 

 
 
 
 		(SELECT B.BOARD_NO AS NO, B.REG_DATE, B.USER_UID, TITLE, B.BOARD_NO AS PICTURE, U.DISPLAY_NAME, 
				COALESCE((SELECT COUNT(*) FROM BOARD_LIKE_TB BL WHERE BL.TARGET_NO = B.BOARD_NO), 0) LIKE_CNT, 
				(SELECT DISTINCT COALESCE(TARGET_TYPE, 1) FROM BOARD_TB B LEFT JOIN BOARD_LIKE_TB BL ON BL.TARGET_NO = B.BOARD_NO) TARGET_TYPE
		 FROM BOARD_TB B, USER_TB U
		 WHERE B.USER_UID = U.USER_UID)
		UNION
		(SELECT R.RECORD_NO AS NO, R.REG_DATE, R.USER_UID, TITLE, R.PICTURE, U.DISPLAY_NAME, 
				COALESCE((SELECT COUNT(*) FROM RECORD_LIKE_TB RL WHERE RL.RECORD_NO = R.RECORD_NO), 0) LIKE_CNT, 
				(SELECT DISTINCT COALESCE(TARGET_TYPE, 3) FROM RECORD_TB R LEFT JOIN RECORD_LIKE_TB RL ON RL.RECORD_NO = R.RECORD_NO WHERE RL.RECORD_NO = R.RECORD_NO) TARGET_TYPE
		 FROM RECORD_TB R, USER_TB U
		 WHERE R.USER_UID = U.USER_UID
		 	AND R.ISOPEN = 1)
		ORDER BY LIKE_CNT DESC, REG_DATE DESC
 
 
select * from record_tb; 
select * from record_comment_tb;
select * from board_tb;
select * from board_comment_tb;

select r.record_no as no, r.title, rc.reg_date, r.target_type
from record_tb r, record_comment_tb rc
where r.user_uid = '1YboVQ9qoUf3IxMoPrxI6Wltwut2' and r.record_no = rc.record_no
union
select b.board_no as no, b.title, bc.reg_date, (SELECT DISTINCT COALESCE(TARGET_TYPE, 1) FROM BOARD_TB B LEFT JOIN BOARD_LIKE_TB BL ON BL.TARGET_NO = B.BOARD_NO) TARGET_TYPE
from board_tb b, board_comment_tb bc
where b.user_uid = '1YboVQ9qoUf3IxMoPrxI6Wltwut2' and b.board_no = bc.board_no
order by reg_date


 
 select * from record_tb;
 
 
(SELECT DISTINCT COALESCE(TARGET_TYPE, 1), BOARD_NO FROM BOARD_TB B LEFT JOIN BOARD_LIKE_TB BL ON BL.TARGET_NO = B.BOARD_NO)
 
 
-- 모든 게시판
SELECT *, 
	(SELECT COUNT(*) FROM BOARD_LIKE_TB LIKETB WHERE LIKETB.TARGET_NO=B.BOARD_NO AND LIKETB.TARGET_TYPE=1) LIKE_CNT
FROM BOARD_TB B, BOARD_CATEGORY_TB C, USER_TB U, LOCATION_TB L
WHERE C.CATEGORY_NO = B.CATEGORY_NO 
	AND B.USER_UID = U.USER_UID 
	AND U.LOCATION_NO = L.LOCATION_NO
ORDER BY LIKE_CNT DESC
LIMIT 1, 10;

-- 카테고리별 
SELECT *, 
	(SELECT COUNT(*) FROM BOARD_LIKE_TB LIKETB WHERE LIKETB.TARGET_NO=B.BOARD_NO AND LIKETB.TARGET_TYPE=1) LIKE_CNT
FROM BOARD_TB B, BOARD_CATEGORY_TB C, USER_TB U, LOCATION_TB L
WHERE C.CATEGORY_NO = B.CATEGORY_NO 
	AND B.USER_UID = U.USER_UID 
	AND U.LOCATION_NO = L.LOCATION_NO
	AND B.CATEGORY_NO = #{categoryNo}
ORDER BY LIKE_CNT DESC
LIMIT #{page}, #{count}

-- 