CREATE TABLE IF NOT EXISTS `author` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `first_name` varchar(60) NOT NULL,
                          `last_name` varchar(60) NOT NULL,
                          `book_id` int(11) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `book` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `title` varchar(60) NOT NULL,
                        `description` text NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `book_author_relation` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `book_id` int(11) NOT NULL,
                        `author_id` int(11) NOT NULL,
                        PRIMARY KEY (`id`),
                        FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
                        FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ALTER TABLE `author`
--   ADD KEY `book_id` (`book_id`);
--
-- ALTER TABLE `author`
--     ADD CONSTRAINT `book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`);
-- COMMIT;