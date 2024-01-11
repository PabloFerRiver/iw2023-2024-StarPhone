
INSERT INTO `user` (`id`, `dni`, `name`, `surname`, `username`, `city`, `country`, `birthdate`, `phone_number`, `email`, `password`, `activate`, `activate_code`, `register_date`) VALUES
('1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', '45389330A', 'Martín', 'Nieto', 'MNP', 'Jerez de la Frontera', 'España', '2024-01-01', 685095840, 'martinnietop01@gmail.com', '$2a$10$gr9LjH6J19SJDlsNHX17IetkDsz8MWiX1rWZWSj/gJt3ZVA0rI.Li', b'1', '', '2024-01-04');

INSERT INTO `user` (`id`, `dni`, `name`, `surname`, `username`, `city`, `country`, `birthdate`, `phone_number`, `email`, `password`, `activate`, `activate_code`, `register_date`) VALUES
('4b21be8e-711d-409a-a052-321a677fc386', '13702514B', 'FINANCE', 'FINANCE', 'FINANCE', 'Jerez', 'España', '2024-01-01', 333222111, 'finance@gmail.com', '$2a$10$sHVrLP/W53xAHsRD5agqoOURN3vjDAYPpN70scOHt1k9.a.OIkyze', b'1', '', '2024-01-05');

INSERT INTO `user` (`id`, `dni`, `name`, `surname`, `username`, `city`, `country`, `birthdate`, `phone_number`, `email`, `password`, `activate`, `activate_code`, `register_date`) VALUES
('776556cb-5db4-423c-a99d-1f81e9c61290', '04560776Z', 'CUSTOMERSUPPORT', 'CUSTOMERSUPPORT', 'CUSTOMERSUPPORT', 'Jerez', 'España', '2024-01-01', 666555444, 'customersupport@gmail.com', '$2a$10$TsOEFCJbfLV3fr.397nuOuNGr0/ng/uQPKv3XCTeWPHa.ZmSkdkLy', b'1', '', '2024-01-05');

INSERT INTO `user` (`id`, `dni`, `name`, `surname`, `username`, `city`, `country`, `birthdate`, `phone_number`, `email`, `password`, `activate`, `activate_code`, `register_date`) VALUES
('78a5a5fa-e116-41b2-a9fa-201471aefd3e', '14047641T', 'CUSTOMER', 'CUSTOMER', 'CUSTOMER', 'Jerez', 'España', '2024-01-01', 444555666, 'customer@gmail.com', '$2a$10$JtxLhJyFOxMuXJDJJNcj8OZ.6pAyCYLUgxEKYvs93GxtmX1Ya5dI6', b'1', '', '2024-01-05');

INSERT INTO `user` (`id`, `dni`, `name`, `surname`, `username`, `city`, `country`, `birthdate`, `phone_number`, `email`, `password`, `activate`, `activate_code`, `register_date`) VALUES
('8df3a9fe-5a52-4479-a5c6-62938d4cce96', '56893528S', 'MARKETING', 'MARKETING', 'MARKETING', 'Jerez', 'España', '2024-01-01', 111222333, 'marketing@gmail.com', '$2a$10$ZI151XW0EZEXs2VeKust6utX9R8H8urJDYsjxuG8PeOop95ACHKUq', b'1', '', '2024-01-05');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'CUSTOMER');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'FINANCE');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'MARKETING');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'CUSTOMERSUPPORT');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('4b21be8e-711d-409a-a052-321a677fc386', 'MARKETING');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('4b21be8e-711d-409a-a052-321a677fc386', 'FINANCE');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('8df3a9fe-5a52-4479-a5c6-62938d4cce96', 'MARKETING');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('78a5a5fa-e116-41b2-a9fa-201471aefd3e', 'CUSTOMER');

INSERT INTO `user_roles` (`user_id`, `roles`) VALUES
('776556cb-5db4-423c-a99d-1f81e9c61290', 'CUSTOMERSUPPORT');


INSERT INTO `fee` (`id`, `title`, `description_mobile`, `description_fiber`, `descriptiontv`, `monthly_data`, `monthly_calls`, `monthlysms`, `monthlyprice`, `max_mobile_lines`) VALUES
('2b01f0e9-ce70-4e18-9112-4a0d149f8d73', 'Plan Solitario', '100 minutos incluidos, el resto a 10 cent/min', '50Gb de internet ', 'Ninguna plataforma incluida', 1, 1, 1, 1, 1);

INSERT INTO `fee` (`id`, `title`, `description_mobile`, `description_fiber`, `descriptiontv`, `monthly_data`, `monthly_calls`, `monthlysms`, `monthlyprice`, `max_mobile_lines`) VALUES
('75f2f3c1-0e4f-448a-a00e-01350250506d', 'Plan Familiar', 'Llamadas iimitadas', 'Toda la casa conectada gracias a la fibra', 'Todas las plataformas incluidas', 122, 122, 122, 122, 5);

INSERT INTO `fee` (`id`, `title`, `description_mobile`, `description_fiber`, `descriptiontv`, `monthly_data`, `monthly_calls`, `monthlysms`, `monthlyprice`, `max_mobile_lines`) VALUES
('e0a6b3f3-d93b-472f-8540-afa5482c8a2e', 'Plan Enamorados ', 'Llamadas ilimitadas entre líneas del contrato, el resto 5 cent/min', 'Incluida fibra óptica de última generación', 'Unicamente plataformas de Entretenimiento', 90, 90, 90, 21, 2);

INSERT INTO `fee` (`id`, `title`, `description_mobile`, `description_fiber`, `descriptiontv`, `monthly_data`, `monthly_calls`, `monthlysms`, `monthlyprice`, `max_mobile_lines`) VALUES
('e2e7461b-6041-47ac-b899-d3758d64f114', 'Plan Streaming', 'No viene con minutos incluidos', '1Gbps de fibra óptica de última generación', 'Únicamente esta incluida Amazon Prime', 14, 14, 14, 14, 14);


INSERT INTO `contract` (`id`, `user_id`, `status`, `fee_id`, `start_date`, `end_date`) VALUES
('0ae1bc46-3178-4ed3-ad5d-baa62f3b2352', '1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'ACTIVO', 'e0a6b3f3-d93b-472f-8540-afa5482c8a2e', '2024-01-08', NULL);

INSERT INTO `contract` (`id`, `user_id`, `status`, `fee_id`, `start_date`, `end_date`) VALUES
('0b9eb791-5be9-4b53-8504-512668261723', '1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'ENPROCESO', 'e0a6b3f3-d93b-472f-8540-afa5482c8a2e', '2024-01-08', NULL);

INSERT INTO `contract` (`id`, `user_id`, `status`, `fee_id`, `start_date`, `end_date`) VALUES
('63a73ef9-e9c8-4c96-8780-7351801eb372', '1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'ACTIVO', '75f2f3c1-0e4f-448a-a00e-01350250506d', '2024-01-08', NULL);

INSERT INTO `contract` (`id`, `user_id`, `status`, `fee_id`, `start_date`, `end_date`) VALUES
('bb88722c-3e0b-4841-9c2a-de41f5b53e2a', '1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'CANCELADO', 'e2e7461b-6041-47ac-b899-d3758d64f114', '2024-01-08', '2024-01-08');

INSERT INTO `contract` (`id`, `user_id`, `status`, `fee_id`, `start_date`, `end_date`) VALUES
('ddecbf1b-c393-475f-a11a-f6aea09d58be', '1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'ACTIVO', '2b01f0e9-ce70-4e18-9112-4a0d149f8d73', '2024-01-07', NULL);


INSERT INTO `mobileline` (`id`, `user_id`, `contract_id`, `phone_number`, `roaming`, `share_data`) VALUES
('46b371cd-c7e5-46fb-9db7-2e85ec82efc6', '1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', '63a73ef9-e9c8-4c96-8780-7351801eb372', 655214457, b'1', b'0');

INSERT INTO `mobileline` (`id`, `user_id`, `contract_id`, `phone_number`, `roaming`, `share_data`) VALUES
('4ab1898e-f0c2-4270-be8e-91dff73a63ff', '1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', '0b9eb791-5be9-4b53-8504-512668261723', 111222333, b'0', b'0');

INSERT INTO `mobileline` (`id`, `user_id`, `contract_id`, `phone_number`, `roaming`, `share_data`) VALUES
('69c9e0ec-5c09-48a1-b9c7-0a744fbdd512', '1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'ddecbf1b-c393-475f-a11a-f6aea09d58be', 956342347, b'1', b'1');

INSERT INTO `mobileline` (`id`, `user_id`, `contract_id`, `phone_number`, `roaming`, `share_data`) VALUES
('7cbbd905-a2c6-4958-88fd-bd69a1dab66b', '1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', 'ddecbf1b-c393-475f-a11a-f6aea09d58be', 605289874, b'1', b'0');

INSERT INTO `mobileline` (`id`, `user_id`, `contract_id`, `phone_number`, `roaming`, `share_data`) VALUES
('d95dceed-c892-4ff4-b71a-3b0de3b9d0cf', '1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', '63a73ef9-e9c8-4c96-8780-7351801eb372', 555555555, b'0', b'0');

INSERT INTO `mobileline` (`id`, `user_id`, `contract_id`, `phone_number`, `roaming`, `share_data`) VALUES
('fda9acb9-464a-4808-b35e-c0dddf0e89eb', '1ee8be06-64e9-4ddf-b39f-8dfcc36eef86', '63a73ef9-e9c8-4c96-8780-7351801eb372', 333222111, b'0', b'0');


INSERT INTO `call_records` (`id`, `mobile_line_id`, `destination_phone_number`, `seconds`, `call_date`) VALUES
('9bd571d1-af03-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 147852369, 56, '2023-12-08');

INSERT INTO `call_records` (`id`, `mobile_line_id`, `destination_phone_number`, `seconds`, `call_date`) VALUES
('9bd58ffe-af03-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 369852147, 120, '2024-01-02');

INSERT INTO `call_records` (`id`, `mobile_line_id`, `destination_phone_number`, `seconds`, `call_date`) VALUES
('c11085bb-af01-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 123321123, 32, '2024-01-09');

INSERT INTO `call_records` (`id`, `mobile_line_id`, `destination_phone_number`, `seconds`, `call_date`) VALUES
('c1109401-af01-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 123321123, 32, '2024-01-09');



INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('0edc8ced-af05-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 35.53, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('0edc9ade-af05-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 43.34, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('3fdb67a4-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('3fdb74ea-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('3fdb807b-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('3fdb8c27-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('3fdb9796-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('3fdba301-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('45a02cd8-af05-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 15.51, '2024-01-02');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('45a03a7f-af05-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 12.21, '2023-12-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('7e27eb49-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('7e27f491-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('7e27fe66-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('7e280795-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('8f40e249-af10-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('8f40f5aa-af10-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('8f4104da-af10-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('8f4113c8-af10-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('bb67f00d-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('bb67fc77-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('bb680826-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('bb681375-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('bb681d6e-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');

INSERT INTO `data_usage_records` (`id`, `mobile_line_id`, `megas`, `data_usage_date`) VALUES
('bb6826fa-af0f-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 1, '2024-01-09');



INSERT INTO `sms_records` (`id`, `mobile_line_id`, `destination_phone_number`, `sms_text`, `sms_date`) VALUES
('4f230258-af04-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 548721397, 'Hola4Hola4Hola4Hola4Hola4Hola4Hola4Hola4Hola4Hola4Hola4Hola4Hola4', '2024-01-09');

INSERT INTO `sms_records` (`id`, `mobile_line_id`, `destination_phone_number`, `sms_text`, `sms_date`) VALUES
('681fabc9-af03-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 123321123, 'HolaHolaHolaHolaHolaHolaHolaHolaHolaHolaHolaHolaHolaHolaHolaHola', '2024-01-02');

INSERT INTO `sms_records` (`id`, `mobile_line_id`, `destination_phone_number`, `sms_text`, `sms_date`) VALUES
('76aaa673-af03-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 564456654, 'Hola2Hola2Hola2Hola2Hola2Hola2Hola2Hola2Hola2Hola2Hola2Hola2Hola2', '2024-01-09');

INSERT INTO `sms_records` (`id`, `mobile_line_id`, `destination_phone_number`, `sms_text`, `sms_date`) VALUES
('ad3cf4c8-af03-11ee-838c-0a0027000020', '46b371cd-c7e5-46fb-9db7-2e85ec82efc6', 456987123, 'Hola3Hola3Hola3Hola3Hola3Hola3Hola3Hola3Hola3Hola3Hola3Hola3Hola3', '2024-01-09');




