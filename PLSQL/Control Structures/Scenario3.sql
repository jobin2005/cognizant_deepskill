BEGIN
  FOR l IN (SELECT lo.LoanID, lo.CustomerID, c.Name, lo.EndDate
            FROM Loans lo JOIN Customers c ON lo.CustomerID = c.CustomerID
            WHERE lo.EndDate BETWEEN SYSDATE AND SYSDATE + 30)
  LOOP
    DBMS_OUTPUT.PUT_LINE('Reminder: Customer ' || l.Name ||
                         ' (ID:' || l.CustomerID || ') - Loan ' || l.LoanID ||
                         ' is due on ' || TO_CHAR(l.EndDate,'YYYY-MM-DD'));
  END LOOP;
END;
/