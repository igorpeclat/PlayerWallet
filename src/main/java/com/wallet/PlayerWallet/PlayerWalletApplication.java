package com.wallet.PlayerWallet;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;

/**
 * The Class PlayerWalletApplication.
 */
@SpringBootApplication
public class PlayerWalletApplication extends SpringBootServletInitializer {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(PlayerWalletApplication.class);
	
	/** The env. */
	@Autowired
    private Environment env;
    
    /**
     * Inits the application.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0) {
            LOG.warn("No Spring profile configured, running with default configuration");
        } else {
            LOG.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
        }
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PlayerWalletApplication.class);
        app.run(PlayerWalletApplication.class, args);
    }

    /* (non-Javadoc)
     * @see org.springframework.boot.web.servlet.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PlayerWalletApplication.class);
    }
}
