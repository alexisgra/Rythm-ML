package fr.unice.polytech.rythmML.kernel.data;

public enum Instruments {
	bd("kick drum or bass drum"), sd("snare drum"), ch("closed hihat"), oh("opened hihat"),
	cc("crash cymbal"), rc("ride cymbal");

	public final String label;

	Instruments(final String label) {
		this.label = label;
	}
}
