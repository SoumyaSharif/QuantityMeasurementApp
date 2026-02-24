# 📅 UC11 : Volume Measurement Equality, Conversion, and Addition

## 📖 Description

UC11 extends the Quantity Measurement App to support **volume measurements** (`LITRE`, `MILLILITRE`, `GALLON`) using the existing generic architecture.

Volume operations mirror length and weight operations:
- Equality  
- Conversion  
- Addition (with explicit target unit)  

Volume, Weight, and Length remain **separate, type-safe measurement categories**.

---

## 🧪 Units & Conversion

- `LITRE (L)` – Base unit  
- `MILLILITRE (mL)` – `1 L = 1000 mL`  
- `GALLON (gal)` – `1 gal ≈ 3.78541 L`  

---

## 🛠 Implementation Details

- `VolumeUnit` enum handles all conversion logic  
- Uses the generic `Quantity<T extends IMeasurable>` class  
- No new Quantity class required  
- Supports cross-unit equality comparison  
- Supports addition with explicit target unit  
- Immutable value objects  
- Round-trip conversions maintain precision using epsilon  
- Volume vs Length vs Weight comparisons are **not allowed**  

---

## 🔑 Design Benefits

- Fully aligned with UC10 generic architecture  
- No code duplication  
- Easy to extend with new volume units  
- Compile-time type safety  

---

## 🧪 Examples

**Equality:**  
`Quantity(1.0, LITRE).equals(Quantity(1000.0, MILLILITRE))`  
→ `true`

**Conversion:**  
`Quantity(1.0, GALLON).convertTo(LITRE)`  
→ `3.78541`

**Addition with Target Unit:**  
`Quantity(1.0, LITRE).add(Quantity(1000.0, MILLILITRE), MILLILITRE)`  
→ `2000.0`

---
