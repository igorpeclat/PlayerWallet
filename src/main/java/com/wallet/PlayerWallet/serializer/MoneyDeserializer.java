package com.wallet.PlayerWallet.serializer;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * The Class MoneyDeserializer.
 */
public class MoneyDeserializer extends JsonDeserializer<BigDecimal> {
	
	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public BigDecimal deserialize(final JsonParser json, final DeserializationContext ctxt) throws IOException{
		if (!StringUtils.isBlank(json.getText())) {
			return new BigDecimal(json.getText()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return null;
	}
}
