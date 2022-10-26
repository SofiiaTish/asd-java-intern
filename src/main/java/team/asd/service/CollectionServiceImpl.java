package team.asd.service;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionServiceImpl implements IsCollectionService {

	@Override
	public List<Object> unmodifiableList(Object... objects) {
		return Stream.of(objects)
				.collect(Collectors.toUnmodifiableList());
	}

	@Override
	public List<Object> immutableList(Object... objects) {
		return Stream.of(objects)
				.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
	}

	@Override
	public Set<Object> retrieveObjectsThatPresentInBothLists(Set<Object> firstSet, Set<Object> secondSet) {
		if (CollectionUtils.isEmpty(firstSet) || CollectionUtils.isEmpty(secondSet)) {
			return Collections.emptySet();
		}
		boolean is = firstSet.retainAll(secondSet);
		return firstSet;
	}
}
