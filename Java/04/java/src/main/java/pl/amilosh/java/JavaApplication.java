package pl.amilosh.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;

@SpringBootApplication
public class JavaApplication {

	public static void main(String[] args) {
		/* Matching for instanceOf */
		var bird = Bird.getBird();
		// old approach
		if (bird instanceof Mallard) {
			System.out.println(((Mallard) bird).quack());
		} else if (bird instanceof ImperialPenguin) {
			System.out.println(((ImperialPenguin) bird).shh());
		}
		// new approach
		if (bird instanceof Mallard mallard) {
			System.out.println(mallard.quack());
		} else if (bird instanceof ImperialPenguin imperialPenguin) {
			System.out.println(imperialPenguin.shh());
		}

		if (!(bird instanceof Mallard mallard1)) {
			return;
		}
		System.out.println(mallard1.quack());

		var noise = bird instanceof Mallard mallard ? mallard.quack() : "";

		/* Switch expressions */
		// old approach
		String message;
		switch (ZonedDateTime.now().getDayOfWeek()) {
			case SATURDAY:
			case SUNDAY:
				message = "weekday";
				break;
			default:
				message = "workday";
				break;
		}
		// new approach
		message = switch (ZonedDateTime.now().getDayOfWeek()) {
			case SATURDAY, SUNDAY -> "weekday";
			case FRIDAY -> {
				System.out.println("hurra");
				yield "friday";
			}
			default -> "workday";
		};

		/* Helpful NullPointerException. */
		var birdNull = Bird.getNullBird();
		System.out.println(birdNull.getName());

		/* Text blocks. */
		var sql = """
			select
				id, name
			from user;
			""";
		var msg = "message = %s".formatted("hello");
	}
}
