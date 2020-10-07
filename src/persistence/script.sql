create database bdalunorest;
use bdalunorest;

create table aluno(id int primary key auto_increment, nome varchar(50), disciplina varchar(50), nota1 double, nota2 double, media double, situacao varchar(50));