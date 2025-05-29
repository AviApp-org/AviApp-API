-- Inserir endereços
INSERT INTO address (street, number, cep, neighborhood, city, state) VALUES
('Rua das Aves', '123', '12345-678', 'Centro', 'São Paulo', 'SP'),
('Avenida dos Frangos', '456', '87654-321', 'Vila Rural', 'Campinas', 'SP'),
('Estrada das Galinhas', '789', '54321-876', 'Zona Rural', 'Ribeirão Preto', 'SP');

-- Inserir clientes
INSERT INTO client (name, cnpj, email, phone, status) VALUES
('Granja Feliz Ltda', '12345678000190', 'contato@granjafeliz.com.br', '11987654321', 'ACTIVE'),
('Ovos do Campo S.A.', '98765432000110', 'contato@ovosdocampo.com.br', '11912345678', 'ACTIVE');

-- Inserir fazendas (sem referência a employee)
INSERT INTO farm (name, address_id, client_id) VALUES
('Fazenda Bom Sucesso', 1, 1),
('Fazenda Vista Linda', 2, 2);

-- Inserir lotes
INSERT INTO batch (name, start_date, status, farm_id) VALUES
('Lote 2023-01', '2023-01-15', 'ACTIVE', 1),
('Lote 2023-02', '2023-03-20', 'ACTIVE', 1),
('Lote 2023-A', '2023-02-10', 'ACTIVE', 2);
