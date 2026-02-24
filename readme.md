# 📅 UC12 : Quantity Subtraction and Division

## 📖 Description

UC12 extends the Quantity Measurement App by adding **subtraction** and **division** operations to the generic `Quantity<T extends IMeasurable>` class.

These operations work across all supported measurement categories:

- Length  
- Weight  
- Volume  

Strict type safety is maintained using generics, preventing cross-category operations.

---

## ✨ Features

- Subtraction with implicit target unit (defaults to first operand’s unit)  
- Subtraction with explicit target unit  
- Cross-unit operations within the same category  
- Division returns a unitless ratio (`double`)  
- Cross-category operations prevented at compile time  
- Null validation and division-by-zero handling  
- Immutability and precision maintained  

---

## 🔑 Design Guarantees

- Reuses existing conversion logic (DRY principle)  
- Type-safe arithmetic within category boundaries  
- No modification of original objects (immutable design)  
- Floating-point precision handled using epsilon  

---

## 🧪 Examples

**Subtraction (Cross-Unit):**  
`Quantity(10.0, FEET).subtract(Quantity(6.0, INCH))`  
→ `9.5 FEET`

**Subtraction (Same Unit):**  
`Quantity(5.0, LITRE).subtract(Quantity(2.0, LITRE))`  
→ `3.0 LITRE`

**Division (Unitless Ratio):**  
`Quantity(10.0, FEET).divide(Quantity(2.0, FEET))`  
→ `5.0`

---
