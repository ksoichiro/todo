create table if not exists `user` (
    `id` bigint(20) not null auto_increment,
    `username` varchar(255) not null,
    `password` varchar(255) not null,
    `enabled` boolean not null default true,
    `updated_at` bigint(20) not null,
    `created_at` bigint(20) not null,
    primary key (`id`),
    unique (`username`)
) engine=InnoDB default charset=utf8 collate utf8_general_ci;

create table if not exists `role` (
    `id` bigint(20) not null,
    `name` varchar(255) not null,
    primary key (`id`),
    unique (`name`)
) engine=InnoDB default charset=utf8 collate utf8_general_ci;

create table if not exists `role_user` (
    `role_id` bigint(20) not null,
    `user_id` bigint(20) not null,
    unique index `ix_role_user` (`role_id`, `user_id`),
    constraint `fk_role_user_role_id` foreign key(`role_id`) references `role`(`id`),
    constraint `fk_role_user_user_id` foreign key(`user_id`) references `user`(`id`)
) engine=InnoDB default charset=utf8 collate utf8_general_ci;

create table if not exists `group` (
    `id` bigint(20) not null auto_increment,
    `name` varchar(255) not null,
    `description` varchar(255),
    `updated_at` bigint(20) not null,
    `created_at` bigint(20) not null,
    primary key (`id`),
    unique (`name`)
) engine=InnoDB default charset=utf8 collate utf8_general_ci;

create table if not exists `group_user` (
    `group_id` bigint(20) not null,
    `user_id` bigint(20) not null,
    unique index `ix_group_user` (`group_id`, `user_id`)
) engine=InnoDB default charset=utf8 collate utf8_general_ci;

create table if not exists `todo` (
    `id` bigint(20) not null auto_increment,
    `title` varchar(1024) not null,
    `note` varchar(1024) not null,
    `owner_type` int(10) not null default 0,
    `user_id` bigint(20) not null,
    `group_id` bigint(20) not null default 0,
    `todo_state_id` bigint(20) not null default 0,
    `updated_at` bigint(20) not null,
    `created_at` bigint(20) not null,
    primary key (`id`)
) engine=InnoDB default charset=utf8 collate utf8_general_ci;

create table if not exists `todo_state` (
    `id` bigint(20) not null,
    `name` varchar(255) not null,
    `description` varchar(255),
    `updated_at` bigint(20) not null,
    `created_at` bigint(20) not null,
    primary key (`id`),
    unique (`name`)
) engine=InnoDB default charset=utf8 collate utf8_general_ci;
