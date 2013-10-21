package br.com.victorcampos.elo7.transferscheduler.api.rest;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;
import br.com.victorcampos.elo7.transferscheduler.app.TransferSchedulerApp;
import br.com.victorcampos.elo7.transferscheduler.entities.ScheduledTransferDBPersister;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.ScheduledTransfer;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.factory.ScheduledTransferFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ScheduledTransferResource extends ServerResource {

    private ScheduledTransferDBPersister persister;

    public ScheduledTransferResource() {
	setPersister(new ScheduledTransferDBPersister(
		TransferSchedulerApp.getDatabase()));
    }

    public ScheduledTransferResource(ScheduledTransferDBPersister persister) {
	setPersister(persister);
    }

    private static class DateTimeTypeConverter implements
	    JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

	public JsonElement serialize(DateTime src, Type srcType,
		JsonSerializationContext context) {
	    return new JsonPrimitive(src.toString());
	}

	public DateTime deserialize(JsonElement json, Type type,
		JsonDeserializationContext context) throws JsonParseException {
	    try {
		return new DateTime(json.getAsString());
	    } catch (IllegalArgumentException e) {
		Date date = context.deserialize(json, Date.class);
		return new DateTime(date);
	    }
	}
    }

    @Get("json")
    public String handleGet() {
	ScheduledTransferDBPersister persister = getPersister();

	String jsonRepresentation = null;
	String uuid = getQueryValue("uuid");

	jsonRepresentation = (uuid != null) ? getSingleScheduledTransfer(uuid,
		persister) : listAllScheduledTransfers(persister);

	return jsonRepresentation;
    }

    private String listAllScheduledTransfers(
	    ScheduledTransferDBPersister persister) {
	GsonBuilder gson = new GsonBuilder();
	gson.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());

	String jsonRepresentation = gson.create().toJson(persister.list());

	return jsonRepresentation;
    }

    private String getSingleScheduledTransfer(String uuid,
	    ScheduledTransferDBPersister persister) {
	ScheduledTransfer scheduledTransfer = persister.get(uuid);

	GsonBuilder gson = new GsonBuilder();
	gson.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());

	String jsonRepresentation = gson.create().toJson(scheduledTransfer);

	return jsonRepresentation;
    }

    @Post
    public String create(Representation parameters) {
	ScheduledTransferDBPersister persister = getPersister();

	Form form = new Form(getRequest().getEntity());

	ScheduledTransfer scheduledTransfer = null;
	try {
	    scheduledTransfer = ScheduledTransferFactory
		    .buildFromPostData(form);
	} catch (InvalidArgumentException e) {
	    // TODO: refactor error/exception/validation system to return a more
	    // informative error message
	    getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
	    return getErrorRepresentation("Error creating new scheduled transfer");
	}

	scheduledTransfer = persister.save(scheduledTransfer);

	GsonBuilder gson = new GsonBuilder();
	gson.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());

	return gson.create().toJson(scheduledTransfer);
    }

    private String getErrorRepresentation(String errorMessage) {
	Map<String, String> errorMap = new HashMap<String, String>();
	errorMap.put("error", errorMessage);

	return new Gson().toJson(errorMap);
    }

    public ScheduledTransferDBPersister getPersister() {
	return persister;
    }

    public void setPersister(ScheduledTransferDBPersister persister) {
	this.persister = persister;
    }

}
