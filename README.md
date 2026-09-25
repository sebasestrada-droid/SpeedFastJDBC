# 🚀 SpeedFast JDBC

> Aplicación de escritorio desarrollada en Java para la gestión de pedidos, repartidores y entregas, utilizando **JDBC** para la conexión y persistencia de información en **MySQL**.

---

## 📌 Descripción

**SpeedFast JDBC** es una aplicación desarrollada para la empresa ficticia **SpeedFast**, dedicada a la gestión y entrega de pedidos.

El proyecto permite trabajar con información persistente almacenada en una base de datos MySQL, conectando la aplicación Java mediante **JDBC**.

La aplicación cuenta con una interfaz gráfica desarrollada con **Java Swing**, desde la cual es posible registrar pedidos, registrar repartidores, asociar entregas y consultar los pedidos almacenados.

---

## 🎯 Objetivo

Implementar la conexión entre una aplicación Java y una base de datos relacional mediante JDBC, aplicando operaciones de inserción y consulta de información.

El proyecto busca demostrar el uso de:

- Conexiones JDBC.
- `DriverManager`.
- `PreparedStatement`.
- `ResultSet`.
- Patrón DAO.
- Manejo de excepciones.
- Claves primarias y foráneas.
- Interfaces gráficas con Swing.
- Componentes `JTable`.
- Persistencia de información en MySQL.

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| ☕ Java 17 | Desarrollo de la aplicación |
| 🖥️ Java Swing | Interfaz gráfica |
| 🔌 JDBC | Conexión con la base de datos |
| 🗄️ MySQL | Almacenamiento de información |
| 📦 Maven | Gestión del proyecto y dependencias |
| 💻 IntelliJ IDEA | Entorno de desarrollo |

---

## 🏗️ Arquitectura del proyecto

El proyecto utiliza una estructura organizada por responsabilidades:

```text
SpeedFastJDBC
│
├── src
│   └── main
│       └── java
│           │
│           ├── app
│           │   └── Main.java
│           │
│           ├── conexion
│           │   └── ConexionDB.java
│           │
│           ├── dao
│           │   ├── PedidoDAO.java
│           │   ├── RepartidorDAO.java
│           │   └── EntregaDAO.java
│           │
│           ├── modelo
│           │   ├── Pedido.java
│           │   ├── Repartidor.java
│           │   └── Entrega.java
│           │
│           └── vista
│               └── VentanaPrincipal.java
│
├── database.sql
├── pom.xml
├── README.md
└── .gitignore
