package br.com.victorcampos.elo7.transferscheduler.app;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

import br.com.victorcampos.elo7.transferscheduler.api.rest.ScheduledTransferResource;

public class TransferSchedulerApp extends Application {

    private static DB database;

    public static void main(String[] args) throws Exception {
	// Yep, there could have some concurrency problems
	// but this project runs as a single-thread program, hopefully.
	database = DBMaker.newTempFileDB().make();

	Component component = new Component();

	component.getServers().add(Protocol.HTTP, 8182);
	component.getDefaultHost().attach("/api", new TransferSchedulerApp());

	component.start();
    }

    public static DB getDatabase() {
	return database;
    }

    @Override
    public Restlet createInboundRoot() {
	Router router = new Router();
	router.attach("/transfers/scheduled", ScheduledTransferResource.class);

	return router;
    }

}
