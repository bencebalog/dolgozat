import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.LinkedList;

public class Main {

	private class Virag {
		public int ora;
		public int perc;
		public int darab;
		public String virag;
		public String szin;
		
		public Virag(String sor) {
			String[] s = sor.split(";");
			ora = Integer.parseInt(s[0].split(":")[0]);
			perc = Integer.parseInt(s[0].split(":")[1]);
			darab = Integer.parseInt(s[1]);
			virag = s[2];
			szin = s[3];
		}
	}
	
	LinkedList<Virag> lista = new LinkedList<>();
	
	private void f0_betolt(String fajlnev) {
		Scanner be = null;
		try {
			be = new Scanner(new File(fajlnev), "utf-8");
			be.nextLine();
			while (be.hasNextLine()) lista.add(new Virag(be.nextLine()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (be != null) be.close();
		}
		System.out.printf("0) %d rendelés adata beolvasva\n", lista.size());
	}
	
	private void f1_viragok() {
		TreeSet<String> virag = new TreeSet<>();
		for (Virag x : lista) virag.add(x.virag);
		System.out.printf("1) Összesen %d féle virágot rendeltek (abc):\n   ", virag.size());
		for (String x : virag) System.out.printf("%s ", x);
	}
	
	private void f2_orankent() {
		TreeMap<Integer, Integer> orak = new TreeMap<>();
		for (Virag x : lista) {
			if (!orak.containsKey(x.ora)) orak.put(x.ora, 1);
			else orak.put(x.ora, orak.get(x.ora)+1);
		}
		System.out.printf("2) Rendelések száma óránkénti bontásban:\n   ");
		int max = 0, maxOra = 0;
		for (Integer x : orak.keySet()) {
			System.out.printf("%02d/%d ", x, orak.get(x));
			if (orak.get(x) > max) { max = orak.get(x); maxOra = x; }
		}
		System.out.println("rendelés");
		System.out.printf("   A legtöbb %d:00-%d:59 között: %d rendelés\n", maxOra, maxOra, max);
	}
	
	private void f3_szinek() {
		TreeMap<String, Integer> szin = new TreeMap<>();
		for (Virag x : lista) {
			if (!szin.containsKey(x.szin)) szin.put(x.szin, x.darab);
			else szin.put(x.szin, szin.get(x.szin) + x.darab);
		}
		System.out.printf("3) Eladott darabszám színek szerinti (abc) csoportosításban:\n   ");
		int n = szin.keySet().size(), i=0;
		for (String x : szin.keySet()) {
			System.out.printf("%d %s", szin.get(x), x);
			i++; if (i<n) System.out.print(", ");
		}
		System.out.println();
	}
	
	public Main() {
		f0_betolt("virag.csv");
		f1_viragok();
		f2_orankent();
		f3_szinek();
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
