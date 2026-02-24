# UC8 : Refactoring Unit Enum to Standalone with Conversion Responsibility

Refactors the design by extracting `LengthUnit` from `QuantityLength` into a standalone enum that handles all unit conversions.

This improves cohesion, reduces coupling, eliminates circular dependencies, and establishes a scalable pattern for future measurement categories (weight, volume, etc.).

---

## 🔑 Key Concepts

- Standalone `LengthUnit` enum with conversion responsibility  
- `QuantityLength` simplified: focuses on equality and arithmetic  
- Conversion methods: `convertToBaseUnit()` and `convertFromBaseUnit()`  
- Backward compatible: all UC1–UC7 operations continue to work  
- Supports future measurement categories using the same pattern  
- Maintains immutability, type safety, and thread-safety  

---

## 🧪 Examples

**Input:**  
`Quantity(1.0, FEET).convertTo(INCHES)`  
**Output:** `Quantity(12.0, INCHES)`

**Input:**  
`Quantity(1.0, FEET).add(Quantity(12.0, INCHES), FEET)`  
**Output:** `Quantity(2.0, FEET)`

**Input:**  
`Quantity(36.0, INCHES).equals(Quantity(1.0, YARDS))`  
**Output:** `true`

**Input:**  
`Quantity(2.54, CENTIMETERS).convertTo(INCHES)`  
**Output:** `Quantity(~1.0, INCHES)`

**Input:**  
`LengthUnit.INCHES.convertToBaseUnit(12.0)`  
**Output:** `1.0 (feet)`

---
