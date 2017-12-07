package org.aksw.dice.HARE.model;

public class Entity {
	String uri;
	String rank;

	public Entity(String uri, String rank) {
		this.uri = uri;
		this.rank = rank;

	}

	/**
	 * @return the uri
	 */

	public String getUri() {
		return uri;
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

}
