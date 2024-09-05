# User Management API

Esta API Realiza el registro de usuarios, validación de datos y generación de tokens de autenticación.

## Estructura del Proyecto

### Paquetes

- **`com.app.usermanagement.api.controller`**: Contiene los controladores de la API.
- **`com.app.usermanagement.api.dto`**: Contiene los objetos de transferencia de datos.
- **`com.app.usermanagement.api.model`**: Contiene las entidades y modelos de datos.
- **`com.app.usermanagement.api.repository`**: Contiene los repositorios para acceder a la base de datos.
- **`com.app.usermanagement.api.service`**: Contiene la lógica de negocio.

### Controlador de Usuario

**`UserController`** - Controlador REST para la gestión de usuarios.

- **POST /users**
  
  Registra un nuevo usuario. Requiere un objeto `UserProfile` en el cuerpo de la solicitud.
  
  **Request:**
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "SecurePassword123",
    "phones": [
      {"type": "mobile", "number": "123-456-7890"}
    ]
  }
**Response:**
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
