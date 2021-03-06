set foreign_key_checks = 0;
truncate table `role_user`;
truncate table `user`;
truncate table `role`;
truncate table `todo_state`;
truncate table `todo`;
set foreign_key_checks = 1;

-- Value of the `password` is generated by ./gradlew encodePassword -Pargs="test"
-- See build.gradle
insert ignore into `user` (`id`, `username`, `password`, `enabled`, `created_at`, `updated_at`) values (1, 'test', 'c3bf653f3addf1102cecb043a423915aa345b85131e7ea0f84108839415ded416574799c648a1289', true, unix_timestamp(), unix_timestamp());

insert ignore into `role` (`id`, `name`) values (1, 'ROLE_ADMIN');
insert ignore into `role` (`id`, `name`) values (2, 'ROLE_USER');

insert ignore into `role_user` (`role_id`, `user_id`) values (2, 1);

insert ignore into `todo_state` (`id`, `name`, `description`, `created_at`, `updated_at`) values (1, 'none', 'Not yet started', unix_timestamp(), unix_timestamp());
insert ignore into `todo_state` (`id`, `name`, `description`, `created_at`, `updated_at`) values (2, 'done', 'Done', unix_timestamp(), unix_timestamp());
insert ignore into `todo_state` (`id`, `name`, `description`, `created_at`, `updated_at`) values (3, 'hold', 'Hold', unix_timestamp(), unix_timestamp());
