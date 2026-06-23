CREATE OR REPLACE PACKAGE AccountOperations AS
  PROCEDURE OpenAccount(p_accountid NUMBER, p_customerid NUMBER, p_type VARCHAR2, p_initial_balance NUMBER);
  PROCEDURE CloseAccount(p_accountid NUMBER);
  FUNCTION GetTotalBalance(p_customerid NUMBER) RETURN NUMBER;
END AccountOperations;
/
CREATE OR REPLACE PACKAGE BODY AccountOperations AS
  PROCEDURE OpenAccount(p_accountid NUMBER, p_customerid NUMBER, p_type VARCHAR2, p_initial_balance NUMBER) IS
  BEGIN
    INSERT INTO Accounts(AccountID, CustomerID, AccountType, Balance, LastModified)
    VALUES (p_accountid, p_customerid, p_type, p_initial_balance, SYSDATE);
    COMMIT;
  END OpenAccount;

  PROCEDURE CloseAccount(p_accountid NUMBER) IS
  BEGIN
    DELETE FROM Accounts WHERE AccountID = p_accountid;
    COMMIT;
  END CloseAccount;

  FUNCTION GetTotalBalance(p_customerid NUMBER) RETURN NUMBER IS
    v_total NUMBER;
  BEGIN
    SELECT NVL(SUM(Balance),0) INTO v_total FROM Accounts WHERE CustomerID = p_customerid;
    RETURN v_total;
  END GetTotalBalance;
END AccountOperations;
/