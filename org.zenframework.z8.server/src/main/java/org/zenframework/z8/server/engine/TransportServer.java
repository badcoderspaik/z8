package org.zenframework.z8.server.engine;

import java.rmi.RemoteException;

import org.zenframework.z8.server.config.ServerConfig;
import org.zenframework.z8.server.ie.ExportMessages;
import org.zenframework.z8.server.ie.Message;
import org.zenframework.z8.server.logs.Trace;

public class TransportServer extends RmiServer implements ITransportServer {

	private static final long serialVersionUID = 6031141331643514419L;

	private static TransportServer INSTANCE;

	private TransportServer(ServerConfig config) throws RemoteException {
		super(ITransportServer.class);
	}

	public static void start(ServerConfig config) throws RemoteException {
		if (INSTANCE == null) {
			INSTANCE = new TransportServer(config);
			INSTANCE.start();
		}
	}

	public static TransportServer get() {
		return INSTANCE;
	}

	@Override
	public void start() throws RemoteException {
		super.start();
		Trace.logEvent("TS: transport server started at '" + getUrl() + "'");
	}

	@Override
	public void sendMessage(Message message) throws RemoteException {
		try {
			new ExportMessages.CLASS<ExportMessages>().get().addMessage(message, getUrl());
		} catch (Throwable e) {
			throw new RemoteException("Can't import message '" + message.getId() + "' from '" + message.getSender() + "'", e);
		}
	}

}
