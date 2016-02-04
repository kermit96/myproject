package iedu.util;

import javax.websocket.server.ServerEndpointConfig.Configurator;

/**
 * ChatServerEndPointConfigurator
 * @author Jiji_Sasidharan
 */
 public class WebSocketServletConfigurator extends Configurator {
 
    private static ieduWebSocketServlet websocketServer = new ieduWebSocketServlet();
 
    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return (T)websocketServer;
    }
}
