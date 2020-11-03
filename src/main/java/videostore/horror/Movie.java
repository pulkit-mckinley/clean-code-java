package videostore.horror;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Movie {
	enum Type {
		CHILDREN,
		REGULAR,
		NEW_RELEASE
	}
	private final String title;
	private final Type type;

	public Movie(String title, Type type) {
		this.title = title;
		this.type = requireNonNull(type);
		// requires a db migration: UPDATE MOVIES SET TYPE='CHILDREN' WHERE TYPE IS NULL;
		// + ALTER TABLE MOVIES SET TYPE NOT NULL;


	}

	public Type getType() {
		return type;
	}

	public String getTitle() {
		return title;
	};
}