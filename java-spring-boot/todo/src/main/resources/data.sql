insert ignore into `user` (`id`, `username`, `password`, `enabled`, `created_at`, `updated_at`) values (1, 'test', 'c3bf653f3addf1102cecb043a423915aa345b85131e7ea0f84108839415ded416574799c648a1289', true, current_timestamp(), current_timestamp());

insert ignore into `role` (`id`, `name`) values (1, 'ROLE_ADMIN');
insert ignore into `role` (`id`, `name`) values (2, 'ROLE_USER');

insert ignore into `role_user` (`role_id`, `user_id`) values (2, 1);
