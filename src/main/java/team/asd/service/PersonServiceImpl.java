package team.asd.service;

import lombok.NonNull;
import team.asd.entities.IsPerson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonServiceImpl implements IsPersonService {

	@Override
	@NonNull
	public List<IsPerson> collectPersonsWithNameStartsWith(List<IsPerson> personList, String prefix) {
		if (personList == null || personList.isEmpty())
			return new ArrayList<>();
		if (prefix == null || prefix.equals(""))
			return personList;
		return personList.stream()
				.filter(p -> p != null && p.getName() != null && p.getName()
						.startsWith(prefix))
				.collect(Collectors.toList());
	}

	@Override
	@NonNull
	public Map<Integer, List<IsPerson>> collectPersonsByAge(List<IsPerson> personList) {
		if (personList == null)
			return new HashMap<>();
		return personList.stream()
				.filter(p -> p != null && p.getAge() != null && p.getAge() >= 0)
				.sorted(Comparator.comparing(IsPerson::getAge))
				.collect(Collectors.groupingBy(IsPerson::getAge));
	}

	@Override
	@NonNull
	public Double calculateAverageAge(List<IsPerson> personList) {
		if (personList == null)
			return 0D;
		personList = personList.stream()
				.filter(p -> p != null && p.getAge() != null && p.getAge() >= 0)
				.collect(Collectors.toList());
		if (personList.isEmpty())
			return 0D;
		return personList.stream()
				.mapToInt(IsPerson::getAge)
				.summaryStatistics()
				.getAverage();
	}

	@Override
	@NonNull
	public IntSummaryStatistics sumAndCountAge(List<IsPerson> personList) {
		if (personList == null)
			return new IntSummaryStatistics();
		return personList.stream()
				.filter(p -> p != null && p.getAge() != null && p.getAge() >= 0)
				.mapToInt(IsPerson::getAge)
				.summaryStatistics();
	}
}
