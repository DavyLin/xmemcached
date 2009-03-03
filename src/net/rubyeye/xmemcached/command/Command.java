package net.rubyeye.xmemcached.command;

import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

import com.google.code.yanf4j.nio.Message;

public class Command implements Message {
	public static final String SPLIT = "\r\n";

	Object key;
	Object result;
	CountDownLatch latch;
	CommandType commandType;
	RuntimeException throwable;

	public enum CommandType implements Message {
		GET_ONE, GET_MANY, SET, REPLACE, ADD, EXCEPTION, DELETE, VERSION, INCR, DECR, GET, STORE, OTHER;

		public int getLength() {
			return 4;
		}

	}

	public Command() {
		super();
	}

	public Command(CommandType cmdType) {
		this.commandType = cmdType;
	}

	public Command(CommandType cmdType, CountDownLatch latch) {
		this.commandType = cmdType;
		this.latch = latch;
	}

	public Command(Object key, Object result, String cmd, CountDownLatch latch) {
		super();
		this.key = key;
		this.result = result;
		this.latch = latch;
	}

	public Command(String key, CommandType commandType, CountDownLatch latch) {
		super();
		this.key = key;
		this.commandType = commandType;
		this.latch = latch;
	}

	public synchronized RuntimeException getException() {
		return throwable;
	}

	public synchronized void setException(RuntimeException throwable) {
		this.throwable = throwable;
	}

	public synchronized Object getKey() {
		return key;
	}

	public synchronized void setKey(Object key) {
		this.key = key;
	}

	public synchronized Object getResult() {
		return result;
	}

	public synchronized void setResult(Object result) {
		this.result = result;
	}

	public ByteBuffer[] getCmd() {
		return null;
	}

	public CountDownLatch getLatch() {
		return latch;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	public int getLength() {
		return 4;
	}

}