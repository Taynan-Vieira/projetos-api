CREATE TABLE IF NOT EXISTS PUBLIC.scp02_tarefa
(
    scp02_cod_tarefa SERIAL NOT NULL,
    scp02_titulo     VARCHAR(255),
    fkscp02scp01_cod_projeto BIGINT NOT NULL,

    CONSTRAINT pkscp02_cod_tarefa PRIMARY KEY (scp02_cod_tarefa),
    CONSTRAINT fkscp02scp01_cod_projeto FOREIGN KEY (fkscp02scp01_cod_projeto)
    REFERENCES scp01_projeto (scp01_cod_projeto)
);

INSERT INTO scp02_tarefa (scp02_cod_tarefa, scp02_titulo, fkscp02scp01_cod_projeto)
VALUES (1, 'LEVANTAMENTO DE REQUISITOS', 1),
       (2, 'DEFINICAO DO DER', 1),
       (3, 'DENICAO DO DIAGRAMA DE CLASSES', 2),
       (4, 'DEFINICAO DO FLUXOGRAMA', 2),
       (5, 'DEFINICAO DAS TAREFAS', 3),
       (6, 'CRIACAO DO PROJETO', 3),
       (7, 'CRIAR MODELOS E REPOSITORYS', 4),
       (8, 'CRIAR TESTES DE UNIDADE', 4),
       (9,'CRIAR RECURSOS', 5),
       (10, 'CRIAR TRATAMENTOS DE ERROS', 5),
       (11, 'TESTES AUTOMATIZADOS', 1),
       (12, 'TESTES AUTOMATIZADOS', 2),
       (13, 'TESTE DE INTEGRAÇAO', 3),
       (14, 'TESTE DE INTEGRAÇAO', 4),
       (15, 'TESTES COM SELENIUM', 5),
       (16, 'TESTES COM SELENIUM', 6),
       (17, 'TESTES COM SELENIUM', 7),
       (18, 'TESTES COM SELENIUM', 8),
       (19, 'TESTES COM SELENIUM', 9),
       (20, 'TESTES COM SELENIUM', 10);







