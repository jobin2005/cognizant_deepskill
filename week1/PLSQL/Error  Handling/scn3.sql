CREATE OR REPLACE PROCEDURE AddNewCustomer(
  p_id       IN NUMBER,
  p_name     IN VARCHAR2,
  p_dob      IN DATE,
  p_balance  IN NUMBER
) IS
BEGIN
  INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
  VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
  COMMIT;
EXCEPTION
  WHEN DUP_VAL_ON_INDEX THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('AddNewCustomer failed: CustomerID '||p_id||' already exists.');
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('AddNewCustomer error: ' || SQLERRM);
    RAISE;
END AddNewCustomer;
/