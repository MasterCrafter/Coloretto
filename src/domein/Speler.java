package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Speler {
	private String naam;
	private Kaart kleurKaart;
	private List<Kaart> kaarten;

	public Speler(String naam) {
		super();
		this.naam = naam;
		kaarten = new ArrayList<Kaart>();
	}

	public void sorteerKaartenPerKleur() {
		// Sort cards by color, put the Joker at the back
		//
		Collections.sort(kaarten, new Comparator<Kaart>() {
			@Override
			public int compare(Kaart k1, Kaart k2) {
				if (k1.getType() == KaartType.Joker && k2.getType() != KaartType.Joker) {
					return -1;
				}
				if (k2.getType() == KaartType.Joker && k1.getType() != KaartType.Joker) {
					return 1;
				}

				return k1.getKleur().compareTo(k2.getKleur());
			}
		});
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Kaart getKleurKaart() {
		return kleurKaart;
	}

	public void setKleurKaart(Kaart kleurKaart) {
		this.kleurKaart = kleurKaart;
	}

	public List<Kaart> getKaarten() {
		return kaarten;
	}

	public void setKaarten(List<Kaart> kaarten) {
		this.kaarten = kaarten;
	}

	public void toonSpelerKaarten() {
		System.out.println("    Kleur kaart is : " + kleurKaart);
		for (Kaart kaart : kaarten) {
			System.out.println("    " + kaart);
		}
	}
	
	public Map<String, Integer> maakKleurenMap() {
		Map<String,Integer> kleurenMap = new HashMap<String,Integer>();
		for (Kaart kaart : kaarten) {
			if (kaart.getType()==KaartType.Kleur) {
				Integer aantal = kleurenMap.get(kaart.getKleur());
				if (aantal==null) {
					kleurenMap.put(kaart.getKleur(), 1);
				} else {
					kleurenMap.put(kaart.getKleur(), aantal+1);
				}
			}
		}
		return kleurenMap;
	}
	
	public int berekenScore() {
		int score = 0;
		
		// De kleuren map bevat het aantal kaarten per kleur
		//
		Map<String, Integer> kleurenMap = maakKleurenMap();
		List<String> kleuren = new ArrayList<String>(kleurenMap.keySet());
		
		// We sorteren de kleuren lijst per aantal
		//
		Collections.sort(kleuren, new Comparator<String>() {

			@Override
			public int compare(String kleur1, String kleur2) {
				int aantal1 = kleurenMap.get(kleur1);
				int aantal2 = kleurenMap.get(kleur2);
				return aantal2-aantal1;
			}
		});
		
		// We nemen de top 3 van de kleuren gesorteerd op aantal
		// Als we minder dan 3 kleuren hebben dan nemen we er minder
		//
		for (int i=0;i<3 && i<kleuren.size();i++) {
			String kleur = kleuren.get(i);
			int aantal = kleurenMap.get(kleur);
			switch(aantal) {
			case 0 : break;
			case 1 : score+=1; break;
			case 2 : score+=3; break;
			case 3 : score+=6; break;
			case 4 : score+=10; break;
			case 5 : score+=15; break;
			default : score+=21; break;
			}
		}
		for (int i=0;i>3 && i<kleuren.size();i++) {
			String kleur = kleuren.get(i);
			int aantal = kleurenMap.get(kleur);
			switch(aantal) {
			case 0 : break;
			case 1 : score+=-1; break;
			case 2 : score+=-3; break;
			case 3 : score+=-6; break;
			case 4 : score+=-10; break;
			case 5 : score+=-15; break;
			default : score+=-21; break;
			}
		}
		// +2 voor elke Plus2 kaart niet vergeten!
		//
		score+=berekenPlus2Score();
		return score;
	}

	private int berekenPlus2Score() {
		int score=0;
		for (Kaart kaart : kaarten) {
			if (kaart.getType()==KaartType.Plus2) {
				score+=2;
			}
		}
		return score; 	 
		
	}

	public List<Kaart> zoekJokers() {
		List<Kaart> jokers = new ArrayList<Kaart>();
		for (Kaart kaart : kaarten) {
			if (kaart.getType()==KaartType.Joker) {
				jokers.add(kaart);
			}
		}
		return jokers;
	}
	
	public void vervangJokerKaart(Kaart joker, String kleur) {
		joker.setKleur(kleur);
		joker.setType(KaartType.Kleur);
	}

}
