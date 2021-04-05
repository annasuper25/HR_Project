select *
from employees e
         join job_history jh on e.employee_id = jh.employee_id
where age(jh.end_date, jh.start_date) > interval '1 month';