package com.wallet.PlayerWallet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wallet.PlayerWallet.pojo.AbstractPojo;

/**
 * The Class AbstractEntity.
 *
 * @param <T> the generic type
 */
public abstract class AbstractEntity<T extends AbstractPojo> extends AbstractPojo {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3686418618274743298L;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public abstract T getId();

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public abstract void setId(T id);

	/**
	 * Gets the id to string.
	 *
	 * @return the id to string
	 */
	@JsonIgnore
	public String getIdToString() {
		// TODO 0000 Implement id to string convertion
		return String.valueOf(hashCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.leroymerlin.common.pojo.AbstractPojo#hashCode()
	 */
	@Override
	public int hashCode() {
		return (getId() == null) ? super.hashCode() : getId().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.leroymerlin.common.pojo.AbstractPojo#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		// If not an instance of this class, return false
		if (!AbstractEntity.class.isInstance(obj)) {
			return false;
		}

		AbstractEntity<?> that = (AbstractEntity<?>) obj;
		// If both ids are null, return true
		if (that.getId() == null && this.getId() == null) {
			return true;
		}

		// If id equals to null and the other not, return alse
		if (that.getId() == null && this.getId() != null) {
			return false;
		}

		if (that.getId() != null && this.getId() == null) {
			return false;
		}

		// Comparing ids
		return that.getId().equals(this.getId());
	}

	/**
	 * Checks if is controle md 5.
	 *
	 * @return true, if is controle md 5
	 */
	@JsonIgnore
	public boolean isControleMd5() {
		return false;
	}
}