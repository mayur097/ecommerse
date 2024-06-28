CREATE TABLE IF NOT EXISTS category
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product
(
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   description VARCHAR(255),
   name VARCHAR(255),
   available_quantity DOUBLE PRECISION NOT NULL,
   price DECIMAL(38, 2),
   category_id INT,
   CONSTRAINT fk_category
      FOREIGN KEY (category_id)
      REFERENCES category(id)
);


