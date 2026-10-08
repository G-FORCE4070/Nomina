# README - Payroll Management System

This document describes the structure and purpose of the `definicion_tablas.sql` script, which defines a relational database schema designed for managing payroll, social benefits, deductions, social security, and other aspects related to human resources administration within an organization.

## Script Content

The SQL file includes the creation of primary, auxiliary, and relational tables, as well as the definition of primary keys, foreign keys, and uniqueness constraints to ensure data integrity and consistency.

---

## Main Tables

### `PERSONA`
Stores personal information of employees, including identification number, first and last name, date of birth, address, contact information, department, and assigned job position. It is related to the `DEPARTAMENTO` table.

### `DEPARTAMENTO`
Defines the different departments within the organization. Its purpose is to classify employees based on their organizational area.

### `CONTRATO`
Contains details about the employee's work contract, such as start date, type of contract, end date, current status (active or terminated), and its association with the final settlement. It includes a uniqueness constraint (`UNIQUE KEY`) on the `LIQUIDACION_DEFINITIVA_ID_LIQUIDACION` field.

### `NOMINA`
Records payroll information for each employee, such as base salary, payment date, net salary, and the associated contract.

### `PRESTACION_SOCIAL`
Stores values related to severance payments, interest, bonuses, vacation days, and settlement date, linked to a specific contract.

### `LIQUIDACION_DEFINITIVA`
Holds a summary of the values owed to the employee upon contract termination, including severance pay, pending vacation, proportional bonuses, and pending salary.

---

## Complementary Tables

### `BONIFICACION` and `BONIFICACION_NOMINA`
`BONIFICACION` defines the different types of bonuses. `BONIFICACION_NOMINA` acts as a junction table to establish a many-to-many relationship between bonuses and payroll records.

### `DEDUCCION` and `DEDUCCION_NOMINA`
`DEDUCCION` stores deduction types and their values. `DEDUCCION_NOMINA` allows associating multiple deductions with a single payroll record.

---

## Social Security

### `EPS`, `PENSION`, `ARL`, `CAJA_COMPENSACION`
These tables represent external institutions providing healthcare, pension, occupational risk coverage, and compensation services.

### `SEGURIDAD_SOCIAL`
Registers the contributions made by the company to each of the aforementioned institutions on behalf of a specific employee. It references `EPS`, `PENSION`, `ARL`, `CAJA_COMPENSACION`, and `PERSONA`.

---

## Foreign Keys

The relationships between tables are established using foreign keys, defined with the constraints `ON DELETE NO ACTION` and `ON UPDATE NO ACTION` to ensure referential integrity. These relationships include:

- `PERSONA` → `DEPARTAMENTO`
- `CONTRATO` → `PERSONA`, `LIQUIDACION_DEFINITIVA`
- `NOMINA` → `CONTRATO`
- `BONIFICACION_NOMINA` → `BONIFICACION`, `NOMINA`
- `DEDUCCION_NOMINA` → `DEDUCCION`, `NOMINA`
- `PRESTACION_SOCIAL` → `CONTRATO`
- `SEGURIDAD_SOCIAL` → `PERSONA`, `EPS`, `PENSION`, `ARL`, `CAJA_COMPENSACION`

---

## Special Constraints

- **Unique Key (`UNIQUE KEY`)**: Defined in the `CONTRATO` table to ensure each contract has a unique association with its corresponding final settlement.
- **Primary Keys and Auto-Increment**: Used to guarantee the unique identification of records and automatic generation of identifiers.

---

## Technical Recommendations

- Use a database engine that supports foreign keys, such as InnoDB in MySQL or MariaDB.
- Execute table creation statements before applying constraints via `ALTER TABLE`.
- Validate data types and text lengths based on actual system requirements.

---

## Purpose of the Schema

This database schema is designed to support the core processes involved in human resource management, including contract control, payroll processing, social security contributions, bonuses, deductions, and final settlements. Its modular and relational structure allows for system scalability and ensures reliable and organized information management.

