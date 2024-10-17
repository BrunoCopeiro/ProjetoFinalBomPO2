Sistema de Gestão


Descrição do Projeto

Este projeto é um sistema de gestão que permite a autenticação de usuários, cadastro de clientes e gerenciamento de uma agenda.
Ele é desenvolvido em Java utilizando bibliotecas Swing/JavaFX para a interface gráfica e MySQL como banco de dados.

Funcionalidades


Tela de Login


- Autenticação de usuários com nome de usuário e senha.
- Mensagem de erro em caso de falha na autenticação.


![Captura de tela 2024-10-17 165449](https://github.com/user-attachments/assets/b98f6c58-e500-4f47-9d49-f4afc64cc195)



- Tela Principal

Menu com opções para "Cadastro de Usuários", "Cadastro de Clientes", "Agenda e "Opções".

Acesso restrito a usuários autenticados.

![Captura de tela 2024-10-17 165507](https://github.com/user-attachments/assets/0872a65b-feef-42a0-bf7e-e3e85535abca)



Cadastro de Usuários

Formulário para inserção, edição e exclusão de usuários.

- Campos: nome, e-mail, nome de usuário e senha.
- Validação de duplicidade de nomes de usuário.


  ![Captura de tela 2024-10-17 165524](https://github.com/user-attachments/assets/5d001cc2-b5b0-4779-ba4d-f5fb3a35ffe6)


 
 Cadastro de Clientes

Formulário para gerenciar clientes.
Campos: nome, endereço, telefone, e-mail e CPF/CNPJ.


![Captura de tela 2024-10-17 165534](https://github.com/user-attachments/assets/40c55146-9b40-4dc2-8b9f-2eedcbbe58ff)


Agenda

Registro com data, horário, descrição e cliente associado.
Exibição em formato de tabela (dia, semana, mês).


![Captura de tela 2024-10-17 165545](https://github.com/user-attachments/assets/a0b9c1ea-da4b-45b1-a2ca-7130ac7419e4)


Estrutura do Projeto Utilizando o NetBeans

![Captura de tela 2024-10-17 170058](https://github.com/user-attachments/assets/2f4e40a5-1935-4f77-8a99-468f3ae87378)


Tecnologias Utilizadas


Linguagem de Programação: Java (Swing/JavaFX)
Banco de Dados: MySQL
Gerenciamento de Banco de Dados: MySQL Workbench
Conexão com Banco de Dados: JDBC
Instruções de Execução
Configuração do Banco de Dados

Utilize o MySQL Workbench para criar um banco de dados local.
Execute os scripts SQL fornecidos na seção abaixo para criar as tabelas e dados iniciais.
Executando o Projeto

