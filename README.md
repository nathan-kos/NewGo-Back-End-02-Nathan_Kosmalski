# OBJETIVO 
- Implementar uma API REST para realizar cadastro de produtos e controle de estoque e preço destes produtos

#### LEGENDAS:
- US - User Story.
- RN - Regras de Negócio.
- RNF - Requisitos Não Funcionais.


#### US001 - Criar tabela para armazenar informação de produtos com os seguintes campos:
- id: long
- hash: UUID
- nome: varchar(255)
- descricao: text
- ean13: varchar(13)
- preco: numeric(13,2)
- quantidade: numeric(13,2)
- estoque_min: numeric(13,2)
- dtcreate: timestamp
- dtupdate: timestamp
- lativo: boolean

#### US002 - Criar recurso que permite criar, alterar, consultar e remover produtos.
- “Toda a estrutura de persistência e consulta -> endpoints, servlets, db”

#### Regras de Negócio:
 - RN001 - Produto deve persistir as informações id e hash automáticos.
- RN002 - Não permitir cadastrar produtos com nomes duplicados.
- RN003 - Não permitir cadastrar produtos com ean13 duplicados.
- RN004 - Não permitir cadastrar produtos com preço, quantidade ou estoque_min negativo.
- RN005 - Não permitir alterar as informações de id, hash, nome, ean13, dtcreate,
dtupdate pelo usuário
- RN006 - Um novo produto deve ser cadastrado somente com os campos nome,
descrição, ean13, preço, quantidade e estoque mínimo.
- RN007 - Quantidade, estoque mínimo e preço não podem ser nulos, sendo o valor
padrão zero.
- RN008 - Nome não pode ser nulo nem vazio.
- RN009 - dtcreate deve ser preenchido com a timestamp atual, dtupdate preenchido
com nulo e lativo com falso.
- RN010 - Será permitido alterar o campo lativo pelo usuário somente na funcionalidade
específica para tal.
- RN011 - Sempre que atualizar alguma informação do produto seja através de qualquer
funcionalidade, atualizar dtupdate com o timestamp atual.
- RN012 - Não permitir atualizar informações de um produto inativo exceto para caso de
reativação.
- RN013 - A API Rest deve receber como parâmetro do usuário somente o UUID para
fins de interação com os produtos.

#### Requisitos Não Funcionais:
- RNF001 - Utilizar Banco de dados Postgres;

- RNF002 - Utilizar servlets para receber as requisições HTTP;

- RNF003 - Versão do java 8;

- RNF005 - Não deve ser utilizado nenhum framework para criação da API bem como para persistência dos dados;

- RNF004* - Utilizar arquitetura de 3 camadas;

- RNF006* - Utilizar processo gitflow;

- RNF007 - Utilizar uma branch para cada US;

- RNF008 - Utilizar os seguinte prefixos para criação das branchs de acordo com o propósito da mesma
    - bugfix/
    - feature/
    - hotfix/
    - improvement/

- RNF009 - Utilizar o seguinte prefixo para cada commit de acordo com a alteração realizada:
    - feat: (new feature for the user, not a new feature for build script)
    - fix: (bug fix for the user, not a fix to a build script)
    - docs: (changes to the documentation)
    - style: (formatting, missing semicolons, etc; no production code change)
    - refactor: (refactoring production code, eg. renaming a variable)
    - test: (adding missing tests, refactoring tests; no production code change)
    - chore: (updating grunt tasks etc; no production code change)

- RNF011 - Não remover as branches mergeadas para a Main.

- RNF010 - Criar uma coleção Postman para todos os endpoints.

- RNF011 – Modelo de nome do projeto no git: NewGo Back-End 02 – [Nome]