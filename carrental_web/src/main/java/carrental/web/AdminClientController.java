package carrental.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import carrental.model.Client;
import carrental.repository.ClientRepository;

@Controller
public class AdminClientController {

	@Autowired
    ClientRepository clientRepository;



	@PostMapping("/adminclient")
	public String createAdminPageFromPost(Map<String, Object> model) {

		return "redirect:/adminclient";

	}

	@GetMapping("/adminclient")
	public String getAllClients(Map<String, Object> model) {

        List<Client> allClients = clientRepository.findAll();
        model.put("clients", allClients);
        model.put("client", new Client());
		
		return "adminclient";

	}

	@PostMapping("/adminclient/newclient")
    public String createClient(Map<String, Object> model, Client client) {
	    clientRepository.save(client);
        return "redirect:/adminclient";
    }

    @PostMapping("/adminclient/findclientid")
    public String findClientID(Map<String, Object> model, Client client) {
	    try {
	        Optional<Client> foundClient = clientRepository.findById(client.getId());
            Client unwrappedClient = foundClient.get();
            model.put("findClientByID", unwrappedClient );

        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
	    return getAllClients(model);
    }

    @PostMapping("/adminclient/findclientname")
    public String findClientName(Map<String, Object> model, Client client) {
	    try {
            List<Client> foundClients = clientRepository.findByName(client.getName());
            model.put("findClientsByName", foundClients);

        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        return getAllClients(model);
    }

    @PostMapping("/adminclient/delete")
    public String deleteClient(Map<String, Object> model, Client client) {
        try {
            clientRepository.deleteById(client.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        return getAllClients(model);
    }

    @PostMapping("/adminclient/update")
    public String updateClient(Map<String, Object> model, Client client) {

	    try {
            Optional<Client> searchedClient = clientRepository.findById(client.getId());
            Client updatedClient = searchedClient.get();

            updatedClient.setBooking(client.getBooking());
            updatedClient.setLoyaltyPoint(client.getLoyaltyPoint());
            updatedClient.setFeedback(client.getFeedback());
            updatedClient.setEmailAddress(client.getEmailAddress());
            updatedClient.setName(client.getName());
            updatedClient.setPhoneNumber(client.getPhoneNumber());

            clientRepository.save(updatedClient);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        return getAllClients(model);
    }


}
