# Write your MySQL query statement below

SELECT
    e.name AS Employee,
    e.salary AS Salary,
    d.name AS Department
FROM Employee e
JOIN Department d
    ON e.departmentId = d.id
WHERE e.salary = 
    (
        SELECT MAX(e1.salary)
        FROM Employee e1
        WHERE e.departmentId = e1.departmentId
    );