DROP TABLE IF EXISTS member CASCADE;
CREATE TABLE member (
                        id          VARCHAR(10) NOT NULL,
                        password    VARCHAR(12) NOT NULL,
                        mem_id      INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY
);