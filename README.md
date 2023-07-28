# ProxiBanque
Projet développé par Malainine et Guilhem

Il s'agit d'un webservice.

La banque ProxiBanque , banque proche de ses clients, est organisée en réseau d'agences.
Une agence est caractérisée par un numéro d'identification de 5 caractères alphanumérique
et une date de création.

Chaque agence est dirigée par un gérant unique. Le gérant a la responsabilité des conseillers
clientèle de l'agence. Chaque conseiller a la responsabilité au plus de 10 clients.

Un client est caractérisé par : nom, prenom, adresse,code postal,ville, téléphone,
Chaque client peut disposer d'un compte courant et d'un compte épargne.

Tout compte bancaire (qu'il soit compte courant ou compte épargne) est caractérisé par un numéro
de compte, un solde et une date d'ouverture du compte.
En particulier, le Compte courant est caractérisé par une autorisation de découvert par défaut de
1000 euros. Le compte épargne est caractérisé par le taux de rémunération, par défaut à 3%
La banque propose à ses clients les 2 cartes bancaires suivantes : carte visa Electron, visa premier.

La suppression d'un client du Système implique la suppression de tous les comptes associés au
client, ainsi que la désactivation de ses cartes bancaires.

# Technologies
Java, Spring Boot, JPA, PostgreSQL.

# Fonctionnalités

Persistance en base de données d'une liste de conseillers et d'une liste de clients.

# Installation

Clonez le projet dans votre Workspace Eclipse :

git clone https://github.com/guilhem1492/ProxiBanque_GM_BM.git

Importez le et lancez le projet Spring.

# Reste à faire

- 
-
-