# Inventarios - Sistema de Control de Inventario

Una solución **Java + Spring Boot** para gestionar productos en inventario con:

* **API REST** CRUD
* **Interfaz web** con Thymeleaf y Bootstrap
* **Actualización en tiempo real** vía WebSocket (STOMP)
* **Documentación Swagger** y **H2 Database** en memoria

---

## Características

1. **API REST** (`/api/products`)

   * Crear, consultar, editar y eliminar productos
   * Filtrar por categoría

2. **Interfaz web** (`/products`)

   * Listado, creación y edición de productos
   * Menú desplegable con categorías predefinidas
   * Formularios validados con Spring Validation

3. **Real-Time Updates**

   * WebSockets en `/ws` con STOMP
   * Notificaciones automáticas en `/topic/products`
   * La tabla en la UI se refresca sin recargar la página

4. **Datos pre-cargados**

   * Tabla `Category` inicializada al arranque con categorías:
     electrónica, línea blanca, electrodomésticos, muebles,
     motocicletas, llantas, acumuladores, bicicletas,
     teléfonos celulares, computadoras, juguetes

5. **Persistencia y Consola H2**

   * Base de datos en memoria H2 (runtime)
   * Consola web en `/h2-console` para inspección

6. **Documentación interactiva**

   * Swagger UI en `/swagger-ui.html`

---

## Tecnologías y Dependencias

* **Java 17**
* **Spring Boot 3**
* **Maven** (wrapper opcional o global)
* **Spring Data JPA**
* **H2 Database**
* **Spring Boot Starter Thymeleaf**
* **Spring Boot Starter WebSocket**
* **Spring Boot Starter Validation**
* **springdoc-openapi-starter-webmvc-ui** (Swagger)

---

## Estructura del proyecto

```
Inventarios/
├── src/main/java/com/inventarios
│   ├── model/           (Product, Category)
│   ├── repository/      (ProductRepository, CategoryRepository)
│   ├── service/         (ProductService)
│   ├── controller/      (ProductController, WebController, SwaggerConfig)
│   ├── config/          (WebSocketConfig)
│   └── InventariosApplication.java
├── src/main/resources
│   ├── templates/       (product-list.html, product-form.html)
│   ├── static/          (CSS/JS si aplica)
│   └── application.properties
├── src/test/java/...     (Pruebas unitarias)
├── pom.xml
└── README.md
```

---

## Cómo ejecutar

1. **Clona** el repositorio:

   ```bash
   git clone https://github.com/jalducin/Inventarios.git
   ```
2. **Entra** al directorio:

   ```bash
   cd Inventarios
   ```
3. **Compila y ejecuta** con Maven global o wrapper:

   ```bash
   mvn clean spring-boot:run
   ```

   o, si tienes el wrapper:

   ```bash
   ./mvnw clean spring-boot:run
   ```

---

## Endpoints disponibles

### API REST (JSON)

```
GET    /api/products
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
GET    /api/products/filter?category={cat}
```

### Interfaz Web

```
GET    /products           ← Tabla y filtro
GET    /products/new       ← Formulario nuevo
POST   /products/save      ← Guardar (crear/editar)
GET    /products/edit/{id} ← Formulario edición
GET    /products/delete/{id} ← Eliminar
```

### Consola H2

* URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
* JDBC URL: `jdbc:h2:mem:inventarios`

### Swagger UI

* URL: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### WebSocket (STOMP)

* Endpoint: `/ws` (SockJS)
* Topic de suscripción: `/topic/products`

---

¡Listo! Con esto tendrás un sistema de inventario completo, con API, UI, datos pre-cargados y actualización en tiempo real.
