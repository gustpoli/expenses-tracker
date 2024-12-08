CREATE TABLE categories (
  id SERIAL,
  name VARCHAR(100) NOT NULL,
  icon TEXT,
  PRIMARY KEY (id)
);

CREATE TABLE expenses (
  id SERIAL,
  value DECIMAL(10, 2) NOT NULL,
  description TEXT,
  date DATE NOT NULL,
  category_id INTEGER,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES categories(id) 
);

INSERT INTO categories (name, icon) VALUES  
  ('Alimentação', '🍽️'),
  ('Transporte', '🚗'),
  ('Lazer', '🎉'),
  ('Saúde', '💊'),
  ('Educação', '📚');

INSERT INTO expenses (value, date, description, category_id) VALUES 
  (50.00, '2023-10-01', 'Compra de supermercado', 1),
  (15.00, '2023-10-02', 'Passagem de ônibus', 2),
  (30.00, '2023-10-03', 'Cinema com amigos', 3),
  (20.00, '2023-10-04', 'Medicamento', 4),
  (100.00, '2023-10-05', 'Curso online', 5);