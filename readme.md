# UC6 : Addition of Two Length Units

Supports addition of two `Length` quantities using the generic `Length` class introduced in UC3/UC4/UC5.

The `LengthUnit` enum includes `FEET`, `INCHES`, `YARDS`, and `CENTIMETERS`, each defined with a `conversionFactor` relative to a consistent base unit.

Two `Length` objects (or raw values with their respective units) can be added together.

The result is returned in the unit of the first operand (target unit).

All units belong to the same measurement category (Length).

---

## 🔑 Key Concepts

- Base unit normalization before arithmetic operations  
- Cross-unit addition using conversion factors  
- Target unit consistency (result in first operand’s unit)  
- Reuse of existing conversion logic (DRY principle)  
- Immutability preserved during addition  
- Validation to ensure both operands belong to Length category  

---

## 🧪 Example

**Input:**  
`Length(1.0, FEET)` + `Length(6.0, INCHES)`

**Output:**  
`Length(1.5, FEET)`
