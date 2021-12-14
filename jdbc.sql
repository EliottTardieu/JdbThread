-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : mar. 14 déc. 2021 à 15:10
-- Version du serveur :  8.0.27-0ubuntu0.20.04.1
-- Version de PHP : 7.3.33-1+ubuntu20.04.1+deb.sury.org+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `jdbc`
--

-- --------------------------------------------------------

--
-- Structure de la table `AdressesCompletes`
--

CREATE TABLE `AdressesCompletes` (
  `id` int NOT NULL,
  `adresse` text NOT NULL,
  `ville` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Clients`
--

CREATE TABLE `Clients` (
  `id` int NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `reduction` int NOT NULL,
  `adresse` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Commandes`
--

CREATE TABLE `Commandes` (
  `id` int NOT NULL,
  `id_produits` text NOT NULL,
  `quantite_produits` text NOT NULL,
  `prix` int NOT NULL,
  `id_client` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Fournisseurs`
--

CREATE TABLE `Fournisseurs` (
  `id` int NOT NULL,
  `nom` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prenom` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id_produits` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `adresse` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Fournitures`
--

CREATE TABLE `Fournitures` (
  `id` int NOT NULL,
  `id_produits` text NOT NULL,
  `prix` int NOT NULL,
  `id_fournisseur` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Produits`
--

CREATE TABLE `Produits` (
  `id` int NOT NULL,
  `nom` varchar(100) NOT NULL,
  `categorie` varchar(100) NOT NULL,
  `espece` varchar(100) NOT NULL,
  `prix_unitaire` int NOT NULL,
  `quantite_disponible` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `AdressesCompletes`
--
ALTER TABLE `AdressesCompletes`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Clients`
--
ALTER TABLE `Clients`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_foreign_key_adresse` (`adresse`);

--
-- Index pour la table `Commandes`
--
ALTER TABLE `Commandes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cmd_foreign_key_cli` (`id_client`);

--
-- Index pour la table `Fournisseurs`
--
ALTER TABLE `Fournisseurs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `four_foreign_key_adresse` (`adresse`);

--
-- Index pour la table `Fournitures`
--
ALTER TABLE `Fournitures`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fr_foreign_key_four` (`id_fournisseur`);

--
-- Index pour la table `Produits`
--
ALTER TABLE `Produits`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `AdressesCompletes`
--
ALTER TABLE `AdressesCompletes`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Clients`
--
ALTER TABLE `Clients`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Commandes`
--
ALTER TABLE `Commandes`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Fournisseurs`
--
ALTER TABLE `Fournisseurs`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Fournitures`
--
ALTER TABLE `Fournitures`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Produits`
--
ALTER TABLE `Produits`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Clients`
--
ALTER TABLE `Clients`
  ADD CONSTRAINT `fk_foreign_key_adresse` FOREIGN KEY (`adresse`) REFERENCES `AdressesCompletes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `Commandes`
--
ALTER TABLE `Commandes`
  ADD CONSTRAINT `cmd_foreign_key_cli` FOREIGN KEY (`id_client`) REFERENCES `Clients` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `Fournisseurs`
--
ALTER TABLE `Fournisseurs`
  ADD CONSTRAINT `four_foreign_key_adresse` FOREIGN KEY (`adresse`) REFERENCES `AdressesCompletes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `Fournitures`
--
ALTER TABLE `Fournitures`
  ADD CONSTRAINT `fr_foreign_key_four` FOREIGN KEY (`id_fournisseur`) REFERENCES `Fournisseurs` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
