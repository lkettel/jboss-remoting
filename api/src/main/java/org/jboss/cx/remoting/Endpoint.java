package org.jboss.cx.remoting;

import java.net.URI;
import java.util.concurrent.ConcurrentMap;
import org.jboss.cx.remoting.util.AttributeMap;

/**
 * A potential participant in a JBoss Remoting communications relationship.
 */
public interface Endpoint {
    /**
     * Get the endpoint attribute map.  This is a storage area for any data associated with this endpoint, including
     * (but not limited to) connection and protocol information, and application information.
     *
     * You must have the TODO permission to invoke this method.
     *
     * @return the endpoint map
     */
    ConcurrentMap<Object, Object> getAttributes();

    /**
     * Open an outbound session to another endpoint.  The protocol used is determined by the URI scheme.  The URI user-info part
     * must be {@code null} unless the specific protocol has an additional authentication scheme (e.g. HTTP BASIC).  The
     * authority is used to locate the server (the exact interpretation is dependent upon the protocol). The path may be
     * relative to a protocol-specific deployment path.
     *
     * You must have the TODO permission to invoke this method.
     *
     * @param remoteUri the URI of the server to connect to
     * @param attributeMap the attribute map to use to configure this session
     * @param rootListener the root request listener for this end of the session
     * @return a new session
     *
     * @throws RemotingException if there is a problem creating the session, or if the request or reply type does not
     * match the remote service
     */
    Session openSession(URI remoteUri, AttributeMap attributeMap, RequestListener<?, ?> rootListener) throws RemotingException;

    /**
     * Get the name of this endpoint.
     *
     * @return the endpoint name, or {@code null} if there is no name
     */
    String getName();

    /**
     * Create a client that can be used to invoke a request listener on this endpoint.  The client may be passed to a
     * remote endpoint as part of a request or a reply, or it may be used locally.
     *
     * You must have the TODO permission to invoke this method.
     *
     * @param <I> the request type
     * @param <O> the reply type
     * @param requestListener the request listener
     * @return the client
     */
    <I, O> Client<I, O> createClient(RequestListener<I, O> requestListener);

    /**
     * Create a client source that can be used to acquire clients associated with a request listener on this endpoint.
     * The client source may be passed to a remote endpoint as part of a request or a reply, or it may be used locally.
     * The objects that are produced by this method may be used to mass-produce {@code Client} instances.
     *
     * You must have the TODO permission to invoke this method.
     *
     * @param <I> the request type
     * @param <O> the reply type
     * @param requestListener the request listener
     * @return the context source
     */
    <I, O> ClientSource<I, O> createService(RequestListener<I, O> requestListener);

    /**
     * Add a listener that is notified when a session is created.
     *
     * @param sessionListener the session listener
     */
    void addSessionListener(SessionListener sessionListener);

    /**
     * Remove a previously added session listener.
     *
     * @param sessionListener the session listener
     */
    void removeSessionListener(SessionListener sessionListener);
}
