package com.netty.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import java.io.*;


public class MyDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
		// Wait until the length prefix is available.
		if (in.readableBytes() < 4) {
			return;
		}

		in.markReaderIndex();

		// Wait until the whole data is available.
		int dataLength = in.readInt();
		if (in.readableBytes() < dataLength) {
			in.resetReaderIndex();
			return;
		}

		byte[] decoded = new byte[dataLength];
		in.readBytes(decoded);


		ByteArrayInputStream bytein =new  ByteArrayInputStream(decoded);

		ObjectInputStream data = null;

		try {
			data = new ObjectInputStream(bytein);   

			Object ret = data.readObject();
			out.add(ret);

			return;

		} catch (Exception ex) {

			System.out.println("netty decoding error");
			ex.printStackTrace();

		} finally {

			try {
				data.close();
				bytein.close();
			} catch(Exception ex) {
				
				
			}
		}

/*
		XStream xstream = new XStream();


		String temp ="";
		try {
			temp = new String(decoded,"UTF-8");

		} catch (Exception ex) {


		}
		Object obj = xstream.fromXML(temp);				
		out.add(obj);
*/

	}
}