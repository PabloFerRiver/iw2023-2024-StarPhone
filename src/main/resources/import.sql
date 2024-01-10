
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
