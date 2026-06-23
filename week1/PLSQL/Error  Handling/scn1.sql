CREATE OR REPLACE PROCEDURE SafeTransferFunds(
  p_from_acc IN NUMBER,
  p_to_acc   IN NUMBER,
  p_amount   IN NUMBER
) IS
  v_balance NUMBER;
BEGIN
  SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from_acc FOR UPDATE;
  IF v_balance < p_amount THEN
    RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds for account '||p_from_acc);
  END IF;

  UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_acc;
  UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_acc;

  INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
  VALUES (TRANS_SEQ.NEXTVAL, p_from_acc, SYSDATE, -p_amount, 'TransferOut');

  INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
  VALUES (TRANS_SEQ.NEXTVAL, p_to_acc, SYSDATE, p_amount, 'TransferIn');

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('SafeTransferFunds failed: ' || SQLERRM);
    RAISE;
END SafeTransferFunds;
/