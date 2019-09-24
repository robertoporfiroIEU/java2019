package be.technifutur.applicationrunner;

import java.util.Scanner;

public class ConsoleEnvironnement implements Environnement {

	private Scanner scan = new Scanner(System.in);

	@Override
	public void print(CharSequence out) {
		System.out.print(out);
	}

	@Override
	public String nextLine() {
		return this.scan.nextLine();
	}

}
