# Actividad 3 - Tema 4: CRUD con Spring Boot, JPA y MySQL
REALIZADO POR: ANTONIO VAZQUEZ KARLA GUADALUPE

CRUD completo de **Alumnos** y **Escuelas** hecho con Spring Boot, Spring Data JPA y MySQL, con vistas Thymeleaf y una API REST. El proyecto corre en un VPS, conectado a una base de datos MySQL real.

## Proyecto en el VPS
- Vistas: http://138.68.234.30:8086/alumnos
- http://138.68.234.30:8086/alumnos/nuevo
- API: http://138.68.234.30:8086/api/alumnos

## 1. Entidades y relación
Elegí **Alumno** y **Escuela**. La relación es de **muchos a uno**: muchos alumnos pertenecen a una escuela. 
En `Alumno` se usa `@ManyToOne` con `@JoinColumn(name = "escuela_id")` (la llave foránea), y en `Escuela` se usa `@OneToMany(mappedBy = "escuela")`.

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/7b784ac6-05a5-4c20-8781-63ce2e1929cd" />

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/1a8b6afb-f5f3-4d5a-a1e4-0678ba5f05b4" />

Estructura de la tabla `alumno` en MySQL, con la llave foránea `escuela_id`:

<img width="530" height="89" alt="image" src="https://github.com/user-attachments/assets/16370e61-ea1c-4299-9b7c-14eda4719c51" />

## 2. Estructura del proyecto
- `models`: entidades `Alumno` y `Escuela`
- `repository`: `AlumnoRepository` y `EscuelaRepository` (extienden `JpaRepository`)
- `service`: `AlumnoService` y `EscuelaService` (lógica de negocio)
- `controllers`: `AlumnoViewController` (vistas), `AlumnoController` y `EscuelaController` (API REST)
- `templates/alumnos`: `lista.html` y `formulario.html`

## 3. CRUD funcionando

### Leer (lista con el nombre de la escuela)
<img width="1920" height="451" alt="image" src="https://github.com/user-attachments/assets/5c1468c6-f583-495d-a156-c575850441f9" />

### Crear
<img width="1919" height="565" alt="image" src="https://github.com/user-attachments/assets/7a8ef541-1e30-48b6-b0fc-2962a47ade2a" />
<img width="1920" height="529" alt="image" src="https://github.com/user-attachments/assets/ffa5c210-a699-48cc-9966-fac6237baa9c" />

### Actualizar
<img width="1919" height="512" alt="image" src="https://github.com/user-attachments/assets/57fad3f3-99c6-44ad-88c5-80c013a3c008" />

### Eliminar
<img width="1920" height="492" alt="image" src="https://github.com/user-attachments/assets/e16e0bfa-f914-4049-9729-829e6a348e15" />
ANTES
<img width="1916" height="437" alt="image" src="https://github.com/user-attachments/assets/64843b6c-b147-4b97-b94d-31aecb4fd2a1" />
DESPUÈS

## 4. Relación reflejada
Al consultar un alumno se muestra el **nombre de su escuela**, no solo el ID, tanto en la vista como en el JSON de la API.
<img width="1918" height="453" alt="image" src="https://github.com/user-attachments/assets/e2eaef8d-94c4-4028-b70e-4fc4b1a6bf9f" />

## 5. Persistencia en la base de datos real (VPS)
Los datos se guardan en la base MySQL del VPS. Consulta con `JOIN` desde la terminal:
(Antes y despuès de agregar a un alumno)
```sql
SELECT a.1, a.Guadalupe, a.23160840, e.Instituto Tecnològico de Oaxaca
FROM alumno a JOIN escuela e ON a.escuela_id = e.1;
```
<img width="721" height="287" alt="image" src="https://github.com/user-attachments/assets/6c2081cf-e970-4112-9e5f-41af5db18362" />

## 6. Despliegue en el VPS
El proyecto corre en el puerto **8086**, sin afectar las actividades 1 y 2 (puertos 8082 y 8084).
<img width="765" height="107" alt="image" src="https://github.com/user-attachments/assets/e4585120-efab-4d3b-a612-b72476344fef" />

## 7. Pruebas con Postman
Probé la API REST del proyecto desplegado en el VPS (`http://138.68.234.30:8086`). La colección exportada está en la carpeta `postman/`.

| Operación | Método | URL |
|---|---|---|
| Listar escuelas | GET | `/api/escuelas` |
| Listar alumnos | GET | `/api/alumnos` |
| Obtener un alumno | GET | `/api/alumnos/{id}` |
| Crear alumno | POST | `/api/alumnos?escuelaId=1` |
| Actualizar alumno | PUT | `/api/alumnos/{id}?escuelaId=2` |
| Eliminar alumno | DELETE | `/api/alumnos/{id}` |

### GET - Listar alumnos (incluye la escuela)
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/db99a23b-0f79-42f6-8f40-b35d0f6c419d" />

### POST - Crear alumno
Body (raw, JSON):
```json
{
  "nombre": "Luis Hernández",
  "matricula": "2021121"
}
```
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/78fbc149-cce7-4d84-9241-b4e9b80b375c" />

### PUT - Actualizar alumno
Body (raw, JSON):
```json
{
  "nombre": "Luis Hernández Pérez",
  "matricula": "20211111"
}
```
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/0bead0ff-bf55-42e6-80bd-85c42e909647" />

### DELETE - Eliminar alumno
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/e18f78ba-4f46-43f8-83a6-07e16a0d5aab" />

## Configuración
Las credenciales de la base de datos no están en el repositorio: se pasan con variables de entorno.

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/act3_db?allowPublicKeyRetrieval=true&useSSL=false}
spring.datasource.username=${DB_USER:act3_user}
spring.datasource.password=${DB_PASS:123456}
server.port=${PORT:8086}
```

## Cómo ejecutar
```bash
mvnw.cmd clean package -DskipTests
java -jar target/demoCrud-0.0.1-SNAPSHOT.jar
```
