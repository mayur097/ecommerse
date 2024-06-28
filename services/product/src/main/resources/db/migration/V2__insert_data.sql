-- Insert data into category table
INSERT INTO category (description, name) VALUES
('Electronics and gadgets', 'Electronics'),
('Books and literary works', 'Books'),
('Clothing and apparel', 'Clothing'),
('Home and kitchen appliances', 'Home & Kitchen'),
('Health and personal care', 'Health & Personal Care');

-- Insert data into product table
INSERT INTO product (description, name, available_quantity, price, category_id) VALUES
('Smartphone with 6GB RAM', 'Smartphone', 100, 299.99, 1),
('Wireless Bluetooth Headphones', 'Headphones', 50, 59.99, 1),
('Mystery Novel', 'Mystery Book', 200, 14.99, 2),
('Men\'s Casual T-Shirt', 'T-Shirt', 150, 19.99, 3),
('Non-stick Frying Pan', 'Frying Pan', 75, 24.99, 4),
('Vitamin D Supplement', 'Vitamin D', 300, 12.99, 5),
('4K Ultra HD Smart TV', 'Smart TV', 20, 499.99, 1),
('Romantic Novel', 'Romance Book', 180, 9.99, 2),
('Women\'s Denim Jacket', 'Denim Jacket', 80, 49.99, 3),
('Blender with multiple speeds', 'Blender', 40, 39.99, 4),
('Organic Skincare Lotion', 'Skincare Lotion', 120, 15.99, 5);
