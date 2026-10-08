# README - SQL Reports for Payroll Management System

This document explains the purpose and usage of two SQL reports used in the payroll management system: **Department Report** and **Employee Report**. These queries are used to extract summarized and detailed payroll and social security information within a specified time period.

---

## Report 1: Department Summary Report

### Purpose

Generates a summarized payroll report by department within a specified date range. It includes information such as:

- Total number of employees
- Base salary
- Bonuses
- Deductions
- Total paid per employee
- Department name
- Employee identification

### Required Parameters

The query uses placeholders `?` to represent dynamic input values, which must be provided before execution:

1. `?` → Start date of the report period (`DATE`)
2. `?` → End date of the report period (`DATE`)
3. `?` → Department ID (`INT`)

### Output Columns

| Column             | Description                                  |
|--------------------|----------------------------------------------|
| DEPARTAMENTO       | Name of the department                       |
| CEDULA             | Employee's identification number             |
| NOMBRE             | Full name of the employee                    |
| SALARIO            | Base salary of the employee                  |
| BONIFICACIONES     | Total bonuses received                       |
| DEDUCCIONES        | Total deductions applied                     |
| SALARIO_TOTAL      | Sum of all base salaries for the employee(s) |
| TOTAL_PAGADO       | Net amount paid (salary + bonuses - deductions) |

---

## Report 2: Social Security Report by Employee

### Purpose

Provides a detailed summary of social security contributions made for a specific employee within a specified period. This includes contributions to EPS, ARL, Pension Fund, and Compensation Fund.

### Required Parameters

This report also requires three input values:

1. `?` → Start date of the report period (`DATE`)
2. `?` → End date of the report period (`DATE`)
3. `?` → Employee identification number (`BIGINT`)

### Output Columns

| Column           | Description                            |
|------------------|----------------------------------------|
| CEDULA           | Employee's identification number       |
| NOMBRE           | Full name of the employee              |
| FECHA_PAGO       | Payment date or 'PENDIENTE' if missing |
| EPS              | Name of the health provider            |
| ARL              | Name of the occupational risk admin    |
| PENSION          | Name of the pension fund               |
| CAJA             | Name of the compensation fund          |
| APORTES_EPS      | Contribution to EPS                    |
| APORTES_ARL      | Contribution to ARL                    |
| APORTES_PENSION  | Contribution to pension                |
| APORTES_CAJA     | Contribution to compensation fund      |
| TOTAL_APORTE     | Sum of all contributions               |

---

## Execution Notes

- These queries are designed to be used in **prepared statement environments** such as stored procedures, reporting tools (e.g., Power BI, Tableau), or application code (Java, Python, etc.).
- Replace the `?` placeholders with actual values using the corresponding method in your tool or programming language.

---

## Example Usage (MySQL with variables)

```sql
SET @start_date = '2023-01-01';
SET @end_date = '2023-12-31';
SET @department_id = 3;
-- Then replace ? with @start_date, @end_date, @department_id
