CREATE OR REPLACE PACKAGE EmployeeManagement AS
  PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_dept VARCHAR2, p_hiredate DATE);
  PROCEDURE UpdateEmployeeDetails(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER);
  FUNCTION CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/
CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
  PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_dept VARCHAR2, p_hiredate DATE) IS
  BEGIN
    INSERT INTO Employees(EmployeeID, Name, Position, Salary, Department, HireDate)
    VALUES (p_id, p_name, p_position, p_salary, p_dept, p_hiredate);
    COMMIT;
  END HireEmployee;

  PROCEDURE UpdateEmployeeDetails(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER) IS
  BEGIN
    UPDATE Employees SET Name = p_name, Position = p_position, Salary = p_salary WHERE EmployeeID = p_id;
    COMMIT;
  END UpdateEmployeeDetails;

  FUNCTION CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER IS
    v_salary NUMBER;
  BEGIN
    SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_id;
    RETURN v_salary * 12;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN RETURN NULL;
  END CalculateAnnualSalary;
END EmployeeManagement;
/