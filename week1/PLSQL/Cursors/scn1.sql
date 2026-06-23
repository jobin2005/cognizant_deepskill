DECLARE
  CURSOR c_txn IS
    SELECT a.CustomerID, c.Name, t.TransactionID, t.TransactionDate, t.Amount, t.TransactionType
    FROM Transactions t
    JOIN Accounts a ON t.AccountID = a.AccountID
    JOIN Customers c ON a.CustomerID = c.CustomerID
    WHERE TRUNC(t.TransactionDate,'MM') = TRUNC(SYSDATE,'MM')
    ORDER BY a.CustomerID, t.TransactionDate;

  v_rec c_txn%ROWTYPE;
  v_current_customer NUMBER := NULL;
BEGIN
  OPEN c_txn;
  LOOP
    FETCH c_txn INTO v_rec;
    EXIT WHEN c_txn%NOTFOUND;
    IF v_current_customer IS NULL OR v_current_customer != v_rec.CustomerID THEN
      v_current_customer := v_rec.CustomerID;
      DBMS_OUTPUT.PUT_LINE('--- Statement for '||v_rec.Name||' (Customer '||v_rec.CustomerID||') ---');
    END IF;
    DBMS_OUTPUT.PUT_LINE(TO_CHAR(v_rec.TransactionDate,'YYYY-MM-DD') || ' | ' ||
                         v_rec.TransactionType || ' | ' || v_rec.Amount);
  END LOOP;
  CLOSE c_txn;
END;
/