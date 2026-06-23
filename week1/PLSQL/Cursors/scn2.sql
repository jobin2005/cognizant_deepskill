DECLARE
  CURSOR c_acc IS SELECT AccountID FROM Accounts;
  v_fee CONSTANT NUMBER := 50;
BEGIN
  FOR r IN c_acc LOOP
    UPDATE Accounts SET Balance = Balance - v_fee, LastModified = SYSDATE
    WHERE AccountID = r.AccountID;
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (TRANS_SEQ.NEXTVAL, r.AccountID, SYSDATE, -v_fee, 'AnnualFee');
  END LOOP;
  COMMIT;
END;
/