ALTER TABLE video
    ADD COLUMN categoria_id INT;

    ALTER TABLE video
        ADD FOREIGN KEY(categoria_id) REFERENCES categoria(id);