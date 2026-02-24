# UC5 : Length Unit Conversion API

Extended `Length` class to provide explicit conversion between all supported units (`FEET`, `INCHES`, `YARDS`, `CENTIMETERS`).

Added `Length.convert(value, sourceUnit, targetUnit)` for static conversions and  
`Length.convertTo(targetUnit)` for instance-level conversions.

Maintained DRY principle using base unit normalization.

Backward compatible with all UC1–UC4 functionality.

---

## 🔑 Key Concepts

- Enum-based conversion factors for type safety  
- Cross-unit conversions with proper precision  
- Immutability and value-object semantics  
- Input validation for `null`, `NaN`, and infinite values  
- Overloaded conversion methods for flexibility  

---
