# Write your MySQL query statement below

SELECT email
FROM (
    SELECT
        email,
        COUNT(email) AS count
    FROM person
    GROUP BY email
) tab
WHERE count > 1;