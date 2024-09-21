insert into users(id, username, password)
values ('5d730ada-9c91-11ed-bef5-631db7f28980', 'user1', '{noop}password1'),
       ('5da0a544-9c91-11ed-a601-23bef39fb8a6', 'user2', '{noop}password2');

insert into task(id, details, completed, user_id)
values ('71117396-8694-11ed-9ef6-77042ee83937', 'First task', false, '5d730ada-9c91-11ed-bef5-631db7f28980'),
       ('7172d834-8694-11ed-8669-d7b17d45fba8', 'Second task', true, '5da0a544-9c91-11ed-a601-23bef39fb8a6');