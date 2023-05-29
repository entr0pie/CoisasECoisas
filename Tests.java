package coisas_e_coisas;

public class Tests {
	public void testCustomer() {
		System.out.println("====[ CUSTOMER ]=====================================================");

		System.out.println("Criando novo cliente ...");
		Customer customer1 = new Customer("123456789", "Pedro", "de Lara");
        customer1.save();
        System.out.println("ID (PRIMARY KEY) Pedro de Lara: " + customer1.getId());

        System.out.println("Carregando o Pedro de Lara do banco de dados (findOne)... ");
        Customer customer2 = Customer.findOne(customer1.getId());
        if (customer2 != null) {
            System.out.println("Atributos:");
        	System.out.println("	Id: " + customer2.getId());
        	System.out.println("	CPF: " + customer2.CPF);
            System.out.println("	Name: " + customer2.name);
            System.out.println("	Surname: " + customer2.surname);
        }

        System.out.println("Deletando Pedro de Lara...");
        customer1.delete();
        
        System.out.println("Buscando linha recém deletada (deve retornar null): " + Customer.findOne(customer1.getId()));
		System.out.println("=====================================================================");

	}
	
	public void testWorker() {
		System.out.println("====[ WORKER ]=======================================================");
		System.out.println("Criando nova linha no banco de dados...");
		
		Worker worker1 = new Worker("123456789", "Lara", "de Pedro", "1234567890", "Rua Chile");
        worker1.save();
        System.out.println("ID (PRIMARY KEY) Lara de Pedro: " + worker1.getId());

        System.out.println("Buscando o trabalhador pré-cadastrado (findOne)... ");
        Worker worker2 = Worker.findOne(1);
        
        if (worker2 != null) {
            System.out.println("Atributos:");
            System.out.println("	CPF: " + worker2.CPF);
            System.out.println("	Name: " + worker2.name);
            System.out.println("	Surname: " + worker2.surname);
            System.out.println("	Phone Number: " + worker2.phone_number);
            System.out.println("	Address: " + worker2.address);
        }
        
        System.out.println("Buscando serviços deste trabalhador...");
        
        for (int i = 0; i < worker2.services.size(); i++) {
        	System.out.println(String.format("	Serviço %s: %s", i + 1, worker2.services.get(i).name));
        }

        System.out.println("Deletando Lara de Pedro...");
        worker1.delete();
        
        System.out.println("Buscando linha recém deletada (deve retornar null): " + Worker.findOne(worker1.getId()));
        
		System.out.println("=====================================================================");
	}
	
	
	public void testService() {
		System.out.println("====[ SERVICE ]======================================================");
		
		System.out.println("Criando nova linha no banco de dados...");
		Service service1 = new Service("Limpeza de rodapé", "Alvenaria", 50.0);
        service1.save();
        
        System.out.println("ID (PRIMARY KEY) Limpeza de rodapé: " + service1.getId());

        System.out.println("Carregando a Limpeza de Rodapé do banco de dados (findOne)... ");
        Service service2 = Service.findOne(service1.getId());
        if (service2 != null) {
            System.out.println("Atributos:");
            System.out.println("	Name: " + service2.name);
            System.out.println("	Type: " + service2.type);
            System.out.println("	Price: " + service2.price);
        }

        System.out.println("Deletando Limpeza de Rodapé... ");
        service1.delete();
        System.out.println("Buscando linha recém deletada (deve retornar null): " + Service.findOne(service1.getId()));
        
		System.out.println("=====================================================================");

	}
	
	public void testRequest() {
		System.out.println("====[ REQUEST ]======================================================");

        System.out.println("Criando novo pedido...");
		Request request1 = new Request(1, "2023-05-28");
		request1.save();
        
        System.out.println("ID do pedido (2023-05-28): " + request1.getId());

        System.out.println("Buscando pedido " + request1.getId() + " no banco de dados (findOne)...");
        Request request2 = Request.findOne(request1.getId());
        
        if (request2 != null) {
            System.out.println("Atributos:");
            System.out.println("	Customer ID: " + request2.customer_id);
            System.out.println("	Customer name: " + Customer.findOne(request2.customer_id).name);
            System.out.println("	Contract Date: " + request2.contract_date);
        }
        
        System.out.println("Serviços:");
        Request service_request = Request.findOne(1);
        for (int i = 0; i < service_request.services.size(); i++) { // Trocar aqui para request2
        	System.out.println(String.format("  Serviço %s: %s", i + 1, service_request.services.get(i).name));
        }

        System.out.println("Deletando pedido " + request1.getId()  + "...");
        request1.delete();
        
        System.out.println("Buscando linha recém deletada (deve retornar null): " + Request.findOne(request1.getId()));

        
		System.out.println("=====================================================================");
	}
}
