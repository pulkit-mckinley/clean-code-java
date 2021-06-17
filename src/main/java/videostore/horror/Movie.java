package videostore.horror;

import static java.util.Objects.requireNonNull;

public class Movie {
	enum Category {
		CHILDREN,
		REGULAR,
		NEW_RELEASE,
//		ELDERS breaks compilation
	}

	private final String title;
	private final Category category;

	public Movie(String title, Category category) {
		this.title = requireNonNull(title);
		this.category = requireNonNull(category);
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}
}