package team.asd.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import team.asd.entities.IsPerson;

import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonServiceImpl implements IsPersonService {

	@Override
	@NonNull
	public List<IsPerson> collectPersonsWithNameStartsWith(List<IsPerson> personList, String prefix) {
		if (CollectionUtils.isEmpty(personList)) {
			return Collections.emptyList();
		}
		if (StringUtils.isEmpty(prefix)) {
			return personList;
		}
		return personList.stream()
				.filter(p -> p != null && StringUtils.startsWith(p.getName(), prefix))
				.collect(Collectors.toList());
	}

	@Override
	@NonNull
	public Map<Integer, List<IsPerson>> collectPersonsByAge(List<IsPerson> personList) {
		if (personList == null) {
			return Collections.emptyMap();
		}
		return personList.stream()
				.filter(filterPersonsByAge())
				.collect(Collectors.groupingBy(IsPerson::getAge));
	}

	@Override
	@NonNull
	public Double calculateAverageAge(List<IsPerson> personList) {
		if (CollectionUtils.isEmpty(personList)) {
			return 0D;
		}
		personList = personList.stream()
				.filter(filterPersonsByAge())
				.collect(Collectors.toList());
		if (personList.isEmpty()) {
			return 0D;
		}
		return personList.stream().collect(Collectors.averagingInt(IsPerson::getAge));
	}

	@Override
	@NonNull
	public IntSummaryStatistics sumAndCountAge(List<IsPerson> personList) {
		if (personList == null) {
			return new IntSummaryStatistics();
		}
		return personList.stream()
				.filter(filterPersonsByAge())
				.mapToInt(IsPerson::getAge)
				.summaryStatistics();
	}

	private Predicate<IsPerson> filterPersonsByAge() {
		return person -> person != null && person.getAge() != null && person.getAge() >= 0;
	}
}
