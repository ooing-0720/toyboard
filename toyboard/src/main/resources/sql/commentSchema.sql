DROP TABLE IF EXISTS COMMENT CASCADE;
CREATE TABLE COMMENT (
                       seq         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       board_seq       INT NOT NULL,
                       content     VARCHAR(1000) NULL,
                       writer      VARCHAR(10) NOT NULL,
                       regDate     TIMESTAMP NOT NULL,
                       member_id      INT NOT NULL

);