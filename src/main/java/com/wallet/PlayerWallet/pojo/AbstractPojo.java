package com.wallet.PlayerWallet.pojo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class AbstractPojo.
 */
public abstract class AbstractPojo implements Serializable {
    
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2180551618356039000L;
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        // Hashcode por reflection
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        // Equals por reflection
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}