-- Inserir endereços
-- Inserir endereços
INSERT INTO address (id, street, number, cep, neighborhood, city, state) VALUES
(1, 'Rua das Aves', '123', '12345-678', 'Centro', 'São Paulo', 'SP'),
(2, 'Avenida dos Frangos', '456', '87654-321', 'Vila Rural', 'Campinas', 'SP'),
(3, 'Estrada das Galinhas', '789', '54321-876', 'Zona Rural', 'Ribeirão Preto', 'SP');

-- Inserir clientes
INSERT INTO client (id, name, cnpj, email, phone, status) VALUES
(1, 'Granja Feliz Ltda', '12345678000190', 'contato@granjafeliz.com.br', '11987654321', 'ACTIVE'),
(2, 'Ovos do Campo S.A.', '98765432000110', 'contato@ovosdocampo.com.br', '11912345678', 'ACTIVE');

-- Inserir fazendas (sem referência a employee)
INSERT INTO farm (id, name, address_id, client_id) VALUES
(1, 'Fazenda Bom Sucesso', 1, 1),
(2, 'Fazenda Vista Linda', 2, 2);

-- Inserir lotes
INSERT INTO batch (id, name, start_date, status, farm_id) VALUES
(1, 'Lote 2023-01', '2023-01-15', 'ACTIVE', 1),
(2, 'Lote 2023-02', '2023-03-20', 'ACTIVE', 1),
(3, 'Lote 2023-A', '2023-02-10', 'ACTIVE', 2);

---- Inserir aviários
--INSERT INTO aviary (id, name, initial_amount_of_roosters, initial_amount_of_chickens, batch_id) VALUES
--(1, 'Aviário 1', 50, 500, 1),
--(2, 'Aviário 2', 45, 450, 1),
--(3, 'Aviário A', 60, 600, 3);
