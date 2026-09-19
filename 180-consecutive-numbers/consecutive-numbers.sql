# Write your MySQL query statement below

WITH cte AS (
    SELECT num, 
    LAG(num, 1) OVER(ORDER BY id) AS prev1,
    LAG(num, 2) OVER(ORDER BY id) AS prev2
    FROM logs
)
SELECT  DISTINCT
    num AS ConsecutiveNums
FROM cte
WHERE num = prev1
    AND num = prev2;