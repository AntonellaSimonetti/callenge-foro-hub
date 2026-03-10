# ForoHub API

API RESTful para gestión de foros construida con Spring Boot 4.0.2 y Java 21, con autenticación JWT y MySQL.

## 🚀 Tecnologías

- **Java 21** | **Spring Boot 4.0.2**
- **Spring Security + JWT** (Auth0 java-jwt 4.5.0)
- **Spring Data JPA** + **Hibernate**
- **MySQL 8.0**
- **Lombok** | **SpringDoc OpenAPI 3.0.1**
- **Docker Compose**

## 📋 Requisitos

- JDK 21+
- Maven 3.6+ (o usa `./mvnw`)
- Docker & Docker Compose (opcional)

## ⚡ Inicio Rápido

```bash
# 1. Clonar repositorio
git clone https://github.com/AntonellaSimonetti/callenge-foro-hub.git
cd ForoHub

# 2. Levantar base de datos
docker-compose up -d

# 3. Ejecutar aplicación
./mvnw spring-boot:run
```

La API estará disponible en `http://localhost:8080`

## 🔑 Autenticación

### Login
```bash
POST /login
Content-Type: application/json

{
  "username": "admin",
  "password": "password"
}
```

**Respuesta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

Usa el token en todas las peticiones protegidas:
```bash
Authorization: Bearer {token}
```

## 📌 Endpoints

| Método | Endpoint | Descripción | Auth |
|--------|----------|-------------|------|
| `POST` | `/login` | Autenticación | ❌ |
| `GET` | `/topicos/all?page=0&size=10&sortType=NONE` | Listar tópicos (paginado) | ✅ |
| `GET` | `/topicos/{id}` | Obtener tópico | ✅ |
| `POST` | `/topicos` | Crear tópico | ✅ |
| `PUT` | `/topicos/{id}` | Actualizar tópico | ✅ |
| `DELETE` | `/topicos/{id}` | Eliminar tópico | ✅ |

### Parámetros de Ordenamiento
- `NONE` - Sin ordenar
- `UPPER` - Ascendente por fecha
- `LOWER` - Descendente por fecha

### Ejemplo: Crear Tópico
```bash
POST /topicos
Authorization: Bearer {token}
Content-Type: application/json

{
  "titulo": "¿Cómo usar Spring Security?",
  "mensaje": "Necesito ayuda con JWT...",
  "autor": "Juan Pérez",
  "curso": "Spring Boot"
}
```

## 🗄️ Modelo de Datos

### Topico
```java
{
  "id": 1,
  "titulo": "String",
  "mensaje": "String",
  "fechaCreacion": "2026-02-15T12:30:00",
  "autor": "String",
  "curso": "String"
}
```

### Usuario
```java
{
  "id": 1,
  "username": "String",
  "password": "String (BCrypt)"
}
```

## 📚 Documentación API

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI Spec:** http://localhost:8080/v3/api-docs

## ⚙️ Configuración

### application.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ForoHub
spring.datasource.username=foro_user
spring.datasource.password=foro_pass
api.security.token.secret=${JWT_SECRET:123456789}
```

### Docker Compose
```yaml
# Base de datos MySQL 8.0
# Puerto: 3306
# Database: ForoHub
# Usuario: foro_user
# Password: foro_pass
```

## 🔒 Seguridad

- **Autenticación:** JWT (JSON Web Tokens)
- **Encriptación:** BCrypt para contraseñas
- **Sesiones:** Stateless
- **Endpoints públicos:** `/login`, `/swagger-ui/**`, `/v3/api-docs/**`

⚠️ **Producción:** Cambia `JWT_SECRET` por variable de entorno segura.

## 🐳 Docker

```bash
# Iniciar MySQL
docker-compose up -d

# Ver logs
docker-compose logs -f mysql

# Detener
docker-compose down
```

## 🧪 Testing

```bash
./mvnw test
```

## 📁 Estructura

```
src/main/java/pe/jsaire/forohub/
├── controllers/          # REST Controllers
├── models/              # Entidades JPA
├── repositories/        # JPA Repositories
├── services/            # Lógica de negocio
├── securities/          # Configuración JWT/Security
├── config/              # Configuraciones
└── utils/               # Utilidades y excepciones
```


