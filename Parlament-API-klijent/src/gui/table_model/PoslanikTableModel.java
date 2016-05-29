package gui.table_model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import poslanici.poslanik.Poslanik;

public class PoslanikTableModel extends AbstractTableModel {
	
	private static final String[] columnNames = { "ID", "Name", "Last name", "Birth date" };
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
	
	
	private List<Poslanik> poslanici;
	
	public PoslanikTableModel(List<Poslanik> poslanici) {
		super();

		if (poslanici != null) {
			this.poslanici = poslanici;
		} else {
			this.poslanici = new LinkedList<>();
		}
	}
	public PoslanikTableModel() {
		this.poslanici = new LinkedList<>();
	}
	
	

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return poslanici.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Poslanik poslanik = poslanici.get(rowIndex);

		switch (columnIndex) {
			case 0:
				return poslanik.getId();
			case 1:
				return poslanik.getIme();
			case 2:
				return poslanik.getPrezime();
			case 3:
				return (poslanik.getDatumRodjenja() != null) ? sdf.format(poslanik.getDatumRodjenja()) : "nepoznato";
	
			default:
				return "NN";
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return false;
		}

		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Poslanik p = poslanici.get(rowIndex);

		boolean greska = false;
		String tekst = "";
		
		switch (columnIndex) {
			case 1:
				String ime = aValue.toString();
				if (ime != null && !ime.trim().isEmpty()) {
					p.setIme(ime);
				} else {
					greska = true;
					tekst = "Ime ne moze da bude prazan string.";
				}
				break;
	
			case 2:
				String prezime = aValue.toString();
				if (prezime != null && !prezime.trim().isEmpty()) {
					p.setPrezime(prezime);
				} else {
					greska = true;
					tekst = "Prezime ne moze da bude prazan string.";
				}
				break;
	
			case 3:
				String datumString = aValue.toString();
				try {
					Date datum = sdf.parse(datumString);
					p.setDatumRodjenja(datum);
				} catch (ParseException e) {
					greska = true;
					tekst = "Datum mora da bude u sledecem formatu: dd.MM.yyyy.";
				}
	
				break;
	
			default:
				return;
		}

		if (greska) {
			JOptionPane.showMessageDialog(null, tekst, "Greska", JOptionPane.ERROR_MESSAGE);
		}
		
		fireTableCellUpdated(rowIndex, columnIndex);

	}

	public void setPoslanici(List<Poslanik> poslanici) {
		if (poslanici != null) {
			this.poslanici = poslanici;
			fireTableDataChanged();
		}
	}
	
	public List<Poslanik> getPoslanici() {
		return poslanici;
	}

	public void isprazniListu() {
		poslanici = new LinkedList<>();
		fireTableDataChanged();
	}


}
