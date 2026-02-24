# 📅 UC10 : Generic Measurement Refactor

## 📖 Description

Refactored the system to use a single generic class `Quantity` for all measurement categories.

This refactor eliminates duplication, improves scalability, and establishes a clean architecture for supporting multiple measurement types.

---

## 🛠 Architectural Changes

- Introduced `IMeasurable` interface  
- Implemented `LengthUnit` and `WeightUnit` as enums  
- Removed duplicate quantity classes  
- Centralized logic into a single generic `Quantity<T extends IMeasurable>` class  

---

## 🔑 Key Improvements

- Compile-time type safety  
- Prevented cross-category comparison (e.g., Length vs Weight)  
- Generic design supports new categories easily  
- Easily extendable to:
  - Volume  
  - Time  
  - Temperature  
  - And more  

---

## ✅ Result

Cleaner, scalable, and fully extensible measurement system.

Future measurement categories can now be added with minimal changes by simply:

1. Creating a new enum implementing `IMeasurable`
2. Defining conversion logic inside the enum
3. Using the generic `Quantity` class

---
