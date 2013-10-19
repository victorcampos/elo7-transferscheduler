package br.com.victorcampos.elo7.transferscheduler.entities;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.mapdb.DB;

import br.com.victorcampos.elo7.transferscheduler.entities.transfer.ScheduledTransfer;

public class ScheduledTransferDBPersister {

    private DB database;

    public ScheduledTransferDBPersister(DB database) {
	this.database = database;
    }

    public String save(ScheduledTransfer transfer) {
	Map<String, ScheduledTransfer> dbMap = database
		.getTreeMap("scheduled_transfers");

	String uuid = transfer.getUuid() == null ? UUID.randomUUID().toString()
		: transfer.getUuid();
	
	transfer.setUuid(uuid);
	transfer.setFee(transfer.calculateFee());

	dbMap.put(uuid, transfer);

	return uuid;
    }

    public ScheduledTransfer get(String uuid) {
	Map<String, ScheduledTransfer> dbMap = database
		.getTreeMap("scheduled_transfers");

	return dbMap.get(uuid);
    }

    public Collection<ScheduledTransfer> list() {
	Map<String, ScheduledTransfer> dbMap = database
		.getTreeMap("scheduled_transfers");

	Collection<ScheduledTransfer> transfers = dbMap.values();

	return transfers;
    }

}
