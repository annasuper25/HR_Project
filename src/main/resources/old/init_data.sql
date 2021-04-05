insert into locations (street_address, city) values
('pleshka 1000', 'Moskva'),
('pleshka 9999', 'Vladivostok'),
('pleshka 1000', 'TEST'),
('pleshka 1000', 'SPB'),
('pleshka 1000', 'Perm');

insert into departments (department_name, location_id) VALUES
('test1', 1),
('test2', 2),
('test3', 3),
('test4', 4),
('test5', 5);

insert into jobs (min_salary, max_salary, job_title) VALUES
(1000, 2000, 'test1'),
(2222, 2000, 'test2'),
(3333, 2000, 'test3'),
(4444, 2000, 'test41'),
(5555, 2000, 'test5');

insert into employees (first_name, last_name, email, phone_number, hire_date, job_id, salary, manager_id, department_id) VALUES
('name', 'last', 'email', 'phone', '2020-01-01', 1, 2000, null, 1),
('name', 'last', 'email', 'phone', '2020-01-01', 1, 2000, 1, 1),
('name', 'last', 'email', 'phone', '2020-01-01', 1, 2000, 1, 1),
('name', 'last', 'email', 'phone', '2020-01-01', 1, 2000, null, 1),
('name', 'last', 'email', 'phone', '2020-01-01', 1, 2000, 4, 1);

insert into  job_history (employee_id, start_date, end_date, job_id, department_id) VALUES
(1, '2020-01-01', '2020-01-03', 1, 1),
(1, '2020-02-01', '2020-01-03', 2, 2),
(1, '2020-02-02', '2020-02-05', 3, 1),
(2, '2020-02-01', '2020-02-13', 1, 1),
(2, '2020-02-02', '2020-02-23', 3, 1),
(2, '2020-02-03', '2020-02-03', 4, 3),
(3, '2020-01-01', '2020-03-03', 3, 3),
(3, '2020-01-02', '2020-03-03', 3, 3);