-- CREATE SCHEMA IF NOT EXISTS db_projetozeta ;

USE db_projetozeta;


-- DROP TABLE IF EXISTS place;
-- DROP TABLE IF EXISTS tag;
-- DROP TABLE IF EXISTS user;
-- DROP TABLE IF EXISTS place_tag;

CREATE TABLE IF NOT EXISTS place (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS tag (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  tag varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(255) NOT NULL,
  id_provider varchar(255) NOT NULL,
  nome varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS place_tag (
  date date NOT NULL,
  user_id bigint(20) NOT NULL,
  tag_id bigint(20) NOT NULL,
  place_id bigint(20) NOT NULL,
  PRIMARY KEY (place_id, tag_id, user_id),
  CONSTRAINT fk_tag_id FOREIGN KEY (tag_id) REFERENCES tag (id),
  CONSTRAINT fk_place_id FOREIGN KEY (place_id) REFERENCES place (id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=INNODB;