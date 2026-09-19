CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      # Write your MySQL query statement below.


    WITH ranked AS (

        SELECT
            id, salary,
            DENSE_RANK() OVER(
                ORDER BY salary DESC
            ) AS rnk
        FROM employee
    )
    SELECT salary FROM
    ranked WHERE rnk = N
    LIMIT 1
  );
END