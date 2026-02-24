# 📅 UC14 : Temperature Measurement Equality and Conversion

## 📖 Description

UC14 extends the Quantity Measurement App to support **temperature measurements** (`CELSIUS`, `FAHRENHEIT`).

Unlike length, weight, and volume, temperature uses **non-linear conversion formulas** rather than simple multiplication factors.

The system supports:
- Equality  
- Conversion  

Arithmetic operations such as addition, subtraction, multiplication, and division are **not allowed** for temperature.

---

## 🌡 Units & Conversion

- `CELSIUS (°C)` – Base unit  
- `FAHRENHEIT (°F)` – `°F = (°C × 9/5) + 32`  

---

## 🛠 Implementation Details

- `TemperatureUnit` enum implements `IMeasurable`  
- Uses conversion formulas instead of multiplication factors  
- Supports cross-unit equality using epsilon precision  
- Arithmetic operations throw `UnsupportedOperationException`  
- Fully compatible with the existing generic `Quantity<T extends IMeasurable>` class  
- No changes required to `LengthUnit`, `WeightUnit`, or `VolumeUnit`  

---

## 🔑 Design Highlights

- Preserves type safety across categories  
- Maintains immutability  
- Supports non-linear unit transformations  
- Prevents invalid arithmetic operations by design  

---

## 🧪 Examples

**Equality:**  
`Quantity(0.0, CELSIUS).equals(Quantity(32.0, FAHRENHEIT))`  
→ `true`

**Conversion:**  
`Quantity(100.0, CELSIUS).convertTo(FAHRENHEIT)`  
→ `212.0`

**Invalid Arithmetic:**  
`Quantity(50.0, CELSIUS).add(Quantity(10.0, CELSIUS))`  
→ `UnsupportedOperationException`

---
