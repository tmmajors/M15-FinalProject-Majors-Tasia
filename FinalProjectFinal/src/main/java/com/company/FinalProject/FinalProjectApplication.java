package com.company.FinalProject;

import com.company.FinalProject.Crypto.CryptoResponse;
import com.company.FinalProject.ISS.SpaceResponse;
import com.company.FinalProject.Weather.WeatherResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Scanner;


@SpringBootApplication
public class FinalProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(FinalProjectApplication.class, args);

		Scanner myScanner = new Scanner(System.in);

		while (true) {
			//Menu
			System.out.println("Menu");
			System.out.println(" ");
			//System.out.println("Please select an option from the menu below: ");
			System.out.println(" ");
			System.out.println("1. Local Weather");
			System.out.println("2. ISS Location");
			System.out.println("3. Weather at ISS Location");
			System.out.println("4. Cryptocurrency Prices");
			System.out.println("5. Exit");

			int userInput;
			
			do {
				try {
					System.out.println("Select a menu option: ");
					userInput = Integer.parseInt(myScanner.nextLine());
				}
				//if user enters a number out of range
				catch (NumberFormatException e) {
					userInput = 0;
				}
				if (userInput < 1 || userInput > 5) {
					System.out.println("Error: Invalid selection, please enter an integer in between 1-5");
				}
			} while (userInput < 1 || userInput > 5);

			//switch statement for user choice
			switch (userInput) {
				case 1:
					cityWeather();
					break;
				case 2:
					getISSLocation(issLocationInfo());
					break;
				case 3:
					weatherInfo();
					break;
				case 4:
					cryptoReturnInfo();
					break;
				default:
					System.out.println("Exiting App... Goodbye!");
					return;
			}
		}

	}


	//WEATHER
	public static String userCity(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter city here: ");
		return scanner.nextLine();
	}

	public static WeatherResponse getWeatherResponse(String weatherURI) {

		WebClient client = WebClient.create(weatherURI);
		WeatherResponse weatherResponse = null;

		try {
			Mono<WeatherResponse> response = client
					.get()
					.retrieve()
					.bodyToMono(WeatherResponse.class);
			weatherResponse = response.share().block();
		} catch (WebClientResponseException we) {
			int statusCode = we.getRawStatusCode();
			if (statusCode >= 400 && statusCode < 500) {
				System.out.println("Client Error");
			} else if (statusCode >= 500 && statusCode < 600) {
				System.out.println("Server Error");
			}
			System.out.println(we.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return weatherResponse;

	}

	public static void weatherReport(WeatherResponse weatherResponse){
		if (weatherResponse == null){
			return;
		}

		//print weather info out
		System.out.println(weatherResponse.getMain());
		System.out.println("Current weather status: ");
		System.out.println("Temperature: " + weatherResponse.getMain().getTemp());
		System.out.println("Feels Like: " + weatherResponse.getMain().getFeels_like());
		System.out.println("Max Temperature" + weatherResponse.getMain().getTemp_max() + " - " + weatherResponse.getMain().getTemp_min());
		System.out.println("Humidity: " + weatherResponse.getMain().getHumidity());
	}

	//Weather by city
	public static void cityWeather() {
	WeatherResponse weatherResponse;
	do {
		String city = userCity();
		String weatherURI = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=5a5b62808ec829bf95a5338109de9cc2&units=imperial";
		weatherResponse = getWeatherResponse(weatherURI);
	}
	while (weatherResponse == null);
	weatherReport(weatherResponse);
	}

	//ISS
	// Space API call
	public static SpaceResponse getIssCoordinates() {

		WebClient client = WebClient.create("http://api.open-notify.org/iss-now.json");
		SpaceResponse spaceResponse = null;

		try {
			Mono<SpaceResponse> response = client
					.get()
					.retrieve()
					.bodyToMono(SpaceResponse.class);

			spaceResponse = response.share().block();

		} catch (WebClientResponseException we) {
			int statusCode = we.getRawStatusCode();
			if (statusCode >= 400 && statusCode < 500) {
				System.out.println("Client Error");
			} else if (statusCode >= 500 && statusCode < 600) {
				System.out.println("Server Error");
			}
			System.out.println(we.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return spaceResponse;
	}

	//calls location of ISS Response
	public static void getISSLocation(SpaceResponse spaceResponse){
		if (spaceResponse == null){
			System.out.println("Error: Location unknown, please enter city name here: ");
			return;
		}

		System.out.println("ISS Coordinates: ");
		System.out.println("Latitude: "  +  spaceResponse.setIss_position().getLatitude());
		System.out.println("Longitude: "  +  spaceResponse.getIss_position().Longitude());
	}

		//prints location + returns weather
	public static SpaceResponse issLocationInfo(){
		SpaceResponse spaceResponse = getSpaceResponse();
		getISSLocation(spaceResponse);
		return spaceResponse;
	}

	public static void weatherInfo(){
		SpaceResponse spaceResponse = getSpaceResponse();
		WeatherResponse weatherResponse = null;
		if(spaceResponse != null){
			String weatherURI = "https://api.openweathermap.org/data/2.5/weather?lat="+spaceResponse.getIss_position().getLatitude() + "&lon=" + spaceResponse.getIss_position().getLongitude() + "&appid=5a5b62808ec829bf95a5338109de9cc2";
			weatherResponse = getWeatherResponse(weatherURI);
			//method for weather goes here(weatherResponse);
		}
	}


	//CRYPTO
	//gets user crypto input
	public static String cryptoInput() {
			System.out.println("Enter cryptocurrency symbol here: ");
			Scanner myScanner = new Scanner(System.in);
			return myScanner.nextLine();
	}

	//Crypto response
	public static CryptoResponse Response(String coinURI) {

		WebClient client = WebClient.create(coinURI);
		CryptoResponse cryptoResponse = null;

		//API try request
		try {
			Mono<CryptoResponse[]> response = client
					.get()
					.retrieve()
					.bodyToMono(CryptoResponse[].class);

			cryptoResponse = response.share().block()[0];

		} catch (ArrayIndexOutOfBoundsException e) {

		} catch (WebClientResponseException we) {
			int statusCode = we.getRawStatusCode();
			if (statusCode >= 400 && statusCode < 500) {
				System.out.println("Client Error");
			} else if (statusCode >= 500 && statusCode < 600) {
				System.out.println("Server Error");
			}
			System.out.println(we.getMessage());

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return cryptoResponse;
	}

	//prints crypto info
	public static void cryptoDisplay(CryptoResponse cryptoResponse) {
		if (cryptoResponse == null) {
			System.out.println("Error: Invalid currency value, please try again using the symbols BTC or ETH ");
			return;
		}
		System.out.println("Cryptocurrency name: " + cryptoResponse.getName());
		System.out.println("Cryptocurrency symbol: " + cryptoResponse.getAsset_id());
		System.out.println("Current price: " + "USD $:" + "%.2f" + cryptoResponse.getPrice_usd());
	}

	//crypto API
	public static void cryptoReturnInfo(){
		String assetID = cryptoInput();
		String coinURI = "https://rest.coinapi.io/v1/assets/" + assetID + "?apikey=802E149A-0965-4450-BD31-7A73C51867C6";
		CryptoResponse cryptoResponse = Response(coinURI);
		cryptoDisplay(cryptoResponse);
	}

}











