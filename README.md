# Tanabe Sports Inventory

Sistema de gestión de inventario para la tienda de esquí Tanabe Sports, construido con Spring Boot y MongoDB, y desplegado con Docker Compose.

## 🚀 Cómo ejecutar

1. Clona el repositorio.
2. Ejecuta `docker-compose up --build`
3. La API estará disponible en `http://localhost:8080/api/products`

## 📦 Endpoints principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/products` | Lista todos los productos |
| GET | `/api/products/{id}` | Obtiene un producto por ID |
| POST | `/api/products` | Crea un nuevo producto |
| PUT | `/api/products/{id}` | Actualiza un producto |
| DELETE | `/api/products/{id}` | Elimina un producto |
| GET | `/api/products/low-stock` | Productos con stock bajo (≤ 5 unidades) |

## 🗂️ Modelo de datos

Los productos incluyen campos adaptados a equipamiento de esquí: `sku`, `name`, `brand`, `category` (SKIS, BOOTS, HELMETS, GOGGLES, APPAREL, ACCESSORIES), `size`, `color`, `price`, `stockQuantity`, `reorderLevel`, etc.
