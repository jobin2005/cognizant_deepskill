CREATE OR REPLACE PROCEDURE UpdateSalary(
  p_empid IN NUMBER,
  p_pct   IN NUMBER
) IS
BEGIN
  UPDATE Employees
  SET Salary = Salary * (1 + p_pct/100)
  WHERE EmployeeID = p_empid;

  IF SQL%ROWCOUNT = 0 THEN
    RAISE_APPLICATION_ERROR(-20002, 'Employee ID not found: ' || p_empid);
  END IF;

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('UpdateSalary error: ' || SQLERRM);
    RAISE;
END UpdateSalary;
/