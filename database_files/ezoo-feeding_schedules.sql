-- drop table feeding_schedules if exists
drop table if exists feeding_schedules;

create table feeding_schedules (
  schedule_id integer primary key,
  feeding_time varchar (100),
  recurrence varchar (80),
  food varchar (80),
  notes varchar (80)
);

insert into feeding_schedules values (
  1,          -- id
  '3:45',      -- feeding_time
  '3 times a day', -- recurrence
  'Plants',    -- food
  'first schedule'   -- notes
);