package jackson.section1.sortingExamples;

/**
 * A media object in an iTunes library that may be a
 * podcast, song, video, etc.
 * 
 * @author shilad
 */
public class Media implements Comparable<Media> {
	
	private String filePath;
	private String name;
	private int count;
	
	public Media(String filePath, String name, int count) {
		this.filePath = filePath;
		this.name = name;
		this.count = count;
	}

	public Media(String filePath, String name) {
        this(filePath, name, 0);
	}

	@Override
	public String toString() {
		return "media: " + name + " at " + filePath + " with count " + count;
	}

	public boolean matchesQuery(String query) {
		return getName().toLowerCase().contains(query.toLowerCase());
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}

	public String getFilePath() {
		return filePath;
	}

	@Override
    public boolean equals(Object other) {
		if (other == null || !(other instanceof Media)){
            return false;
        }

        Media media = (Media) other;
        return (filePath.equals(media.filePath) && name.equals(media.name));
    }

    public int compareTo(Media other){
		return this.count - other.count;
	}
}
