INSERT INTO usuario VALUES ("juan", "banga", "pardo", "ourense", "100383373@alumnos.uc3m.es",MD5(100383373));
INSERT INTO usuario VALUES ("alejandro", "de la cruz", "alvarado", "madrid", "100383497@alumnos.uc3m.es",MD5(100383497));
INSERT INTO usuario VALUES ("alejandro", "de la torre", "argota", "madrid", "100383407@alumnos.uc3m.es",MD5(100383407));
INSERT INTO usuario VALUES ("ruben", "diaz", "fernandez", "madrid", "100383451@alumnos.uc3m.es",MD5(100383451));

INSERT INTO producto VALUES (1, "Artículo de Juan", "Abrigos", "Esto es una descipción de Juan", "Aquí debería ir un Base64", 10, "100383373@alumnos.uc3m.es", "100383497@alumnos.uc3m.es", "Vendido");
INSERT INTO producto VALUES (2, "Artículo de Juan", "Accesorios", "Esto es una descipción de Juan", "Aquí debería ir un Base64", 20, "100383373@alumnos.uc3m.es", "100383497@alumnos.uc3m.es", "Reservado");
INSERT INTO producto VALUES (3, "Artículo de Alex de la Cruz", "Zapatos", "Esto es una descipción de Alex de la Cruz", "Aquí debería ir un Base64", 30, "100383497@alumnos.uc3m.es", null, "Disponible");
INSERT INTO producto VALUES (4, "Artículo de Ruben", "Zapatos", "Esto es una descipción de Ruben", "Aquí debería ir un Base64", 40, "100383451@alumnos.uc3m.es", null, "Disponible");