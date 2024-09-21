create table users
(
    id         uuid primary key,
    username varchar(255) not null,
    password text
);

create unique index idx_user_username on users (username);

alter table task
    add column user_id uuid not null references users (id);

create index idx_task_user on task (user_id);