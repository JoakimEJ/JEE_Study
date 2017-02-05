CREATE TABLE day(
  id BIGINT not null,
  dayName varchar(255),
  primary key (id)
);

INSERT INTO day
  (id, dayName)
VALUES
  (1, 'MONDAY'),
  (2, 'TUESDAY'),
  (3, 'WEDNESDAY'),
  (4, 'THURSDAY'),
  (5, 'FRIDAY');