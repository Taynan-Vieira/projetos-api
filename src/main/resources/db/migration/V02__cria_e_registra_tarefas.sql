CREATE TABLE IF NOT EXISTS PUBLIC.scp02_tarefa
(
    scp02_cod_tarefa SERIAL NOT NULL,
    scp02_titulo     VARCHAR(255),
    fkscp02scp01_cod_projeto BIGINT NOT NULL,

    CONSTRAINT pkscp02_cod_tarefa PRIMARY KEY (scp02_cod_tarefa),
    CONSTRAINT fkscp02scp01_cod_projeto FOREIGN KEY (fkscp02scp01_cod_projeto)
    REFERENCES scp01_projeto (scp01_cod_projeto)
);

INSERT INTO scp02_tarefa (scp02_titulo, fkscp02scp01_cod_projeto)
VALUES ('LEVANTAMENTO DE REQUISITOS', 1),
       ('DEFINICAO DO DER', 1),
       ('DENICAO DO DIAGRAMA DE CLASSES', 2),
       ('DEFINICAO DO FLUXOGRAMA', 2),
       ('DEFINICAO DAS TAREFAS', 3),
       ('CRIACAO DO PROJETO', 3),
       ('CRIAR MODELOS E REPOSITORYS', 4),
       ('CRIAR TESTES DE UNIDADE', 4),
       ('CRIAR RECURSOS', 5),
       ('CRIAR TRATAMENTOS DE ERROS', 5),
       ('TESTES AUTOMATIZADOS', 1),
       ('TESTES AUTOMATIZADOS', 2),
       ('TESTE DE INTEGRAÇAO', 3),
       ('TESTE DE INTEGRAÇAO', 4),
       ('TESTES COM SELENIUM', 5),
       ('TESTES COM SELENIUM', 6),
       ('TESTES COM SELENIUM', 7),
       ('TESTES COM SELENIUM', 8),
       ('TESTES COM SELENIUM', 9),
       ('TESTES COM SELENIUM', 10);







