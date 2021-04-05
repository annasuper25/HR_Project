CREATE TABLE jobs
(
    job_id     VARCHAR(10),
    job_title  VARCHAR(35) NOT NULL,
    min_salary int,
    max_salary int
);

alter table jobs
    add constraint jobs_pk primary key (job_id);

----------
create sequence locations_seq;

CREATE TABLE locations
(
    location_id    int         not null default nextval('locations_seq'),
    street_address VARCHAR(40),
    city           VARCHAR(30) NOT NULL
);

alter table locations
    add constraint locations_pk primary key (location_id);

----------
create sequence departments_seq;

CREATE TABLE departments
(
    department_id   int         not null default nextval('departments_seq'),
    department_name varchar(30) NOT NULL,
    manager_id      int,
    location_id     int
);

alter table departments
    add constraint departments_id primary key (department_id);

alter table departments
    add constraint departments_location_fk foreign key (location_id) references locations (location_id);

----------
create sequence employees_seq;

CREATE TABLE employees
(
    employee_id   int         not null default nextval('employees_seq'),
    first_name    varchar(20),
    last_name     varchar(25) NOT NULL,
    email         varchar(25) NOT NULL,
    phone_number  varchar(20),
    hire_date     date        NOT NULL,
    job_id        varchar(10) NOT NULL,
    salary        int,
    manager_id    int,
    department_id int,
    CONSTRAINT emp_salary_min
        CHECK (salary > 0),
    CONSTRAINT emp_email_uk
        UNIQUE (email)
);

alter table employees
    add constraint employees_pk primary key (employee_id);

alter table employees
    add constraint employees_job_fk foreign key (job_id) references jobs (job_id);

alter table employees
    add constraint employees_dep_fk foreign key (department_id) references departments (department_id);

alter table employees
    add constraint employees_manager_fk foreign key (manager_id) references employees (employee_id);

----------
CREATE TABLE job_history
(
    employee_id   int         NOT NULL,
    start_date    DATE        NOT NULL,
    end_date      DATE        NOT NULL,
    job_id        VARCHAR(10) NOT NULL,
    department_id int,
    CONSTRAINT jhist_date_interval
        CHECK (end_date > start_date)
);

alter table job_history
    add constraint job_history_pk primary key (employee_id, start_date);

alter table job_history
    add constraint job_history_job_fk foreign key (job_id) references jobs (job_id);

alter table job_history
    add constraint job_history_dep_fk foreign key (department_id) references departments (department_id);

alter table job_history
    add constraint job_history_emp_fk foreign key (employee_id) references employees (employee_id);