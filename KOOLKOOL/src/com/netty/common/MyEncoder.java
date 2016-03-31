package com.netty.common;

import java.io.*;

import com.thoughtworks.xstream.*;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class MyEncoder  extends  MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) {
    	
    	ByteArrayOutputStream byteout =new  ByteArrayOutputStream( );
    	
    	ObjectOutputStream dout = null;

    	try {
    		dout = new ObjectOutputStream(byteout);
    		dout.writeObject(msg);
    		
    		
    		byte[] data2 = byteout.toByteArray();
    		int dataLength2 = data2.length;
            out.writeInt(dataLength2);  // data length
            out.writeBytes(data2);      // data
            return;
    	
    	} catch(Exception ex) {
    		    		    ex.printStackTrace();		
    	} finally {
    		try {
    			dout.close();
    			byteout.close();
    		} catch(Exception ex) {
    			
    			
    		}
    		
    	}
    	
    	
    	/*
    	XStream xstream = new XStream();
    	
    	byte[] data = null;
    	try {
    		data = xstream.toXML(msg).getBytes("UTF-8");
    	} catch (Exception ex) {}
    	
        int dataLength = data.length;

        out.writeInt(dataLength);  // data length
        out.writeBytes(data);      // data
        
        */
    }
}
