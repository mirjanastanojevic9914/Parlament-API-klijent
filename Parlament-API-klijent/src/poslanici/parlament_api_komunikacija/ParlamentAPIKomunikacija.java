package poslanici.parlament_api_komunikacija;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import poslanici.poslanik.Poslanik;


public class ParlamentAPIKomunikacija {

	private static final String membersURL = "http://147.91.128.71:9090/parlament/api/members";
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"dd.MM.yyyy.");

	public static List<Poslanik> vratiPoslanike() {
		List<Poslanik> poslanici = new LinkedList<Poslanik>();
		try {
			String result = sendGet(membersURL);
			Gson gson = new GsonBuilder().create();
			JsonArray membersJson = gson.fromJson(result, JsonArray.class);

			for (int i = 0; i < membersJson.size(); i++) {
				JsonObject memberJson = (JsonObject) membersJson.get(i);

				Poslanik p = new Poslanik();
				p.setId(memberJson.get("id").getAsInt());
				p.setIme(memberJson.get("name").getAsString());
				p.setPrezime(memberJson.get("lastName").getAsString());
				if (memberJson.get("birthDate") != null)
					p.setDatumRodjenja(sdf.parse(memberJson.get("birthDate")
							.getAsString()));

				poslanici.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return poslanici;
	}

	public static JsonArray vratiPoslanikeJSon() throws Exception {
		return new GsonBuilder().setPrettyPrinting().create()
				.fromJson(sendGet(membersURL), JsonArray.class);
	}

	private static String sendGet(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));

		boolean endReading = false;
		String response = "";

		while (!endReading) {
			String s = in.readLine();

			if (s != null) {
				response += s;
			} else {
				endReading = true;
			}
		}
		in.close();

		return response.toString();
	}

	public static JsonArray prebaciUJsonNiz(List<Poslanik> poslanici) {
		
		JsonArray jsonArray = new JsonArray();
		JsonObject jsonObject = null;

		for (int i = 0; i < poslanici.size(); i++) {
			Poslanik pos = poslanici.get(i);

			jsonObject = new JsonObject();
			jsonObject.addProperty("id", pos.getId());
			jsonObject.addProperty("name", pos.getIme());
			jsonObject.addProperty("lastName", pos.getPrezime());
			
			try {
				jsonObject.addProperty("birthDate",
						sdf.format(pos.getDatumRodjenja()));
			} catch (Exception e) {
				jsonObject.addProperty("birthDate", "nn");
			}

			jsonArray.add(jsonObject);

		}

		return jsonArray;
	}

}
