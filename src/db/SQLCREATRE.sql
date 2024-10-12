CREATE TABLE
  Cliente (
    id_cliente integer primary key autoincrement,
    nome text not null,
    email text not null,
    senha text not null,
    telefone text not null,
    cpf text not null
  )
  
  CREATE TABLE
  Mesa (
    id_mesa integer primary key autoincrement,
    capacidade integer not null,
    numero_mesa integer not null,
    restaurante text,
    FOREIGN KEY (restaurante) REFERENCES Restaurante (cnpj)
  )
  
  CREATE TABLE
  Restaurante (
    id_restaurante integer primary key autoincrement,
    nome text not null,
    email text not null,
    senha text not null,
    endereco text not null,
    "ano_abertura" INTEGER NOT NULL,
    "cnpj" TEXT NOT NULL
  )
  
  CREATE TABLE
  Reserva (
    id_reserva integer primary key autoincrement,
    data_reserva date,
    quantidade_pessoas integer not null,
    valor float not null,
    metodo_pagamento text not null,
    numero_mesa integer,
    restaurante text,
    cliente text,
    FOREIGN KEY (numero_mesa) REFERENCES Mesa (numero_mesa),
    FOREIGN KEY (restaurante) REFERENCES Restaurante (cnpj),
    FOREIGN KEY (cliente) REFERENCES Cliente (cpf)
  )