CREATE TABLE IF NOT EXISTS PUBLIC.scp01_projeto
(
    scp01_cod_projeto      SERIAL NOT NULL,
    scp01_titulo           VARCHAR(255),
    scp01_previsao_entrega DATE,

    CONSTRAINT pkscp01_cod_projeto PRIMARY KEY (scp01_cod_projeto)
);

INSERT INTO scp01_projeto(scp01_cod_projeto, scp01_titulo, scp01_previsao_entrega)
VALUES (1, 'IFOOD', '2020-03-30'),
       (2, 'NETSHOES', '2020-04-20'),
       (3, 'KANUI', '2020-04-29'),
       (4, 'UBER', '2020-04-15'),
       (5, 'NUBANK', '2020-05-26'),
       (6, '99', '2020-06-01'),
       (7, 'BANCO DO BRASIL', '2020-06-10'),
       (8, 'SPOTFY', '2020-06-21'),
       (9, 'MAGAZINE LUIZA', '2020-07-10'),
       (10, 'AMAZON', '2020-07-23');

