create table users (
    id bigint primary key auto_increment,
    name varchar(20) not null ,
    username varchar(20) unique,
    password varchar(60) not null,
    age int(5) not null,
    is_expired boolean not null default false,
    is_locked boolean not null default false,
    is_credentials_expired boolean not null default false,
    is_enabled boolean not null default false
    id_activation_token ...
);

create table roles (
    id bigint primary key  auto_increment,
    role varchar(20) not null
);

create table user_roles (
    id_user bigint not null ,
    id_roles bigint not null ,

    foreign key (id_user) references users(id),
    foreign key (id_roles) references users(id),
    primary key (id_user, id_roles)
);

create table activation_tokens (
    id bigint primary key auto_increment,
    value varchar(32) unique not null,
    creation_date datetime not null,
    expiration_date datetime not null
);