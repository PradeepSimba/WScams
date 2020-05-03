package org.sambasoft;


import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/wsServer")
public class WsServer {
	
	@OnOpen
	public void OnOpen(Session session) {
		System.out.println(session.toString()); 
	}
	
	@OnMessage
	public void onMessage(Session ss,byte[] img) throws IOException, AWTException {
		
		try {
                    while(1==1){
                    BufferedImage screenshot = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write( screenshot, "jpg", baos );
                baos.flush();
                byte[] imageInByte = baos.toByteArray();
                baos.close();
                ByteBuffer buf = ByteBuffer.wrap(imageInByte);
			ss.getBasicRemote().sendBinary(buf);
                        
                    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@OnClose
	public void onClose(Session ss) {
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
