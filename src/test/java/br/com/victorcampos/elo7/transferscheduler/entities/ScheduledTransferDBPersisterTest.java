package br.com.victorcampos.elo7.transferscheduler.entities;

import static org.junit.Assert.*;

import java.util.Collection;

import org.joda.time.DateTime;
import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import br.com.victorcampos.elo7.transferscheduler.InvalidArgumentException;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.ScheduledTransfer;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.TypeAScheduledTransfer;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.TypeBScheduledTransfer;
import br.com.victorcampos.elo7.transferscheduler.entities.transfer.TypeCScheduledTransfer;

public class ScheduledTransferDBPersisterTest {

    @Test
    public void testSave() throws InvalidArgumentException {
	DB database = DBMaker.newDirectMemoryDB().make();
	ScheduledTransferDBPersister persister = new ScheduledTransferDBPersister(
		database);

	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(1);
	ScheduledTransfer scheduledTransfer = new TypeAScheduledTransfer(
		"12345-6", "12345-6", 0, now, scheduledDate);

	String uuid = persister.save(scheduledTransfer);
	assertEquals(scheduledTransfer, persister.get(uuid));
    }

    @Test
    public void testGet() throws InvalidArgumentException {
	DB database = DBMaker.newDirectMemoryDB().make();
	ScheduledTransferDBPersister persister = new ScheduledTransferDBPersister(
		database);

	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(1);
	ScheduledTransfer scheduledTransfer = new TypeAScheduledTransfer(
		"12345-6", "12345-6", 0, now, scheduledDate);

	String uuid = persister.save(scheduledTransfer);
	assertEquals(scheduledTransfer, persister.get(uuid));
    }

    @Test
    public void testList() throws InvalidArgumentException {
	DB database = DBMaker.newDirectMemoryDB().make();
	ScheduledTransferDBPersister persister = new ScheduledTransferDBPersister(
		database);

	DateTime now = new DateTime();
	DateTime scheduledDate = now.plusDays(1);
	ScheduledTransfer scheduledTransfer = new TypeAScheduledTransfer(
		"12345-6", "12345-6", 0, now, scheduledDate);
	ScheduledTransfer typeBScheduledTransfer = new TypeBScheduledTransfer(
		"12345-6", "12345-6", 10000, now, scheduledDate);
	ScheduledTransfer typeCScheduledTransfer = new TypeCScheduledTransfer(
		"12345-6", "12345-6", 20000, now, scheduledDate);

	persister.save(scheduledTransfer);
	persister.save(typeBScheduledTransfer);
	persister.save(typeCScheduledTransfer);
	
	Collection<ScheduledTransfer> list = persister.list();
	
	assertEquals(3, list.size());
    }

}
