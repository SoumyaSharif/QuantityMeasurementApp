# Quantity Measurement Application - Architecture Overview

##  Helper Methods in `IMeasurable` Interface
- Added helper methods to identify **measurement type** and **unit instance by unit name**.
- Ensures:
  - Comparisons happen only between **compatible measurement types**.
  - Correct **unit conversions**.
  - Smooth conversion from **QuantityDTO to IMeasurable units**.
- Updated enums implementing `IMeasurable`:
  - `LengthUnit`
  - `WeightUnit`
  - `VolumeUnit`
  - `TemperatureUnit`

---

#  Define POJO and DTO Classes

## 2.1 `QuantityDTO` (POJO / DTO)
- Represents **input data for quantity measurement**.
- Contains:
  - `value`
  - `unit`
  - `measurement type`
- Defines an inner interface `IMeasurableUnit` for DTO-level units.
- DTO enums include:
  - `LengthUnit`
  - `WeightUnit`
  - `VolumeUnit`
  - `TemperatureUnit`
- Supported measurement categories:
  - **Length** → feet, inches, yards, centimeters, meters
  - **Weight** → grams, kilograms, pounds, ounces
  - **Volume** → liters, milliliters, gallons
  - **Temperature** → Celsius, Fahrenheit, Kelvin
- Designed to simplify:
  - Input handling
  - Mapping DTO units to application units

---

## 2.2 `QuantityModel` (POJO)
- Generic model representing a **quantity and its measurable unit**.
- Generic type `<U>` extends `IMeasurable`.
- Used inside the **service layer** for operations like:
  - Conversion
  - Comparison
  - Addition
  - Subtraction
  - Division
- Also used as a **DTO for repository storage**.
- Allows flexible handling of multiple measurement types.

---

## 2.3 `QuantityMeasurementEntity` (POJO)
- Represents a **complete quantity operation record**.
- Stores:
  - Operands
  - Operation type
  - Result
  - Error details (if any)

### Key Features
- Implements `Serializable`
- Used for **persisting operation history to disk**
- Required for **repository serialization mechanism**
- Contains `serialVersionUID` for serialization compatibility.

### Design Considerations
- Designed to be **immutable-like** using constructors.
- Fields are not `final` because serialization does not support them.
- Multiple constructors support:
  - Conversion
  - Comparison
  - Addition
  - Subtraction
  - Division
  - Error cases

### Purpose
- Enables:
  - Operation history tracking
  - Logging
  - Debugging
  - UI reporting

---

#  Repository Layer

## 3.1 `IQuantityMeasurementRepository`
- Defines **data access contract** for quantity measurement data.
- Abstracts persistence implementation.
- Enables:
  - Saving measurement operations
  - Fetching stored records
- Follows **Interface Segregation Principle**.
- Allows future implementations such as:
  - Cache repository
  - Database repository

---

## 3.2 `QuantityMeasurementCacheRepository` (Singleton)
- Implements `IQuantityMeasurementRepository`.
- Stores entities in **in-memory cache** using `ArrayList`.

### Features
- Singleton implementation
- Maintains **history of operations**
- Supports persistence through **serialization to disk**.

### Core Methods
- `saveToDisk()` → append new operations to file
- `loadFromDisk()` → load operations at application startup
- `getAllMeasurements()` → retrieve stored operations

### Additional Mechanism
- Custom `AppendableObjectOutputStream` used to:
  - Append objects safely
  - Maintain file integrity during multiple writes.

---

#  Custom Exception

## `QuantityMeasurementException`
- Custom exception for **quantity measurement errors**.
- Used for:
  - Invalid unit conversions
  - Incompatible measurement operations
  - Arithmetic failures.

### Characteristics
- Extends `RuntimeException`
- Unchecked exception
- Provides constructors for:
  - Custom error messages
  - Exception chaining.

---

#  Service Layer

## 5.1 `IQuantityMeasurementService`
- Defines operations for quantity processing.
- Accepts and returns **QuantityDTO objects**.

### Supported Operations
- Conversion
- Comparison
- Addition
- Subtraction
- Division

---

## 5.2 `QuantityMeasurementServiceImpl`

### Responsibilities
- Implements `IQuantityMeasurementService`.
- Contains **core business logic** for quantity operations.

### Supported Measurement Types
- Length
- Weight
- Volume
- Temperature

### Key Principles
- **Single Responsibility Principle (SRP)**  
  Handles only quantity measurement logic.

- **Open/Closed Principle (OCP)**  
  Allows adding new measurement types without modifying existing code.

### Implementation Approach
1. Convert values to **base units**.
2. Perform operation.
3. Convert result to requested unit.

### Design Improvements
- Separates **data representation** and **business logic**:
  - `QuantityDTO` → data transfer
  - `QuantityModel` → internal representation
  - `ServiceImpl` → business logic.

### Service Workflow
1. Accept `QuantityDTO` input.
2. Convert to `QuantityModel`.
3. Validate measurement types.
4. Perform operation.
5. Handle errors using `QuantityMeasurementException`.
6. Create `QuantityMeasurementEntity`.
7. Store entity in repository.
8. Return standardized `QuantityDTO` result.

### Additional Features
- Uses **Dependency Injection** for repository.
- Enables easy swapping between repository implementations.

---

# Controller Layer

## `QuantityMeasurementController`

### Role
- Entry point for **quantity measurement operations**.

### Responsibilities
- Accept user inputs (`QuantityDTO`).
- Validate inputs (future Spring validation).
- Delegate operations to service layer.
- Return processed results.

### Supported Operations
- Comparison
- Conversion
- Addition
- Subtraction
- Division

### Future Extension
- Designed to support **REST APIs**:
  - `/compare`
  - `/convert`
  - `/add`
  - `/subtract`
  - `/divide`

---

# Step 7: Application Entry Point

## `QuantityMeasurementApp`

### Role
- Main class of the application.
- Initializes core components:
  - Repository
  - Service
  - Controller

### Responsibilities
- Start application.
- Coordinate system flow.
- Delegate operations to controller.

---

# Design Patterns Used :

## 1. Factory Pattern
- Creates instances of:
  - Controllers
  - Services

## 2. Facade Pattern
- `QuantityMeasurementController` provides a simplified interface for operations.

## 3. Singleton Pattern
- `QuantityMeasurementCacheRepository` ensures a single repository instance.

## 4. Interface Segregation Principle
- Separate interfaces for:
  - Service layer
  - Repository layer

## 5. Dependency Injection
- Inject repository into service layer.
- Promotes **loose coupling** and **testability**.

---

# Overall Architecture Flow

User Input → Controller → Service Layer → Repository → Disk Storage

1. User sends request (`QuantityDTO`).
2. Controller receives and forwards request.
3. Service performs validation and operations.
4. Result stored as `QuantityMeasurementEntity`.
5. Repository persists entity to disk.
6. Result returned to user.
