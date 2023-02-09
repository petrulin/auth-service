package com.otus.authservice.util.enums;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class EnumUtil {


	/**
	 * The methods converts enum list to string line by comma.
	 * Enums have to implement {@link LawCase} or {@link UpperCase} interfaces.
	 *
	 * @param enums list of nums

	 * @return string value
	 */
	public static <T extends Marker> String toStringByComma(List<T> enums) {
		return enums.stream().map(t -> t.getClass().getInterfaces()[0].isNestmateOf(LawCase.class)
			? t.toString().toLowerCase()
			: t.toString()
		).collect(joining(","));
	}

	/**
	 * The method converts string value to enum list.
	 *
	 * @param enumClazz type of enum
	 * @param stringValue is representation of enum list as string by comma
	 * @return list of enums
	 */
	public static <E extends Enum<E>> List<E> toEnumList(Class<E> enumClazz, String stringValue) {
		return stream(stringValue.split(","))
			.map(string ->
				Stream.of(enumClazz.getEnumConstants())
					.map(candidate -> candidate.name().equalsIgnoreCase(string)
						? candidate
						: null
					)
					.filter(Objects::nonNull)
					.findFirst()
					.orElseGet(null)
			)
			.collect(toList());
	}
}
