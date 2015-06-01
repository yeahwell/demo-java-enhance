package com.yeahwell.demo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * <h5>Buffer</h5> <h6>property</h6>
 * <ul>
 * <li>capacity</li>
 * <li>position</li>
 * <li>limit</li>
 * </ul>
 * <h6>function</h6>
 * <ul>
 * <li>allocate()</li>
 * <li>read()</li>
 * <li>get()</li>
 * <li>flip()</li>
 * <li>rewind()</li>
 * <li>clear() compact()</li>
 * <li>mark() reset()</li>
 * <li>equals() compareTo()</li>
 * </ul>
 * 
 * @author yanjiawei
 *
 */
public class TestJavaNIO {

	@Test
	public void testBuffer() throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile("hello.txt",
				"rw");
		FileChannel fileChannel = randomAccessFile.getChannel();
		// create buffer with capacity of 48 bytes
		ByteBuffer buffer = ByteBuffer.allocate(48);
		// read into buffer.
		int bytesRead = fileChannel.read(buffer);
		while (-1 != bytesRead) {
			System.out.println("Read " + bytesRead);
			// make buffer ready for read
			buffer.flip();
			while (buffer.hasRemaining()) {
				// read 1 byte at a time
				System.out.println((char) buffer.get());
			}
			// make buffer ready for writing
			buffer.clear();
			bytesRead = fileChannel.read(buffer);
		}
		randomAccessFile.close();
	}

	@Test
	public void testScatteringRead() throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile("hello.txt",
				"rw");
		FileChannel fileChannel = randomAccessFile.getChannel();
		ByteBuffer header = ByteBuffer.allocate(128);
		ByteBuffer body = ByteBuffer.allocate(1024);
		ByteBuffer[] bufferArray = { header, body };
		fileChannel.read(bufferArray);
	}

	@Test
	public void testGatheringWrite() throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile("hello.txt",
				"rw");
		FileChannel fileChannel = randomAccessFile.getChannel();
		ByteBuffer header = ByteBuffer.allocate(128);
		ByteBuffer body = ByteBuffer.allocate(1024);
		ByteBuffer[] bufferArray = { header, body };
		fileChannel.read(bufferArray);
	}

	@Test
	public void testTransferFrom() throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();
		RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
		FileChannel toChannel = toFile.getChannel();
		long position = 0;
		long count = fromChannel.size();
		toChannel.transferFrom(fromChannel, position, count);
	}

	@Test
	public void testTransferTo() throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();
		RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
		FileChannel toChannel = toFile.getChannel();
		long position = 0;
		long count = fromChannel.size();
		fromChannel.transferTo(position, count, toChannel);
	}

	@Test
	public void testSelector() throws IOException {
		Selector selector = Selector.open();
		SelectableChannel channel = null;
		channel.configureBlocking(false);
		SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
		while (true) {
			int readyChannels = selector.select();
			if (readyChannels == 0)
				continue;
			Set selectedKeys = selector.selectedKeys();
			Iterator keyIterator = selectedKeys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey key1 = (SelectionKey) keyIterator.next();
				if (key1.isAcceptable()) {
					// a connection was accepted by a ServerSocketChannel.
				} else if (key1.isConnectable()) {
					// a connection was established with a remote server.
				} else if (key1.isReadable()) {
					// a channel is ready for reading
				} else if (key1.isWritable()) {
					// a channel is ready for writing
				}
				keyIterator.remove();
			}
		}
	}

	@Test
	public void testServerSocketChannel() throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(9999));
		serverSocketChannel.configureBlocking(false);
		while (true) {
			SocketChannel socketChannel = serverSocketChannel.accept();
			if (socketChannel != null) {
				// do something with socketChannel...
			}
		}
	}
	
	@Test
	public void testDatagramChannel() throws IOException{
		DatagramChannel channel = DatagramChannel.open();
		channel.socket().bind(new InetSocketAddress(9999));
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		channel.receive(buf);
		
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf1 = ByteBuffer.allocate(48);
		buf1.clear();
		buf1.put(newData.getBytes());
		buf1.flip();
		int bytesSent = channel.send(buf1, new InetSocketAddress("jenkov.com", 80));
		
		int bytesRead = channel.read(buf);
		int bytesWritten = channel.write(buf);
		
	}
	
	@Test
	public void testPipe() throws IOException{
		Pipe pipe = Pipe.open();
		Pipe.SinkChannel sinkChannel = pipe.sink();
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		while(buf.hasRemaining()) {
		    sinkChannel.write(buf);
		}
		
		Pipe.SourceChannel sourceChannel = pipe.source();
		ByteBuffer buf2 = ByteBuffer.allocate(48);
		int bytesRead = sourceChannel.read(buf2);
		
	}
	
}
