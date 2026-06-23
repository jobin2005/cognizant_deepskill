CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
  p_principal   IN NUMBER,
  p_annual_rate IN NUMBER, -- percent, e.g., 5 for 5%
  p_years       IN NUMBER
) RETURN NUMBER IS
  r NUMBER;
  n NUMBER;
BEGIN
  r := p_annual_rate / 100 / 12;
  n := p_years * 12;
  IF r = 0 THEN
    RETURN p_principal / n;
  ELSE
    RETURN (p_principal * r) / (1 - POWER(1 + r, -n));
  END IF;
END CalculateMonthlyInstallment;
/