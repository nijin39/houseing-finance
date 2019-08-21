select i.institute_name, a.year, a.total
from (
    select institute_code, year, sum(amount) as total
    from credit_guarantee
    group by institute_code, year) a, institute i
where a.institute_code = i.institute_code