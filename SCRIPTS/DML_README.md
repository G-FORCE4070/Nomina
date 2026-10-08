# README - Payroll Management System (DML Script)

This document explains the structure and purpose of the `DML.sql` script, which is used to populate the database tables defined in the `definicion_tablas.sql` schema. It contains multiple `INSERT` statements that provide initial data for the payroll management system.

## Purpose of the Script

The script serves to preload the system with realistic and representative records for each table. This facilitates system testing, data validation, query development, and demonstration of functionalities within the context of payroll, contracts, social security, and employee management.

---

## Contents of the DML Script

### `EPS`, `PENSION`, `CAJA_COMPENSACION`, `ARL`
These inserts add records for the main external institutions that handle health services (EPS), pension funds, family compensation funds, and occupational risk administrators.

### `DEPARTAMENTO`
Populates the table with five organizational departments: Human Resources, Finance, Technology, Marketing, and Sales.

### `DEDUCCION` and `BONIFICACION`
Contains various types of payroll deductions and bonuses with their respective reasons and values.

### `PERSONA`
Inserts basic employee data such as full name, birth date, address, contact number, department, and job title. This information is crucial for linking personal data to contracts, payroll, and social security.

### `SEGURIDAD_SOCIAL`
Records monthly contributions to ARL, EPS, PENSION, and CAJA COMPENSACION for each employee. It reflects the company's compliance with social security regulations.

### `LIQUIDACION_DEFINITIVA`
Stores the final settlement values for contract termination cases, including severance pay, proportional bonuses, pending salaries, and vacation days.

### `CONTRATO`
Establishes labor contracts associated with the `PERSONA` table. Each contract specifies its type (e.g., fixed-term, indefinite, apprenticeship), its status (active or finalized), and the corresponding settlement record.

### `NOMINA`
Populates the payroll table with salary information, payment dates, and associated contract IDs. Both gross and net salary values are recorded.

### `PRESTACION_SOCIAL`
Provides social benefit data such as severance, interest, vacation days, and bonus values associated with each contract.

### `DEDUCCION_NOMINA` and `BONIFICACION_NOMINA`
These are associative tables used to link multiple deductions and bonuses to specific payroll records. They implement many-to-many relationships between the payroll and financial adjustments.

---

## Execution Order Recommendations

1. Ensure that all tables from `definicion_tablas.sql` are created successfully.
2. Follow the logical insertion order to maintain referential integrity:
   - Insert into reference tables first (`DEPARTAMENTO`, `EPS`, `PENSION`, etc.).
   - Then insert employees (`PERSONA`) and related tables like `CONTRATO`.
   - Insert payroll-related data (`NOMINA`, `DEDUCCION`, `BONIFICACION`) afterward.
   - Finally, populate the linking tables (`DEDUCCION_NOMINA`, `BONIFICACION_NOMINA`).

---

## Additional Notes

- All `INSERT` statements assume valid foreign key relationships.
- Null values are used where necessary, particularly for active contracts without end dates or settlements.
- The script is intended for initial data loading and should be used in development or testing environments.

