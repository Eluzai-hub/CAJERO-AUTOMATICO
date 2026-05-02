# Cajero Automático en Java Swing

## 📋 Descripción

Aplicación de cajero automático desarrollada en Java con interfaz gráfica usando Swing. Permite a los usuarios autenticarse con un PIN y realizar operaciones bancarias como consultar saldo y retirar dinero.

## ✨ Características

### Pestaña 1 - Login
- ✓ Ingreso de usuario
- ✓ Validación de PIN (Código correcto: **1234**)
- ✓ Máximo 3 intentos
- ✓ Mensaje "ACCESO BLOQUEADO" después de 3 intentos fallidos
- ✓ Contador de intentos en tiempo real

### Pestaña 2 - Menú Principal
- ✓ Saldo inicial: **$100.00**
- ✓ Consultar saldo en cualquier momento
- ✓ Retirar dinero con validaciones:
  - Solo acepta números enteros
  - No permite retirar más que el saldo disponible
  - Actualiza el saldo en tiempo real
- ✓ Botón Salir (vuelve al login)

## 🏗️ Estructura del Proyecto

```
CAJERO-AUTOMATICO/
├── src/
│   └── com/
│       └── cajero/
│           ├── Main.java                 # Punto de entrada
│           ├── CajeroAutomatico.java     # Ventana principal con pestañas
│           ├── LoginPanel.java           # Panel de login
│           ├── MenuPrincipalPanel.java   # Panel del menú
│           └── Cuenta.java               # Modelo de datos
└── README.md
```

## 🔧 Requisitos

- Java JDK 8 o superior
- Apache NetBeans IDE (recomendado)
- Conocimientos básicos de Java Swing

## 🚀 Cómo ejecutar

### Opción 1: Con NetBeans
1. Abre Apache NetBeans
2. File → Open Project → Selecciona la carpeta del proyecto
3. Clic derecho en el proyecto → Run

### Opción 2: Desde línea de comandos
```bash
javac -d bin src/com/cajero/*.java
java -cp bin com.cajero.Main
```

## 📝 Pruebas

### Test 1: Login exitoso
1. Usuario: `cualquier nombre`
2. PIN: `1234`
3. ✓ Acceso permitido → Va a la pestaña del menú

### Test 2: Login fallido (3 intentos)
1. Usuario: `test`
2. PIN: `0000` (3 veces)
3. ✓ Mensaje "ACCESO BLOQUEADO"

### Test 3: Consultar saldo
1. Clic en "CONSULTAR SALDO"
2. ✓ Muestra $100.00 inicialmente

### Test 4: Retirar dinero
1. Clic en "RETIRAR DINERO"
2. Ingresa: `50`
3. ✓ Saldo se actualiza a $50.00
4. Intenta retirar: `100`
5. ✓ Mensaje de fondos insuficientes

## 💰 Lógica de Saldo

```
Saldo Inicial: $100.00

Cada retiro:
- Valida que sea número entero
- Valida que sea mayor a 0
- Valida que no exceda el saldo
- Actualiza el saldo
```

## 🎯 Funcionalidades Implementadas

| Requisito | Estado |
|-----------|--------|
| Pestaña de Login | ✅ |
| PIN 1234 | ✅ |
| 3 intentos máximo | ✅ |
| ACCESO BLOQUEADO | ✅ |
| Saldo inicial $100 | ✅ |
| Consultar saldo | ✅ |
| Retirar dinero | ✅ |
| Solo números enteros | ✅ |
| Validar saldo máximo | ✅ |
| Actualizar saldo | ✅ |
| Botón Salir | ✅ |
| Pestañas interconectadas | ✅ |

## 👨‍💻 Autor

Eluzai-hub

## 📄 Licencia

Licencia abierta - Libre para usar y modificar
