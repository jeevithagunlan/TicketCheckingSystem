package Chk_Ticket;
import java.util.HashMap;
import java.util.Map;

	public class TrainTicketSystem {
	    private Map<String, Ticket> tickets;
	    private Map<String, String> seatAllocations;

	    public TrainTicketSystem() {
	        tickets = new HashMap<>();
	        seatAllocations = new HashMap<>();
	    }

	    public String purchaseTicket(String from, String to, String firstName, String lastName, String email) {
	        Ticket ticket = new Ticket(from, to, firstName, lastName, email, 20);
	        tickets.put(email, ticket);
	        assignSeat(ticket);
	        return "Ticket purchased successfully.\n" + ticket.toString();
	    }

	    private void assignSeat(Ticket ticket) {
	        String section = seatAllocations.size() % 2 == 0 ? "A" : "B";
	        seatAllocations.put(ticket.getEmail(), section);
	        ticket.setSeat(section + (seatAllocations.size() % 2 == 0 ? 1 : 2));
	    }

	    public String viewReceipt(String email) {
	        Ticket ticket = tickets.get(email);
	        if (ticket != null) {
	            return ticket.toString();
	        } else {
	            return "Ticket not found for the provided email.";
	        }
	    }

	    public String viewSeatAllocation(String section) {
	        StringBuilder result = new StringBuilder();
	        for (Map.Entry<String, String> entry : seatAllocations.entrySet()) {
	            if (entry.getValue().equals(section)) {
	                result.append(entry.getKey()).append(" : ").append(seatAllocations.get(entry.getKey())).append("\n");
	            }
	        }
	        return result.toString();
	    }

	    public String removeUser(String email) {
	        if (tickets.containsKey(email)) {
	            Ticket removedTicket = tickets.remove(email);
	            seatAllocations.remove(email);
	            return "Ticket for " + removedTicket.getFullName() + " removed successfully.";
	        } else {
	            return "Ticket not found for the provided email.";
	        }
	    }

	    public String modifySeat(String email, String newSection) {
	        if (tickets.containsKey(email)) {
	            Ticket ticket = tickets.get(email);
	            seatAllocations.remove(email);
	            ticket.setSeat(newSection + (seatAllocations.size() % 2 == 0 ? 1 : 2));
	            seatAllocations.put(email, newSection);
	            return "Seat modified successfully for " + ticket.getFullName() + ".\n" + ticket.toString();
	        } else {
	            return "Ticket not found for the provided email.";
	        }
	    }

	    public static void main(String[] args) {
	        TrainTicketSystem ticketSystem = new TrainTicketSystem();

	        // Example usage
	        System.out.println(ticketSystem.purchaseTicket("London", "France", "John", "Doe", "john.doe@example.com"));
	        System.out.println(ticketSystem.viewReceipt("john.doe@example.com"));
	        System.out.println(ticketSystem.viewSeatAllocation("A"));
	        System.out.println(ticketSystem.modifySeat("john.doe@example.com", "B"));
	        System.out.println(ticketSystem.viewSeatAllocation("B"));
	        System.out.println(ticketSystem.removeUser("john.doe@example.com"));
	    }
	}

	class Ticket {
	    private String from;
	    private String to;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private double price;
	    private String seat;

	    public Ticket(String from, String to, String firstName, String lastName, String email, double price) {
	        this.from = from;
	        this.to = to;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.price = price;
	    }

	    public String getFullName() {
	        return firstName + " " + lastName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setSeat(String seat) {
	        this.seat = seat;
	    }

	    @Override
	    public String toString() {
	        return "From: " + from + "\nTo: " + to + "\nUser: " + getFullName() + "\nPrice Paid: $" + price + "\nSeat: " + seat;
	    }
	}