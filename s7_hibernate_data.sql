--
-- Table `AdressesCompletes`
--
INSERT INTO s7_hibernate.AdressesCompletes (id, adresse, ville) VALUES (1, '16 Rue Victor Hugo', 'Paris');
INSERT INTO s7_hibernate.AdressesCompletes (id, adresse, ville) VALUES (2, '3 Rue Nationale', 'Bordeaux');
INSERT INTO s7_hibernate.AdressesCompletes (id, adresse, ville) VALUES (3, '14 Rue de Verdun', 'Tours');
INSERT INTO s7_hibernate.AdressesCompletes (id, adresse, ville) VALUES (4, '21 Boulevard Carnot', 'Marseille');

--
-- Table `Clients`
--
INSERT INTO s7_hibernate.Clients (id, nom, prenom, reduction, adresse) VALUES (2, 'Dupont', 'Cécile', 0, 1);
INSERT INTO s7_hibernate.Clients (id, nom, prenom, reduction, adresse) VALUES (3, 'Navarro', 'Jérôme', 20, 2);
INSERT INTO s7_hibernate.Clients (id, nom, prenom, reduction, adresse) VALUES (4, 'Durand', 'Pierre', 0, 3);

--
-- Table `Commandes`
--
INSERT INTO s7_hibernate.Commandes (id, id_produits, quantite_produits, prix, id_client) VALUES (1, '1,2', '10,12', 60, 2);
INSERT INTO s7_hibernate.Commandes (id, id_produits, quantite_produits, prix, id_client) VALUES (2, '1,2', '14,13', 78.2, 4);
INSERT INTO s7_hibernate.Commandes (id, id_produits, quantite_produits, prix, id_client) VALUES (3, '2,4', '1, 15', 227, 3);

--
-- Table `Fournisseurs`
--
INSERT INTO s7_hibernate.Fournisseurs (id, nom, prenom, id_produits, adresse) VALUES (1, 'Dupont', 'Jean', '1,2', 1);
INSERT INTO s7_hibernate.Fournisseurs (id, nom, prenom, id_produits, adresse) VALUES (2, 'Prudhomme', 'François', '4', 4);

--
-- Table `Fournitures`
--
INSERT INTO s7_hibernate.Fournitures (id, id_produits, prix, id_fournisseur) VALUES (1, '1,2', 38, 1);
INSERT INTO s7_hibernate.Fournitures (id, id_produits, prix, id_fournisseur) VALUES (3, '4', 1500, 2);

--
-- Table `Produits`
--
INSERT INTO s7_hibernate.Produits (id, nom, categorie, espece, prix_unitaire, quantite_disponible) VALUES (1, 'Azalées', 'Fleur', 'Rhododendron', 3.5, 16);
INSERT INTO s7_hibernate.Produits (id, nom, categorie, espece, prix_unitaire, quantite_disponible) VALUES (2, 'Bégonias', 'Fleur', 'Bégonia', 2, 2);
INSERT INTO s7_hibernate.Produits (id, nom, categorie, espece, prix_unitaire, quantite_disponible) VALUES (3, 'Bambou', 'Plante', 'Bambusa', 5.5, 25);
INSERT INTO s7_hibernate.Produits (id, nom, categorie, espece, prix_unitaire, quantite_disponible) VALUES (4, 'Cerisier', 'Plante', 'Prunus', 15, 100);
