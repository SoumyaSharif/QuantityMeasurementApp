# 📅 UC13 : Centralized Arithmetic Operations Using Enum Strategy

## 📖 Description

UC13 refactors the Quantity Measurement App by introducing an `ArithmeticOperation` enum (`ADD`, `SUBTRACT`, `DIVIDE`) to centralize arithmetic behavior.

All arithmetic operations now delegate to a single private helper method, eliminating duplicate validation and conversion logic while preserving existing functionality.

---

## 🛠 Implementation Details

- `ArithmeticOperation` enum handles operation-specific computation  
- Single private helper method performs:
  - Null validation  
  - Base unit conversion  
  - Enum dispatch  
  - Result conversion  
- Add and subtract results are rounded to **two decimal places**  
- Divide returns a **dimensionless raw double value**  
- No changes required to existing unit enums (`LengthUnit`, `WeightUnit`, `VolumeUnit`)  
- Full backward compatibility with UC12 maintained  

---

## 🔑 Design Benefits

- Eliminates duplicated arithmetic logic (DRY principle)  
- Centralized validation and conversion workflow  
- Clean separation of operation behavior  
- Scalable design for adding future arithmetic operations  
- Maintains immutability and type safety  

---

## 🧪 Examples

**Addition:**  
`Quantity(10.0, FEET).add(Quantity(5.0, FEET))`  
→ `15.00 FEET`

**Subtraction:**  
`Quantity(10.0, FEET).subtract(Quantity(5.0, FEET))`  
→ `5.00 FEET`

**Division:**  
`Quantity(10.0, FEET).divide(Quantity(5.0, FEET))`  
→ `2.0`

---
