package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	
	private static final String kleuren[] 
			= new String[] { "Oranje", "Blauw", "Bruin", "Geel", "Grijs", "Groen", "Roze" };

	private List<Kaart> kaarten;

	public Deck() {
		kaarten = new ArrayList<>();
		voegKaartenToe();
		schudDeKaarten();
	}

	private void voegKaartenToe() {
		
		// De kleur kaarten
		//
		for (String kleur : kleuren) {
			for (int i = 0; i < 9; i++) {
				Kaart kaart = new Kaart(i+1, kleur, KaartType.Kleur);
				kaarten.add(kaart);
			}
		}
		
		// De Laatste rond kaart
		//
		Kaart laatsteRondKaart = new Kaart(1, "Laatste ronde", KaartType.LaatsteRonde);
		kaarten.add(laatsteRondKaart);
		
		// 10 +2 kaarten
		//
		for (int i=0;i<10;i++) {
			Kaart kaart = new Kaart(i+1, "+2", KaartType.Plus2);
			kaarten.add(kaart);
		}
		
		// 3 jokers
		//
		for (int i=0;i<3;i++) {
			Kaart kaart = new Kaart(i+1, "Joker", KaartType.Joker);
			kaarten.add(kaart);
		}
	}

	public void schudDeKaarten() {
		Random rnd = new Random();
		List<Kaart> list = new ArrayList<Kaart>();
		while (!kaarten.isEmpty()) {
			int index = Math.abs(rnd.nextInt()) % kaarten.size();
			Kaart kaart = kaarten.remove(index);
			list.add(kaart);
		}
		kaarten = list;
	}
	
	/**
	 * Verstop de "Laatste ronde" kaart in het deck
	 */
	public void verstopDeLaatsteRondeKaart() {
		
		List<Kaart> list = new ArrayList<Kaart>();
		
		// Remove the "Last Round" card from the deck and set it aside for now
		//
		Kaart laatsteRondeKaart = geefLaatsteRondeKaart();
		
		// Deal 15 cars face down from the supply
		//
		for (int i=0;i<15;i++) {
			list.add(geefKaart());
		}
		
		// Place the last round card on top of this stack of 15 cars
		//
		list.add(laatsteRondeKaart);
		
		// Then, place the rest of the card supply on top of this
		//
		list.addAll(kaarten);
		
		// Dit geeft ons een nieuwe deck
		//
		kaarten = list;
	}

	public Kaart geefKaart() {
		var kaart = kaarten.remove(kaarten.size() - 1);
		return kaart;
	}
	
	/**
	 * Zoek de laatste ronde kaart in het deck en haal hem eruit
	 * @return De laatste ronde kaart uit het deck
	 */
	public Kaart geefLaatsteRondeKaart() {
		for (Kaart kaart : kaarten) {
			if (kaart.getType()==KaartType.LaatsteRonde) {
				kaarten.remove(kaart);
				return kaart;
			}
		}
		throw new RuntimeException("Er zit geen laatste rond kaart in het deck!!!!");
	}
	
	public Kaart geefKaartMetKleur(String kleur) {
		for (Kaart kaart : kaarten) {
			if (kaart.getType()==KaartType.Kleur && kaart.getKleur().equals(kleur)) {
				kaarten.remove(kaart);
				return kaart;
			}
		}
		throw new IllegalArgumentException("Kaart met kleur '"+kleur+"' kon niet worden gevonden in het deck");
	}
	
	public static final String[] getKleuren() {
		return kleuren;
	}

	public static void main(String[] args) {
		Deck deck = new Deck();

		for (int i = 0; i < 5; i++) {
			Kaart kaart = deck.geefKaart();
			System.out.println("Kaart nr " + (i + 1) + " : " + kaart.getKleur());
		}

	}

}