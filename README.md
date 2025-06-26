
# Inventarios - Sistema de Control de Inventario

API REST desarrollada en Java + Spring Boot para la gestión de productos en inventario.

## Características
- Crear nuevos productos
- Consultar productos existentes
- Editar productos por ID
- Eliminar productos por ID
- Filtrar productos por categoría

## Tecnologías
- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database

## Cómo ejecutar

1. Clona el repositorio:
```
git clone https://github.com/jalducin/Inventarios.git
```

2. Navega al proyecto:
```
cd Inventarios
```

3. Ejecuta la app:
```
./mvnw spring-boot:run
```

4. Accede a:
- API: http://localhost:8080/api/products
- Consola H2: http://localhost:8080/h2-console  
  (JDBC URL: `jdbc:h2:mem:inventarios`)

## Estructura

- `/model`: Entidad `Product`
- `/controller`: Controlador REST
- `/service`: Lógica de negocio
- `/repository`: Acceso a datos JPA


## Documentación Swagger

Una vez la aplicación esté en ejecución, puedes acceder a la documentación interactiva en:

- Swagger UI: http://localhost:8080/swagger-ui.html

