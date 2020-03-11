CREATE TABLE IF NOT EXISTS PUBLIC.scp01_projeto
(
    scp01_cod_projeto      SERIAL NOT NULL,
    scp01_titulo           VARCHAR(255) NOT NULL,
    scp01_previsao_entrega DATE NOT NULL,

    CONSTRAINT pkscp01_cod_projeto PRIMARY KEY (scp01_cod_projeto)
);

INSERT INTO scp01_projeto(scp01_titulo, scp01_previsao_entrega)
VALUES ('IFOOD', '2020-03-30'),
       ('NETSHOES', '2020-04-20'),
       ('KANUI', '2020-04-29'),
       ('UBER', '2020-04-15'),
       ('NUBANK', '2020-05-26'),
       ('99', '2020-06-01'),
       ('BANCO DO BRASIL', '2020-06-10'),
       ('SPOTFY', '2020-06-21'),
       ('MAGAZINE LUIZA', '2020-07-10'),
       ('AMAZON', '2020-07-23');

