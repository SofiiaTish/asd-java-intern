package team.asd.entities;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface IsDateRange {

	Stream<LocalDate> getDateRange();

}
