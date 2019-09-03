CREATE TABLE comments (
  id INT(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(32) NOT NULL,
  comment VARCHAR(255) NOT NULL,
  created_at BIGINT UNSIGNED
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
