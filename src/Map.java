import java.util.ArrayList;

enum Name {
	HA_NOI,
	HOA_BINH,
	THAI_BINH,
	THANH_HOA,
	NGHE_AN,
}

public class Map {
	static Place[] places;
	private int count;
	private ArrayList<Place> permutationList = new ArrayList<Place>();
		
	void init() {
		places = new Place[Constants.NUMBER_OF_PLACES];
		places[Name.HA_NOI.ordinal()] = new Place(Name.HA_NOI.toString(), new Resource(0, 0, 1));//food
		places[Name.HOA_BINH.ordinal()] = new Place(Name.HOA_BINH.toString(), new Resource(0, 1, 0));//steel
		places[Name.THAI_BINH.ordinal()] = new Place(Name.THAI_BINH.toString(), new Resource(0, 0, 1));//food
		places[Name.THANH_HOA.ordinal()] = new Place(Name.THANH_HOA.toString(), new Resource(1, 0, 0));//wood
		places[Name.NGHE_AN.ordinal()] = new Place(Name.NGHE_AN.toString(), new Resource(1, 0, 0));//wood
		
		//link places
		linkPlaces(Name.HA_NOI.ordinal(), Name.HOA_BINH.ordinal());
		linkPlaces(Name.HA_NOI.ordinal(), Name.THAI_BINH.ordinal());
		linkPlaces(Name.HOA_BINH.ordinal(), Name.THANH_HOA.ordinal());
		linkPlaces(Name.THAI_BINH.ordinal(), Name.THANH_HOA.ordinal());
		linkPlaces(Name.THANH_HOA.ordinal(), Name.NGHE_AN.ordinal());
		
		//enemy setup
		count = -1;
		for (Place place:places) {
			permutationList.add(place);
		}
	}

	public Place generateEnemy() {
		count++;
		if (count%Constants.NUMBER_OF_PLACES==0) {
			//start shuffle
			java.util.Collections.shuffle(permutationList);
			count = 0;
		}
		return permutationList.get(count);
	}
	
	static void linkPlaces(int i1, int i2) {
		Map.places[i1].connectedPlaces.add(Map.places[i2]);
		Map.places[i2].connectedPlaces.add(Map.places[i1]);
	}	
}
