# 📅 UC9 : Weight Measurement Equality, Conversion, and Addition

## 📖 Description

UC9 extends the Quantity Measurement App to support **weight measurements** (`KILOGRAM`, `GRAM`, `POUND`) alongside length.

Weight operations mirror length operations:
- Equality  
- Conversion  
- Addition (with explicit target unit)

Weight and Length are treated as **separate, type-safe measurement categories**.

---

## ⚖️ Units & Conversion

- `KILOGRAM (kg)` – Base unit  
- `GRAM (g)` – `1 kg = 1000 g`  
- `POUND (lb)` – `1 lb ≈ 0.453592 kg`  

---

## 🛠 Implementation Details

- `WeightUnit` enum handles all conversion logic  
- `QuantityWeight` class handles equality and arithmetic  
- Conversions are delegated to `WeightUnit`  
- Supports cross-unit equality comparison  
- Supports addition with explicit target unit  
- Immutable value objects  
- Round-trip conversions maintain precision using epsilon  
- Weight vs Length comparisons are **not allowed**  

---

## 🔑 Key Design Principles

- Standalone enum with conversion responsibility  
- Separation of measurement categories  
- DRY principle applied  
- Immutability and thread-safety  
- Type safety across operations  

---

## 🧪 Examples

**Equality:**  
`Quantity(1.0, KILOGRAM).equals(Quantity(1000.0, GRAM))`  
→ `true`

**Conversion:**  
`Quantity(2.20462, POUND).convertTo(KILOGRAM)`  
→ `Quantity(~1.0, KILOGRAM)`

**Addition with Target Unit:**  
`Quantity(1.0, KILOGRAM).add(Quantity(1000.0, GRAM), GRAM)`  
→ `Quantity(2000.0, GRAM)`

---
```
