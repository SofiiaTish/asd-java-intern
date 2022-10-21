package team.asd.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import team.asd.entities.IsPerson;

import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonServiceImpl implements IsPersonService {

	@Override
	@NonNull
	public List<IsPerson> collectPersonsWithNameStartsWith(List<IsPerson> personList, String prefix) {
		if (CollectionUtils.isEmpty(personList)) {
			return Collections.emptyList();
		}
		if (prefix == null || prefix.equals("")) {
			return personList;
		}
		return personList.stream()
				.filter(p -> p != null && p.getName() != null && p.getName()
						.startsWith(prefix))
				.collect(Collectors.toList());
	}

	@Override
	@NonNull
	public Map<Integer, List<IsPerson>> collectPersonsByAge(List<IsPerson> personList) {
		if (personList == null) {
			return Collections.emptyMap();
		}
		return checkAge(personList).sorted(Comparator.comparing(IsPerson::getAge))
				.collect(Collectors.groupingBy(IsPerson::getAge));
	}

	@Override
	@NonNull
	public Double calculateAverageAge(List<IsPerson> personList) {
		if (personList == null) {
			return 0D;
		}
		personList = checkAge(personList).collect(Collectors.toList());
		if (personList.isEmpty()) {
			return 0D;
		}
		return personList.stream()
				.mapToInt(IsPerson::getAge)
				.summaryStatistics()
				.getAverage();
	}

	@Override
	@NonNull
	public IntSummaryStatistics sumAndCountAge(List<IsPerson> personList) {
		if (personList == null) {
			return new IntSummaryStatistics();
		}
		return checkAge(personList).mapToInt(IsPerson::getAge)
				.summaryStatistics();
	}

	private Stream<IsPerson> checkAge(List<IsPerson> list) {
		return list.stream()
				.filter(p -> p != null && p.getAge() != null && p.getAge() >= 0);
	}
}
