

DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS chat_room;
DROP TABLE IF EXISTS user_table;




CREATE TABLE user_table(
  user_id  SERIAL PRIMARY KEY,
  login VARCHAR(255),
  password VARCHAR(255)
);


CREATE TABLE chat_room(
    room_id  SERIAL PRIMARY KEY,
    room_name VARCHAR(255),
    owner INT,
    CONSTRAINT fk_owner
        FOREIGN KEY (owner)
        REFERENCES user_table(user_id)
);



CREATE TABLE message(
    message_id SERIAL PRIMARY KEY,
    text_message text,
    chat_room_id  INT,
    author  INT,
    message_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_chat_room
        FOREIGN KEY (chat_room_id)
        REFERENCES chat_room(room_id),
    CONSTRAINT fk_author
        FOREIGN KEY (author)
        REFERENCES user_table(user_id)
);








--   constraint fk_tests_students
--      foreign key (highestStudent_id) 
--      REFERENCES students (student_id)