--
-- Table `FullAddress`
--
INSERT INTO s7_hibernate.FullAddress (id, address, city) VALUES (1, '16 Rue Victor Hugo', 'Paris');
INSERT INTO s7_hibernate.FullAddress (id, address, city) VALUES (2, '3 Rue Nationale', 'Bordeaux');
INSERT INTO s7_hibernate.FullAddress (id, address, city) VALUES (3, '14 Rue de Verdun', 'Tours');
INSERT INTO s7_hibernate.FullAddress (id, address, city) VALUES (4, '21 Boulevard Carnot', 'Marseille');
INSERT INTO s7_hibernate.FullAddress (id, address, city) VALUES (5, '8 Rue Jean Macé', 'Créteil');

--
-- Table `Client`
--
INSERT INTO s7_hibernate.Client (id, discount, name, forename, address_id) VALUES (1, 0, 'Dupont', 'Cécile', 1);
INSERT INTO s7_hibernate.Client (id, discount, name, forename, address_id) VALUES (2, 20, 'Navarro', 'Jérôme', 2);
INSERT INTO s7_hibernate.Client (id, discount, name, forename, address_id) VALUES (3, 0, 'Durand', 'Pierre', 3);

--
-- Table `Supplier`
--
INSERT INTO s7_hibernate.Supplier (id, forename, name, address_id) VALUES (1, 'Jean', 'Dupont', 4);
INSERT INTO s7_hibernate.Supplier (id, forename, name, address_id) VALUES (2, 'François', 'Prudhomme', 5);

--
-- Table `Supply`
--
INSERT INTO s7_hibernate.Supply (id, price, supplier_id) VALUES (1, 96, 1);
INSERT INTO s7_hibernate.Supply (id, price, supplier_id) VALUES (2, 1637.5, 2);

--
-- Table `Product`
--
INSERT INTO s7_hibernate.Product (id, availableQuantity, category, name, species, unitPrice, supplier_id, supply_id) VALUES (1,  16, 'Fleur', 'Azalées', 'Rhododendron', 3.5, 1, 1);
INSERT INTO s7_hibernate.Product (id, availableQuantity, category, name, species, unitPrice, supplier_id, supply_id) VALUES (2, 20, 'Fleur', 'Bégonias',  'Bégonia', 2, 1, 1);
INSERT INTO s7_hibernate.Product (id, availableQuantity, category, name, species, unitPrice, supplier_id, supply_id) VALUES (3, 25, 'Plante', 'Bambou', 'Bambusa', 5.5, 2, 2);
INSERT INTO s7_hibernate.Product (id, availableQuantity, category, name, species, unitPrice, supplier_id, supply_id) VALUES (4, 100, 'Plante', 'Cerisier', 'Prunus', 15, 2, 2);

--
-- Table `Ordering`
--
INSERT INTO s7_hibernate.Ordering (id, price, client_id) VALUES (1, 59, 1);
INSERT INTO s7_hibernate.Ordering (id, price, client_id) VALUES (2, 75, 3);
INSERT INTO s7_hibernate.Ordering (id, price, client_id) VALUES (3, 1502, 2);

--
-- Table `OrderContent`
--
INSERT INTO s7_hibernate.OrderContent (ordering_id, product_id, quantity) VALUES (1, 1, 10);
INSERT INTO s7_hibernate.OrderContent (ordering_id, product_id, quantity) VALUES (1, 2, 12);
INSERT INTO s7_hibernate.OrderContent (ordering_id, product_id, quantity) VALUES (2, 1, 14);
INSERT INTO s7_hibernate.OrderContent (ordering_id, product_id, quantity) VALUES (2, 2, 13);
INSERT INTO s7_hibernate.OrderContent (ordering_id, product_id, quantity) VALUES (3, 2, 1);
INSERT INTO s7_hibernate.OrderContent (ordering_id, product_id, quantity) VALUES (3, 4, 15);



