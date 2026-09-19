# Write your MySQL query statement below
SELECT
    c.name AS Customers
FROM customers c
LEFT JOIN Orders o
    ON o.customerId = c.id
WHERE o.customerId IS NULL;