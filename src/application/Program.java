package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
	    
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);	
		
		System.out.println("Enter rental data");
		System.out.print("Car model: ");
		String model = sc.nextLine();
		
		System.out.print("Pickup (DD/MM/YYYY hh:ss): ");
		Date start = sdf.parse(sc.nextLine());
		
		System.out.print("Return (DD/MM/YYYY hh:ss): ");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(model));
		
		System.out.println();
		System.out.print("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
	    System.out.print("Enter price per day: ");
	    double pricePerDay = sc.nextDouble();
	    
	    RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
	    
	    rentalService.processInvoice(cr);
	    
	    System.out.println();
	    System.out.println("INVOICE: ");
	    System.out.println("Basic payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
	    System.out.println("Tax: " +   String.format("%.2f", cr.getInvoice().getTax()));
	    System.out.println("Total payment: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		sc.close();
		
	}

}
