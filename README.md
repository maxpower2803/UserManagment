# User Management API

Esta API proporciona funcionalidades para el registro de usuarios, validación de datos y generación de tokens de autenticación. Está construida utilizando Spring Boot y emplea JWT para la gestión de tokens.

## Estructura del Proyecto

### Paquetes

- com.app.usermanagement.api.controller: Contiene los controladores de la API.
- com.app.usermanagement.api.dto: Contiene los objetos de transferencia de datos.
- com.app.usermanagement.api.model: Contiene las entidades.
- com.app.usermanagement.api.repository: Contiene los repositorios para acceder a la base de datos.
- com.app.usermanagement.api.service: Contiene la lógica de negocio.

### Controlador de Usuario

UserController - Controlador REST para la gestión de usuarios.

- POST /users
  
  Registra un nuevo usuario.
  
  Request:
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "SecurePassword123",
    "phones": [
      {"type": "mobile", "number": "123-456-7890"}
    ]
  }
  ```

  Response Exitoso:
  ```json
  {
    "id": "uuid",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "created": "2024-09-05T12:34:56",
    "modified": "2024-09-05T12:34:56",
    "lastLogin": "2024-09-05T12:34:56",
    "token": "jwt-token",
    "active": true,
    "phones": [
      {"type": "mobile", "number": "123-456-7890"}
    ]
  }
  ```
  Response Error:
  ```json
  {
    "mensaje": "Error descripción"
  }
  ```
### DTOs

- UserResponse: Corresponde a la respuesta que se devuelve después de registrar un usuario.

### Servicios

UserService - Servicio para la gestión de usuarios.

- Métodos Principales:
  - saveUser(UserProfile user): Guarda un nuevo usuario en la base de datos y genera un token de autenticación.
  - generateToken(String email): Genera un token JWT para un usuario basado en su email.

### Configuración

- application.properties:
  
  - password.regex: Expresión regular para validar contraseñas.
  - email.regex: Expresión regular para validar correos electrónicos.
