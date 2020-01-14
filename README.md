# MiniCourse APP
Projeto desenvolvido como parte de processo seletivo da SOGO. Este projeto é apenas para demonstração.

### Introdução

O projeto foi desenvolvido utilizando os princípios do [DDD(domain-driven-design)](https://en.wikipedia.org/wiki/Domain-driven_design) na sua arquitetura. Por isso possui 3 módulos principais: ***Application***, ***Infrastructure*** e ***Domain***.

##### Domain
Este módulo possue arquitetura [*Hexagonal*](https://fernandofranzini.files.wordpress.com/2019/04/1.png) implementada de uma maneira mais sucinta.

Nesse módulo é possível encontrar:
- Toda lógica de negócios;
- Regras de validações de entrada de usuáio;
- Informações obrigatórias para uma entidade;
- Entidades de domínio;
- Os conceitos e palavras de negócios têm seus objetos no seu código (ubiquitous language);
- Aqui os objetos de domínio são: *Student*, *Professor*, *MiniCourse*;
- Decisão de transações, aqui é decidido se o objeto deve ser persistido ou não;

**dependências**: (Quase nenhuma) Lombok (Clean code), Javax Transaction API. Veja o [pom.xml](https://github.com/odravison/mini-course-app/blob/development/minicourses-domain/pom.xml)

##### Infrastructure
Nesse módulo é possível encontrar:
- Implementação dos meios para o hexágono se comunicar com o mundo exterior;
- Implementação da gerência de armazenamento das entidades persistíveis;

Esse módulo poderia conter muitas outras responsabilidades, como: envio de email, envio de sms, *push notification*, obter informações de configurações, etc. Tudo que se trata do "COMO" fazer alguma comunicação externa, seja ela com um banco de dados, ou com uma API Externa, por exemplo.

**dependências**: minicourse-domain e bibliotecas do Spring necessárias para persistência;

##### Application

Nesse módulo é possível encontrar:

- Interfaces com usuário. No caso, Rest/JSON API mas pode ser páginas html, CLI, etc;
- Artefato executável final (java main class): ponto de partida para iniciar toda a aplicação;
- Após *"buildado"*, é possível encontrar e executar um .JAR;

**dependências**: minicourse-domain, minicourse-infra e várias bibliotecas do Spring/boot que gerenciam Rest/JSON API.

### Como executar

Existem duas maneiras de executar esta aplicação.
Para cada uma das maneiras, é necessário uma série de requisitos;

OBS: Link para acessar a aplicação: `localhost:9000`

#### Executando com o Docker

**Requisitos**: 
- Docker instalado na máquina;
- Docker-compose instalado na máquina;

**Passos**:

1. Faça o clone do repositório;
2. No terminal, entre na pasta do projeto;
3. Execute o comando: `docker-compose -f docker-compose.yml up -d`;
4. Espere os containers startarem e use a aplicação normalmente;



#### Executando com o Maven Wrapper

**Requisitos**: 
- Ambiente Java 8 instalado e configurado o JAVA_HOME;
- Postgresql 10 Instalado na máquina;

**Passos**:
OBS: Alguns comandos utilizados, como: `./ ` funconam apenas em linux, se você utiliza outro SO, por favor, use um comando equivalente.

Configurando banco de dados:
1. crie um banco de dados chamado `minicourses` no seu PostgreSQL e configure-o para escutar na porta 5432;

Subindo aplicação:
1. Faça o clone do repositório;
2. No terminal, entre na pasta do projeto;
3. Execute o comando: `./mvnw package -DskipTests`;
4. Ainda no terminal, entre na pasta do módulo `minicourse-application` e execute o comando `./mvnw spring-boot:run`;
5. A aplicação deve rodar normalmente;

#### Usando a aplicação

Os endpoints existentes se resumem em três principais:
- `/professors`
- `/students`
- `/mini-courses`

O `POST` para esses endpoints com devidos `request body`cria suas respectivas entidades.
O `GET` para os endpoints concatenados de `/all` traz uma lista de todos os registros daquela entidade.

Para fazer login na aplicação, é preciso enviar um `POST` com um JSON no body com os campos `email` e `password`.
Após feito o login você receberár um TOKEN e esse token deve ser usado para as demais chamadas aos ENDPOINTS. Ele deve ser enviado no `HEADER` das requisições subsequentes com a key `Authorization`.

Existe um super usuário padrão com email `admin@admin` e password `master`. Apenas este usuário pode criar um professor.

OBS: Uma das regras de negócio é de não haver mais de um login do mesmo usuário no sistema. Essa regra é contemplada através dos tokens, APENAS O ÚLTIMO TOKEN GERADO É VÁLIDO.
