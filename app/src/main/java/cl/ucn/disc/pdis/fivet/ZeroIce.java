/*
 * Copyright (c) 2020. This project is private and this use is only for education,
 * DONT USE THIS PROJECT IN PRODUCTION
 * Alvaro Castillo alvarolucascc96@gmail.com
 */

package cl.ucn.disc.pdis.fivet;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Util;
import com.zeroc.Ice.InitializationData;
import com.zeroc.Ice.Properties;
import com.zeroc.Ice.ObjectPrx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.pdis.fivet.zeroice.model.Contratos;
import cl.ucn.disc.pdis.fivet.zeroice.model.ContratosPrx;

@SuppressWarnings("Singleton")
public class ZeroIce {

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ZeroIce.class);

    /**
     * Singleton
     */
    private static final ZeroIce ZERO_ICE = new ZeroIce();

    /**
     * the communicator with zeroice
     */
    private Communicator theCommunicator;

    /**
     * Contratos
     */
    private ContratosPrx theContratos;

    /**
     * for singleton patron
     */
    private ZeroIce() {
        //nothing to do
    }

    /**
     * get a unique instance for Singleton
     *
     * @return ZERO_ICE
     */
    public static ZeroIce getInstance() {
        return ZERO_ICE;
    }

    /**
     * get Communicator
     *
     * @return theCommunicator
     */
    public Communicator getTheCommunicator() {
        return theCommunicator;
    }

    /**
     * get Contratos
     *
     * @return theContratos
     */
    public ContratosPrx getTheContratos() {
        return theContratos;
    }

    /**
     * @param args to use as source.
     * @return the {@link InitializationData}.
     */
    private static InitializationData getInitializationData(String[] args) {

        // Properties
        final Properties properties = Util.createProperties(args);

        properties.setProperty("Ice.Package.model", "cl.ucn.disc.pdis.fivet.zeroice");

        // https://doc.zeroc.com/ice/latest/property-reference/ice-trace
        // properties.setProperty("Ice.Trace.Admin.Properties", "1");
        // properties.setProperty("Ice.Trace.Locator", "2");
        // properties.setProperty("Ice.Trace.Network", "3");
        // properties.setProperty("Ice.Trace.Protocol", "1");
        // properties.setProperty("Ice.Trace.Slicing", "1");
        // properties.setProperty("Ice.Trace.ThreadPool", "1");
        // properties.setProperty("Ice.Compression.Level", "9");
        properties.setProperty("Ice.Plugin.Slf4jLogger.java", "cl.ucn.disc.pdis.fivet.zeroice.Slf4jLoggerPluginFactory");

        InitializationData initializationData = new InitializationData();
        initializationData.properties = properties;

        return initializationData;
    }

    /**
     * start the communication
     */
    public void start() {

        if (this.theCommunicator != null) {
            log.warn("The Communicator was already initialized?");
            return;
        }
        theCommunicator = Util.initialize(getInitializationData(new String[1]));

        //Name
        String name = Contratos.class.getSimpleName();
        log.debug("Proxying <{}>", name);

        ObjectPrx theProxy = theCommunicator.stringToProxy(name + ":tcp -z -t 15000 -p 8080 ");
        this.theContratos = ContratosPrx.checkedCast(theProxy);

        long delay = theContratos.getDelay(System.currentTimeMillis());
        log.debug("Delay: {} ms.", delay);
    }

    /**
     * stop the communication
     */
    public void stop() {
        if (this.theCommunicator == null) {
            log.warn("The Communicator was already stopped?");
            return;
        }
        theCommunicator = null;
        theCommunicator.destroy();
    }
}
