package gui.kontroler;

import gui.GlavniProzor;
import gui.table_model.PoslanikTableModel;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import poslanici.poslanik.Poslanik;

import javax.swing.JOptionPane;

import poslanici.parlament_api_komunikacija.ParlamentAPIKomunikacija;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GUIKontroler {
	
	private static GlavniProzor glavniProzor;
	private static final String adresa = "data/serviceMembers.json";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					glavniProzor = new GlavniProzor();
					glavniProzor.setLocationRelativeTo(null);
					glavniProzor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void ispisi(String t) {
		glavniProzor.getTextArea().append(t + System.lineSeparator());
	}
	
	public static void vratiPoslanikeUJsonFormatu(){
		try{
			JsonArray jsonArray = ParlamentAPIKomunikacija.vratiPoslanikeJSon();

			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(adresa)));

			String tekst = new GsonBuilder().setPrettyPrinting().create().toJson(jsonArray);
			out.println(tekst);

			out.close();
			String t = "Preuzeti poslanici";
			ispisi(t);
			
		} catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
	public static void popuniTabelu(){
		try{
		List<Poslanik> poslanici = ucitajPoslanike(); 
		PoslanikTableModel model = (PoslanikTableModel) glavniProzor.getTable().getModel();
		model.setPoslanici(poslanici);
		String t = "Ucitani poslanici";
		ispisi(t);
		
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private static List<Poslanik> ucitajPoslanike() throws Exception {
		
		List<Poslanik> poslanici = new LinkedList<>();

		FileReader fr = new FileReader(adresa);

		JsonArray jsonAray = new GsonBuilder().create().fromJson(fr, JsonArray.class);

		fr.close();

		for (int i = 0; i < jsonAray.size(); i++) {
			JsonObject jsonObject = (JsonObject) jsonAray.get(i);

			Poslanik pos = new Poslanik();
			pos.setId(jsonObject.get("id").getAsInt());
			pos.setIme(jsonObject.get("name").getAsString());
			pos.setPrezime(jsonObject.get("lastName").getAsString());
			if (jsonObject.get("birthDate") != null) {
				try {
					pos.setDatumRodjenja((Date) new SimpleDateFormat("dd.MM.yyyy.")
							.parse(jsonObject.get("birthDate").getAsString()));
				} catch (ParseException e) {
				}
			}

			poslanici.add(pos);
		}

		return poslanici;
	}
	
}
