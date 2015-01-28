package net.stefankrause.sj;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.inject.Named;

import org.springframework.stereotype.Component;

@Named
public class GeburtsdatumValidatorService {
	public boolean isDatumValid(String datum) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate dateTime = LocalDate.parse(datum, formatter);
		if (dateTime.isAfter(LocalDate.now())) {
			return false;
		}
		return true;
	}
}
