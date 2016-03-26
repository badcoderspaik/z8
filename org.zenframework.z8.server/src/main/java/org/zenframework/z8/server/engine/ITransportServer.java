package org.zenframework.z8.server.engine;

import java.rmi.RemoteException;

import org.zenframework.z8.server.ie.Message;

public interface ITransportServer extends IServer {

	void sendMessage(Message message) throws RemoteException;

}
