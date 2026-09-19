# Write your MySQL query statement below

SELECT
    e1.name AS employee
FROM employee e1
WHERE salary > 
    (
        SELECT e2.salary
        FROM employee e2
        WHERE e1.managerId = e2.id
    );