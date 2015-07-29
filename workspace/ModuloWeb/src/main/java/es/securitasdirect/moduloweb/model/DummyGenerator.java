package es.securitasdirect.moduloweb.model;

import org.wso2.ws.dataservice.*;
import org.wso2.ws.dataservice.Installation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javier Naval on 24/07/2015.
 */
public class DummyGenerator {

    public static List<DirectAccess> getDirectAcess() {
        List<DirectAccess> l = new ArrayList<DirectAccess>();

        DirectAccess da1 = new DirectAccess();
        da1.setName("aaaaa");
        da1.setDescription("descr");
        da1.setUrl("http://www.google.es");
        da1.setPosition(0);
        l.add(da1);


        DirectAccess da2 = new DirectAccess();
        da2.setName("bbbbb");
        da2.setDescription("descr");
        da2.setUrl("http://www.google.es");
        da2.setPosition(1);
        l.add(da2);

        return l;
    }

    public static InstallationData getInstallation(Integer installationNumber) {
        InstallationData installation = new InstallationData();
        //TODO Inicializar con basura
        return installation;
    }
}
