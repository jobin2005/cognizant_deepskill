CREATE TABLE AuditLog (
  AuditID       NUMBER PRIMARY KEY,
  TransactionID NUMBER,
  AccountID     NUMBER,
  ActionDate     DATE,
  Details       VARCHAR2(4000)
);

-- create a sequence for AuditID first if needed: AUDIT_SEQ
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (AuditID, TransactionID, AccountID, ActionDate, Details)
  VALUES (AUDIT_SEQ.NEXTVAL, :NEW.TransactionID, :NEW.AccountID, SYSDATE,
          'Type='||:NEW.TransactionType||', Amount='||:NEW.Amount);
END;
/