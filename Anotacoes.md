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

## ORM (Object-Relational Mapping)
**ORM (Object-Relational Mapping)** é uma técnica de programação que converte dados entre sistemas incompatíveis usando a programação orientada a objetos. Em Java, você pode usar um ORM como Hibernate ou JPA para interagir com o banco de dados. Em vez de escrever uma consulta SQL como `SELECT * FROM users WHERE id = 1`, você pode escrever algo como `userRepository.findById(1L)`.

Aqui está um exemplo de código usando Spring Data JPA:

```java
// Exemplo de código usando Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
```

## Repository
O **Repository** é um padrão de design que abstrai a lógica necessária para acessar os dados do banco de dados. Ele fornece uma maneira de encapsular a lógica de armazenamento, recuperação e pesquisa de dados, bem como mapear esses dados para o modelo de negócios. O Repository centraliza o código comum de acesso a dados, promovendo a reutilização do código e a separação de preocupações.

Aqui está um exemplo de um repositório em Java usando Spring Boot:

```java
// Exemplo de código de um repositório
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User add(User user) {
        return userRepository.save(user);
    }
}
```
