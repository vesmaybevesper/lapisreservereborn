package dev.spagurder.modtemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LapisReserveReborn {

    public static final String MOD_ID = "lapisreservereborn";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        LOG.info("Initializing {} on {}", MOD_ID, Platform.INSTANCE.loader());
    }

}
