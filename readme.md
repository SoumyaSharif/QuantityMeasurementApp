UC10 – Generic Measurement Refactor
Refactored the system to use a single generic class Quantity for all measurement categories.

Introduced IMeasurable interface
Implemented LengthUnit, WeightUnit as enums
Removed duplicate quantity classes
Ensured compile-time type safety
Prevented cross-category comparison
Easily supports new units (Volume, Time, Temperature, etc.)
Result: Cleaner, scalable, and fully extensible measurement system.
