# Minhas Anotações

## API REST

 ### Métodos Comuns
  - GET: Buscar Informação
  - PUT: Alterar Informação
  - POST: Inserir Informação
  - DELETE: Excluir Informação
  - PATCH: Aleterar PONTUALMENTE uma Informação

 ### Tipos de Parâmetros
  - Body: { }
  - Query Params: http://localhost:8085/users?nome=Isaac&idade=22 (Parâmetros Opcionais)
  - Route Params: http://localhost:8085/users/{nome}/{idade} (Parâmetros **Obrigatórios**)

## DTO
Classes criadas para comunicação entre classes somente com parâmetros necessários para essa comunicação.