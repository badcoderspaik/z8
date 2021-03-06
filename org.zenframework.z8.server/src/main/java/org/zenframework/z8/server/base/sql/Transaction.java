package org.zenframework.z8.server.base.sql;

import org.zenframework.z8.server.db.ConnectionManager;
import org.zenframework.z8.server.runtime.OBJECT;

public class Transaction extends OBJECT {
	static public void z8_begin() {
		ConnectionManager.get().beginTransaction();
	}

	static public void z8_commit() {
		ConnectionManager.get().commit();
	}

	static public void z8_rollback() {
		ConnectionManager.get().rollback();
	}

	static public void z8_flush() {
		ConnectionManager.get().flush();
	}
}
