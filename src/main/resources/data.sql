
INSERT INTO `user` (`id`, `dni`, `name`, `surname`, `username`, `city`, `country`, `birthdate`, `phone_number`, `email`, `password`, `activate`, `activate_code`, `register_date`) VALUES
('1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', '45389330A', 'Martín', 'Nieto', 'MNP', 'Jerez de la Frontera', 'España', '2024-01-01', 685095840, 'martinnietop01@gmail.com', '$2a$10$gr9LjH6J19SJDlsNHX17IetkDsz8MWiX1rWZWSj/gJt3ZVA0rI.Li', b'1', '', '2024-01-04');

INSERT INTO `user` (`id`, `dni`, `name`, `surname`, `username`, `city`, `country`, `birthdate`, `phone_number`, `email`, `password`, `activate`, `activate_code`, `register_date`) VALUES
('409f47de-58b7-4fa5-b661-52ade3e8872a', '76585361F', 'Pablo ', 'Fernandez', 'PR', 'Jerez De La Frontera', 'España', '2024-01-01', 722409367, 'pabloqmiras@gmail.com', '$2a$10$o0OQYMCv9GV0GnOWQZcJBOorbN4faZ7TwfpNPvVWfrpN4xlLDl/r6', b'1', '', '2024-01-11');

INSERT INTO `user` (`id`, `dni`, `name`, `surname`, `username`, `city`, `country`, `birthdate`, `phone_number`, `email`, `password`, `activate`, `activate_code`, `register_date`) VALUES
('4b21be8e-711d-409a-a052-321a677fc386', '13702514B', 'FINANCE', 'FINANCE', 'FINANCE', 'Jerez', 'España', '2024-01-01', 333222111, 'finance@gmail.com', '$2a$10$sHVrLP/W53xAHsRD5agqoOURN3vjDAYPpN70scOHt1k9.a.OIkyze', b'1', '', '2024-01-05');

INSERT INTO `user` (`id`, `dni`, `name`, `surname`, `username`, `city`, `country`, `birthdate`, `phone_number`, `email`, `password`, `activate`, `activate_code`, `register_date`) VALUES
('776556cb-5db4-423c-a99d-1f81e9c61290', '04560776Z', 'CUSTOMERSUPPORT', 'CUSTOMERSUPPORT', 'CUSTOMERSUPPORT', 'Jerez', 'España', '2024-01-01', 666555444, 'customersupport@gmail.com', '$2a$10$TsOEFCJbfLV3fr.397nuOuNGr0/ng/uQPKv3XCTeWPHa.ZmSkdkLy', b'1', '', '2024-01-05');

INSERT INTO `user` (`id`, `dni`, `name`, `surname`, `username`, `city`, `country`, `birthdate`, `phone_number`, `email`, `password`, `activate`, `activate_code`, `register_date`) VALUES
('78167920-0738-4ca7-93d3-bdb2e167a206', '12345678A', 'adminn', 'adminn', 'admin', 'Madrid', 'España', '1999-01-01', 123456789, 'adminn@gmail.com', '$2a$10$DoByaYKJUjEOef1/.4RSKOrzXZwfco0.K.CdEED6igYUckTDY4auq', b'1', '', '2024-01-02');

INSERT INTO `user` (`id`, `dni`, `name`, `surname`, `username`, `city`, `country`, `birthdate`, `phone_number`, `email`, `password`, `activate`, `activate_code`, `register_date`) VALUES
('78a5a5fa-e116-41b2-a9fa-201471aefd3e', '14047641T', 'CUSTOMER', 'CUSTOMER', 'CUSTOMER', 'Jerez', 'España', '2024-01-01', 444555666, 'customer@gmail.com', '$2a$10$JtxLhJyFOxMuXJDJJNcj8OZ.6pAyCYLUgxEKYvs93GxtmX1Ya5dI6', b'1', '', '2024-01-05');

INSERT INTO `user` (`id`, `dni`, `name`, `surname`, `username`, `city`, `country`, `birthdate`, `phone_number`, `email`, `password`, `activate`, `activate_code`, `register_date`) VALUES
('8df3a9fe-5a52-4479-a5c6-62938d4cce96', '56893528S', 'MARKETING', 'MARKETING', 'MARKETING', 'Jerez', 'España', '2024-01-01', 111222333, 'marketing@gmail.com', '$2a$10$ZI151XW0EZEXs2VeKust6utX9R8H8urJDYsjxuG8PeOop95ACHKUq', b'1', '', '2024-01-05');


INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('78167920-0738-4ca7-93d3-bdb2e167a206', 'ADMIN');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'CUSTOMER');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'FINANCE');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'MARKETING');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'CUSTOMERSUPPORT');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'ADMIN');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('4b21be8e-711d-409a-a052-321a677fc386', 'FINANCE');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('8df3a9fe-5a52-4479-a5c6-62938d4cce96', 'MARKETING');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('78a5a5fa-e116-41b2-a9fa-201471aefd3e', 'CUSTOMER');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('776556cb-5db4-423c-a99d-1f81e9c61290', 'CUSTOMERSUPPORT');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('409f47de-58b7-4fa5-b661-52ade3e8872a', 'CUSTOMER');


INSERT INTO `fee` (`id`, `title`, `description_mobile`, `description_fiber`, `descriptiontv`, `monthly_data`, `monthly_calls`, `monthlysms`, `monthlyprice`, `max_mobile_lines`, `status`) VALUES
('2b01f0e9-ce70-4e18-9112-4a0d149f8d73', 'PRUEBA', 'DM', 'DF', 'DTV', 14, 14, 14, 112, 5, 'ACTIVA');

INSERT INTO `fee` (`id`, `title`, `description_mobile`, `description_fiber`, `descriptiontv`, `monthly_data`, `monthly_calls`, `monthlysms`, `monthlyprice`, `max_mobile_lines`, `status`) VALUES
('75f2f3c1-0e4f-448a-a00e-01350250506d', 'prueba3.3', 'mmmm', 'ffff', 'tttt', 122, 122, 122, 122, 5, 'INACTIVA');

INSERT INTO `fee` (`id`, `title`, `description_mobile`, `description_fiber`, `descriptiontv`, `monthly_data`, `monthly_calls`, `monthlysms`, `monthlyprice`, `max_mobile_lines`, `status`) VALUES
('8784d7c6-a391-4e75-8139-87dc714baccd', 'p', 'p', 'p', 'p', 1, 1, 1, 1, 2, 'INACTIVA');

INSERT INTO `fee` (`id`, `title`, `description_mobile`, `description_fiber`, `descriptiontv`, `monthly_data`, `monthly_calls`, `monthlysms`, `monthlyprice`, `max_mobile_lines`, `status`) VALUES
('e0a6b3f3-d93b-472f-8540-afa5482c8a2e', 'prueba2.2', 'mmm', 'fff', 'ttt', 90, 90, 90, 21, 90, 'ACTIVA');

INSERT INTO `fee` (`id`, `title`, `description_mobile`, `description_fiber`, `descriptiontv`, `monthly_data`, `monthly_calls`, `monthlysms`, `monthlyprice`, `max_mobile_lines`, `status`) VALUES
('e2e7461b-6041-47ac-b899-d3758d64f114', 'prueba4.4', 'dm', 'df', 'dtv', 14, 14, 14, 14, 14, 'ACTIVA');


INSERT INTO `contract` (`id`, `user_id`, `status`, `fee_id`, `start_date`, `end_date`) VALUES
('352a7fff-f7e9-4b6c-b4ab-7f5f7622f3b2', '409f47de-58b7-4fa5-b661-52ade3e8872a', 'ACTIVO', '2b01f0e9-ce70-4e18-9112-4a0d149f8d73', '2024-01-11', NULL);

INSERT INTO `contract` (`id`, `user_id`, `status`, `fee_id`, `start_date`, `end_date`) VALUES
('bb88722c-3e0b-4841-9c2a-de41f5b53e2a', '1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'CANCELADO', 'e2e7461b-6041-47ac-b899-d3758d64f114', '2024-01-08', '2024-01-08');

INSERT INTO `mobileline` (`id`, `user_id`, `contract_id`, `phone_number`, `roaming`, `share_data`) VALUES
('f399a23e-add0-421e-b8f5-8ffec077ee9e', '409f47de-58b7-4fa5-b661-52ade3e8872a', '352a7fff-f7e9-4b6c-b4ab-7f5f7622f3b2', 652652652, b'0', b'0');

INSERT INTO `mobileline` (`id`, `user_id`, `contract_id`, `phone_number`, `roaming`, `share_data`) VALUES
('fa4c5896-9757-4439-8a57-537591a375c3', '409f47de-58b7-4fa5-b661-52ade3e8872a', '352a7fff-f7e9-4b6c-b4ab-7f5f7622f3b2', 963963963, b'1', b'0');

INSERT INTO `sms_records` (`id`, `mobile_line_id`, `destination_phone_number`, `sms_text`, `sms_date`) VALUES
('f4310acd-b01c-11ee-b6b5-0a0027000020', 'f399a23e-add0-421e-b8f5-8ffec077ee9e', 11, '11', '2024-01-11');

INSERT INTO `sms_records` (`id`, `mobile_line_id`, `destination_phone_number`, `sms_text`, `sms_date`) VALUES
('f4311bd5-b01c-11ee-b6b5-0a0027000020', 'f399a23e-add0-421e-b8f5-8ffec077ee9e', 11, '11', '2024-01-11');

INSERT INTO `sms_records` (`id`, `mobile_line_id`, `destination_phone_number`, `sms_text`, `sms_date`) VALUES
('f4312695-b01c-11ee-b6b5-0a0027000020', 'f399a23e-add0-421e-b8f5-8ffec077ee9e', 11, '11', '2024-01-11');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('db617071-b01c-11ee-b6b5-0a0027000020', 'f399a23e-add0-421e-b8f5-8ffec077ee9e', 11, '2024-01-11');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('db617bc6-b01c-11ee-b6b5-0a0027000020', 'f399a23e-add0-421e-b8f5-8ffec077ee9e', 11, '2024-01-11');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('db61859f-b01c-11ee-b6b5-0a0027000020', 'f399a23e-add0-421e-b8f5-8ffec077ee9e', 11, '2024-01-11');

INSERT INTO `call_records` (`id`, `mobile_line_id`, `destination_phone_number`, `seconds`, `call_date`) VALUES
('bdfa16b9-b01c-11ee-b6b5-0a0027000020', 'f399a23e-add0-421e-b8f5-8ffec077ee9e', 11, 11, '2024-01-11');

INSERT INTO `call_records` (`id`, `mobile_line_id`, `destination_phone_number`, `seconds`, `call_date`) VALUES
('bdfa245d-b01c-11ee-b6b5-0a0027000020', 'f399a23e-add0-421e-b8f5-8ffec077ee9e', 11, 11, '2024-01-11');

INSERT INTO `call_records` (`id`, `mobile_line_id`, `destination_phone_number`, `seconds`, `call_date`) VALUES
('bdfa2de0-b01c-11ee-b6b5-0a0027000020', 'f399a23e-add0-421e-b8f5-8ffec077ee9e', 11, 11, '2024-01-11');