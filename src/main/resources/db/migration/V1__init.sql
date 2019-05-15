create table activation_tokens
(
    id         bigint primary key auto_increment,
    value      varchar(32) unique not null,
    expires_at timestamp          not null,
    created_at timestamp          not null,
    updated_at timestamp          not null
);

create table users
(
    id                     bigint primary key auto_increment,
    name             varchar(255)        not null,
    username               varchar(255) unique not null,
    email                  varchar(255) unique not null,
    password               varchar(60)         not null,
    is_expired             boolean             not null default false,
    is_locked              boolean             not null default false,
    is_credentials_expired boolean             not null default false,
    is_enabled             boolean             not null default false,
    activation_token_id    bigint              unique not null,
    created_at             timestamp           not null,
    updated_at             timestamp           not null,

    foreign key (activation_token_id) references activation_tokens (id)
);

create table roles
(
    id         bigint primary key auto_increment,
    name       varchar(50) unique not null,
    created_at timestamp          not null,
    updated_at timestamp          not null
);


create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,

    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id),
    primary key (user_id, role_id)
);