DECLARE
  CURSOR c_loans IS SELECT LoanID, LoanAmount, InterestRate FROM Loans FOR UPDATE;
BEGIN
  FOR l IN c_loans LOOP
    IF l.LoanAmount > 10000 THEN
      UPDATE Loans SET InterestRate = InterestRate - 0.5 WHERE LoanID = l.LoanID;
    ELSE
      UPDATE Loans SET InterestRate = InterestRate - 0.25 WHERE LoanID = l.LoanID;
    END IF;
  END LOOP;
  COMMIT;
END;
/