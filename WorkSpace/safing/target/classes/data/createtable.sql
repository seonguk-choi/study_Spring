CREATE TABLE board (
	board_id	        NUMBER,
	member_id	        VARCHAR2(50)	NOT NULL,
	board_title	        VARCHAR2(100)	NOT NULL,
	board_content	    VARCHAR2(3000)	NOT NULL,
	board_writedate	    DATE	        NOT NULL,
	board_updatedate	DATE	        NULL,
	board_read_cnt	    NUMBER	        DEFAULT 0,
	board_like_cnt	    NUMBER	        NULL,
	board_kinds   	    VARCHAR2(20)    NULL,
    CONSTRAINT PK_board_id PRIMARY KEY(board_id),
    CONSTRAINT FK_member_id_board FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE,
    CONSTRAINT CK_board_kinds_board CHECK(board_kinds in ( 'free'  ,  'video' ,  'album' ,   'notice' ,  'review'  ,  'camping' ))
);

CREATE TABLE board_comment (
	comment_id	        NUMBER,
	board_id	        NUMBER	        NOT NULL,
	member_id	        VARCHAR2(20)	NOT NULL,
	comment_content	    VARCHAR2(500)	NULL,
	comment_regdate     DATE	        NOT NULL,
	comment_root	    NUMBER	        NULL,
    CONSTRAINT PK_comment_id PRIMARY KEY(comment_id),
    CONSTRAINT FK_board_id_comment FOREIGN KEY(board_id) REFERENCES board(board_id) ON DELETE CASCADE,
    CONSTRAINT FK_member_id_comment FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE
);

CREATE TABLE board_file (
	board_file_id	    NUMBER,
	board_id	        NUMBER	            NOT NULL,
	member_id	        VARCHAR2(50)	    NOT NULL,
	board_file_name	    VARCHAR2(100)	    NULL,
	board_file_path	    VARCHAR2(1000)	    NULL,
    CONSTRAINT PK_board_file_id PRIMARY KEY(board_file_id),
    CONSTRAINT FK_board_id_file FOREIGN KEY(board_id) REFERENCES board(board_id) ON DELETE CASCADE,
    CONSTRAINT FK_member_id_file FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE
);

CREATE TABLE camping (
	cm_id	        NUMBER	NOT NULL,
	cm_latitude	    VARCHAR2(1000)	NOT NULL,
	cm_longitude	VARCHAR2(1000)	NOT NULL,
	cm_addr	        VARCHAR2(50)	NOT NULL,
	cm_tel	        NUMBER	        NOT NULL,
	cm_img	        VARCHAR2(1000)	NULL,
	cm_content  	VARCHAR2(100)	NULL,
	cm_sfzone	    VARCHAR2(3)     DEFAULT 'n',
	cm_kind	        VARCHAR2(30)    NOT NULL,
    CONSTRAINT PK_cm_id PRIMARY KEY(cm_id),
    CONSTRAINT CK_cm_sfzone CHECK(cm_sfzone IN  ('y' , 'n')),
    CONSTRAINT CK_cm_kind CHECK(cm_kind IN ( '자동차야영장' , '야영장' ))
);

CREATE TABLE product (
	product_num 	NUMBER,
	product_name	VARCHAR2(100)	NOT NULL,
	product_price	NUMBER	        NOT NULL,
	product_stock	NUMBER	        NOT NULL,
	product_date	DATE	        NOT NULL,
    CONSTRAINT PK_product_num PRIMARY KEY(product_num)
);

CREATE TABLE address_sub (
	addr_num    NUMBER	        NOT NULL,
	member_id   VARCHAR2(50)	NOT NULL,
    CONSTRAINT FK_addr_num_address FOREIGN KEY(addr_num) REFERENCES address(addr_num) ON DELETE CASCADE,
    CONSTRAINT FK_member_id_address FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE
);

CREATE TABLE order_ing (
	order_num	        NUMBER,
	member_id	        VARCHAR2(50)	NOT NULL,
	product_num	        NUMBER	        NULL,
	package_num	        NUMBER	        NULL,
	order_state_num	    NUMBER	        NOT NULL,
	order_date	        DATE	        NOT NULL,
	receiver_name	    VARCHAR2(30)	NOT NULL,
	receiver_phone	    VARCHAR2(30)	NOT NULL,
	receiver_addr	    VARCHAR2(100)	NOT NULL,
    CONSTRAINT PK_order_num PRIMARY KEY(order_num),
    CONSTRAINT FK_member_id_order FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE,
    CONSTRAINT FK_product_num_order FOREIGN KEY(product_num) REFERENCES product(product_num) ON DELETE CASCADE,
    CONSTRAINT FK_package_num_order FOREIGN KEY(package_num) REFERENCES product_package(package_num) ON DELETE CASCADE,
    CONSTRAINT FK_order_state_num_order FOREIGN KEY(order_state_num) REFERENCES order_state(order_state_num) ON DELETE CASCADE
);


CREATE TABLE cart (
	cart_num	    NUMBER,
	member_id	    VARCHAR2(50)	NOT NULL,
	product_num 	NUMBER	        NULL,
	package_num 	NUMBER	        NULL,
	product_price	NUMBER	        NOT NULL,
	product_cnt 	NUMBER	        NOT NULL,
    CONSTRAINT PK_cart_num PRIMARY KEY(cart_num),
    CONSTRAINT FK_member_id FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE,
    CONSTRAINT FK_product_num_cart FOREIGN KEY(product_num) REFERENCES product(product_num) ON DELETE CASCADE,
    CONSTRAINT FK_package_num FOREIGN KEY(package_num) REFERENCES product_package(package_num) ON DELETE CASCADE    
);

CREATE TABLE product_file (
	file_num	    NUMBER,
	product_num	    NUMBER	        NOT NULL,
	file_name	    VARCHAR2(100)	NOT NULL,
	file_path	    VARCHAR2(1000)	NOT NULL,
    CONSTRAINT PK_file_num PRIMARY KEY(file_num),
    CONSTRAINT FK_product_num FOREIGN KEY(product_num) REFERENCES product(product_num) ON DELETE CASCADE
);

CREATE TABLE product_package (
	package_num	    NUMBER,
	package_name	VARCHAR2(300)	NOT NULL,
	package_price	VARCHAR2(100)	NOT NULL,
    CONSTRAINT PK_package_num PRIMARY KEY(package_num)
);

CREATE TABLE package_detail (
	product_num	    NUMBER	NOT NULL,
	package_num	    NUMBER	NOT NULL,
    CONSTRAINT PK_product_num_package FOREIGN KEY(product_num) REFERENCES product(product_num) ON DELETE CASCADE,
    CONSTRAINT PK_package_num_package FOREIGN KEY(package_num) REFERENCES product_package(package_num) ON DELETE CASCADE  
);

CREATE TABLE review_product (
	review_num	        NUMBER,
	review_image_num 	NUMBER	        NULL,
	product_num 	    NUMBER	        NULL,
	member_id	        VARCHAR2(50)	NULL,
    package_num	        NUMBER	        NULL,
	review_content	    VARCHAR2(3000)	NOT NULL,
	review_date	        DATE	        NOT NULL,
    CONSTRAINT PK_review_num PRIMARY KEY(review_num),
    CONSTRAINT PK_review_image_num_review FOREIGN KEY(review_image_num) REFERENCES review_imageFile(review_image_num) ON DELETE CASCADE,
    CONSTRAINT FK_product_num_review FOREIGN KEY(product_num) REFERENCES product(product_num) ON DELETE CASCADE,
    CONSTRAINT FK_member_id_review FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE,
    CONSTRAINT FK_package_num_review FOREIGN KEY(package_num) REFERENCES product_package(package_num) ON DELETE CASCADE
);

CREATE TABLE review_imageFile (
	review_image_num	NUMBER	NOT NULL,
	review_image_name	VARCHAR2(100)	NOT NULL,
	review_image_path	VARCHAR2(1000)	NOT NULL,
    CONSTRAINT PK_review_image_num PRIMARY KEY(review_image_num)
);

CREATE TABLE order_result (
	result_num	        NUMBER,
	order_state_num	    NUMBER      	NOT NULL,
	order_date	        DATE	        NOT NULL,
	receiver_name	    VARCHAR2(30)	NOT NULL,
	receiver_phone	    VARCHAR2(30)	NOT NULL,
	receiver_addr	    VARCHAR2(100)	NOT NULL,
	order_num	        NUMBER	        NOT NULL,
	member_id	        VARCHAR2(50)	NOT NULL,
	product_num     	NUMBER	        NULL,
	package_num	        NUMBER      	NULL,
    CONSTRAINT PK_result_num PRIMARY KEY(result_num),
    CONSTRAINT FK_order_state_num FOREIGN KEY(order_state_num) REFERENCES order_state(order_state_num) ON DELETE CASCADE
);

CREATE TABLE board_link (
	link_num	NUMBER	        NOT NULL,
	board_id	NUMBER	        NOT NULL,
	member_id	VARCHAR2(50)	NOT NULL,
	link_url	VARCHAR2(1000)	NULL,
    CONSTRAINT PK_link_num PRIMARY KEY(link_num),
    CONSTRAINT FK_board_id_link FOREIGN KEY(board_id) REFERENCES board(board_id) ON DELETE CASCADE,
    CONSTRAINT FK_member_id_link FOREIGN KEY(member_id) REFERENCES member(member_id) ON DELETE CASCADE
);

CREATE TABLE address (
	addr_num	NUMBER,
    addr_post	NUMBER	        NOT NULL,
	addr_basic	VARCHAR2(100)	NOT NULL,
	addr_detail	VARCHAR2(100)	NULL,	
    CONSTRAINT PK_addr_num PRIMARY KEY(addr_num)
);

CREATE TABLE order_state (
	order_state_num	    NUMBER,
	order_state_name	VARCHAR2(30)	NOT NULL,
    CONSTRAINT PK_order_state_num PRIMARY KEY(order_state_num)
);

CREATE TABLE board_tag (
	tag_num	    NUMBER,
	board_id	NUMBER	        NULL,
	cm_id	    NUMBER	        NULL,
	package_num	NUMBER	        NULL,
	product_num	NUMBER	        NULL,
	tag_key	    VARCHAR2(100)	NULL,
    CONSTRAINT PK_tag_num PRIMARY KEY(tag_num),
    CONSTRAINT FK_board_id_tag FOREIGN KEY(board_id) REFERENCES board(board_id) ON DELETE CASCADE,
    CONSTRAINT FK_cm_id_tag FOREIGN KEY(cm_id) REFERENCES camping(cm_id) ON DELETE CASCADE,
    CONSTRAINT FK_package_num_tag FOREIGN KEY(package_num) REFERENCES product_package(package_num) ON DELETE CASCADE,
    CONSTRAINT FK_product_num_tag FOREIGN KEY(product_num) REFERENCES product(product_num) ON DELETE CASCADE
);

CREATE TABLE member (
	member_id	    VARCHAR2(50),
	member_pw	    VARCHAR2(50)	NOT NULL,
	member_name	    VARCHAR2(50)	NOT NULL,
	member_age	    NUMBER	        NOT NULL,
	member_gender	VARCHAR2(3)     DEFAULT '남',
	member_admin	VARCHAR2(3)     DEFAULT 'n',
	member_filename	VARCHAR2(100)	NULL,
	member_filepath	VARCHAR2(1000)	NULL,
    CONSTRAINT PK_member_id PRIMARY KEY(member_id),
    CONSTRAINT CK_member_gender CHECK(member_gender IN ('남', '여')),
    CONSTRAINT CK_member_admin CHECK(member_admin IN ('y', 'n'))
);

