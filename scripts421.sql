ALTER TABLE student ADD CONSTRAINT age_constraint CHECK (age > 15);
ALTER TABLE student ADD PRIMARY KEY (name);
ALTER TABLE faculty ADD CONSTRAINT name_color_constraint UNIQUE (name, color);
ALTER TABLE student ALTER age SET DEFAULT 20;