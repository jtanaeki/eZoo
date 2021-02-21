-- drop table animals if exists
drop table if exists animals;

create table animals (
  animal_id integer primary key,
  schedule_ID integer,
  "name" varchar (100),
  tax_kingdom varchar (80),
  tax_phylum varchar (80),
  tax_class varchar (80),
  tax_order varchar (80),
  tax_family varchar (80),
  tax_genus varchar (80),
  tax_species varchar (80),
  height numeric(6,2),
  weight numeric(6,2),
  "type" varchar (80),
  health_status varchar (80)
);

insert into animals values (
  1,          -- animal id
  101,        -- schedule id
  'Leo',      -- name
  'Animalia', -- kingdom
  'Chordata',    -- phylum
  'Mammalia',   -- class
  'Carnivora',-- animal_order
  'Felidae',  -- family
  'Panthera', -- genus
  'P. leo',   -- species
  120.88,     -- height
  400.67,     -- weight
  'Mammal (Terrestrial)',   -- type
  'Healthy'   -- healthStatus
);