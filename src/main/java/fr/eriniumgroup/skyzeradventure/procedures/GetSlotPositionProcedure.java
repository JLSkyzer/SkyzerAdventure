package fr.eriniumgroup.skyzeradventure.procedures;

public class GetSlotPositionProcedure {
	public static java.util.List<Object> execute(double slot, double spacing) {
		java.util.List<Object> object = new java.util.ArrayList<>();
		double ring = 0;
		double index = 0;
		double positionInRing = 0;
		double posInRing = 0;
		double angle = 0;
		if (slot == 0) {
			object.add((int) 0);
			object.add((int) 0);
			return object;
		}
		ring = 1;
		index = 1;
		while (true) {
			positionInRing = ring * 8;
			if (slot < index + positionInRing) {
				posInRing = slot - index;
				angle = (2 * Math.PI * posInRing) / positionInRing;
				object.add((int) Math.round(Math.cos(angle) * ring * spacing));
				object.add((int) Math.round(Math.sin(angle) * ring * spacing));
				break;
			}
			index = index + positionInRing;
			ring = ring + 1;
		}
		return object;
	}
}