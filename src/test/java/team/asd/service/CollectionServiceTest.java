package team.asd.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.reflections.Reflections;
import team.asd.engine.ServicesScannerUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test methods for IsCollectionService implementations")
public class CollectionServiceTest {

	private static final Class<IsCollectionService> serviceClass = IsCollectionService.class;
	private static final List<Object> listWIthStrings = List.of("A", "BB", "CCC",  "A");
	private static final List<Object> listWIthDifferentObject = List.of("A", 1, 0.9, "CCC", "C", "AA");

	private static Stream<IsCollectionService> defineServices() {
		Reflections reflections = new Reflections("team.asd.service");
		Set<Class<? extends IsCollectionService>> classes = reflections.getSubTypesOf(serviceClass);
		ServicesScannerUtils<IsCollectionService> servicesScanner = new ServicesScannerUtils<>();
		return classes.stream().map(servicesScanner::defineServiceImplementations);
	}

	@ParameterizedTest
	@MethodSource("defineServices")
	void testRetrieveObjectsThatPresentInBothLists(IsCollectionService collectionService) {
		assertNotNull(collectionService.retrieveObjectsThatPresentInBothLists(null, null));
		assertEquals(0, collectionService.retrieveObjectsThatPresentInBothLists(Set.of(listWIthStrings), null).size());
		assertEquals(0, collectionService.retrieveObjectsThatPresentInBothLists(null, Set.of(listWIthStrings)).size());
		assertEquals(new HashSet<>(listWIthStrings),
				collectionService.retrieveObjectsThatPresentInBothLists(Set.of(listWIthStrings), Set.of(listWIthStrings)));
		assertEquals(Set.of("A", "CCC"),
				collectionService.retrieveObjectsThatPresentInBothLists(Set.of(listWIthStrings), Set.of(listWIthDifferentObject)));
	}

	@ParameterizedTest
	@MethodSource("defineServices")
	void testUnmodifiableList(IsCollectionService collectionService) {
		assertNotNull(collectionService.unmodifiableList(1, 2));
		assertThrows(UnsupportedOperationException.class, () -> collectionService.unmodifiableList(1, 5).add(2));
		assertEquals(listWIthStrings, collectionService.unmodifiableList(listWIthStrings.toArray()));
	}

	@ParameterizedTest
	@MethodSource("defineServices")
	void testImmutableList(IsCollectionService collectionService) {
		assertNotNull(collectionService.immutableList("0()", "A<>"));
		assertThrows(UnsupportedOperationException.class, () -> collectionService.immutableList("<>B", "C!").add("ASD"));
		assertEquals(listWIthStrings, collectionService.immutableList(listWIthStrings.toArray()));
	}

}
