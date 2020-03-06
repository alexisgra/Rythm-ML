package fr.unice.polytech.rythmML.shell;

public class HowToText {

	public static String HOW_TO_GRID = "partition example\n" +
			"\n" +
			"grid {\n" +
			"    bpm BPM_NUMBER\n" +
			"    bpb BEAT_PER_BAR_NUMBER\n" +
			"    composition {\n" +
			"        FIRST_SECTION\n" +
			"        SECOND_SECTION\n" +
			"        THIRD_SECTION\n" +
			"        FIRST_SECTION\n" +
			"    }\n" +
			"}";

	public static String HOW_TO_SECTION = "section SECTION_NAME {\n" +
			"    bar 1-12 BAR_NAME\n" +
			"}";

	public static String HOW_TO_BAR = "bar BAR_NAME {\n" +
			"    NOTE_PLACEMENT \n" +
			"    NOTE_PLACEMENT \n" +
			"}";
}
